package com.cmnit.gatherdata.modules.module

import java.sql.{Connection, PreparedStatement, ResultSet}
import java.util.Calendar

import com.alibaba.fastjson.JSON
import com.cmnit.gatherdata.enums.KafkaTopicEnum
import com.cmnit.gatherdata.modules.bean.GantryRunningStatusData
import com.cmnit.gatherdata.modules.utils.DateUtil
import com.cmnit.gatherdata.utils.{ConfigurationManager, PhoenixUtils}
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.TopicPartition
import org.apache.log4j.Logger
import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010.{HasOffsetRanges, KafkaUtils, OffsetRange}
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.collection.mutable

object GantryRunningStatusDataModule {

  final val logger = Logger.getLogger(GantryRunningStatusDataModule.getClass)

  def startStreaming(sparkSession: SparkSession, kafkaBootstrapServers: String, zkConnect: String): Unit = {

    val topic = KafkaTopicEnum.RM_TGHBU_TOPIC.getTopic
    val durationLength = ConfigurationManager.getProperty("dual.time")
    val groupId = ConfigurationManager.getProperty("group.id")

    val sc = sparkSession.sparkContext
    val ssc = new StreamingContext(sc, Seconds(durationLength.toLong))
    val saslJass = ConfigurationManager.getProperty("sasl.jaas.config")
    val autoOffsetReset = ConfigurationManager.getProperty("auto.offset.reset")
    val topics = topic.split(",").toSet

    //设置kafka参数
    val kafkaParams = Map[String, String](
      "bootstrap.servers" -> kafkaBootstrapServers,
      "group.id" -> groupId,
      "enable.auto.commit" -> "false",
      "auto.offset.reset" -> autoOffsetReset,
      "zookeeper.connect" -> zkConnect,
      "key.deserializer" -> "org.apache.kafka.common.serialization.StringDeserializer",
      "value.deserializer" -> "org.apache.kafka.common.serialization.StringDeserializer",
      "security.protocol" -> "SASL_PLAINTEXT",
      "sasl.mechanism" -> "GSSAPI",
      "sasl.kerberos.service.name" -> "kafka",
      "sasl.jaas.config" -> saslJass
    )

    // 数据库中offset查询
    val offsets: mutable.HashMap[TopicPartition, Long] = mutable.HashMap[TopicPartition, Long]()
    var connect: Connection = null
    var pstmt: PreparedStatement = null
    var result: ResultSet = null
    try {
      connect = PhoenixUtils.getconnection
      for (elem <- topics) {
        val valueFields: String = elem + "," + groupId
        println(valueFields)

        pstmt = PhoenixUtils.queryHbaseByPhoenix("select * from offset_topic where topic = ? and groupId = ?", valueFields, connect)
        result = pstmt.executeQuery
        while (result.next) {
          // 分区数
          val p = result.getString("partitionNum")
          // 偏移量
          val o = result.getString("untilOffset")
          // 创建topic分区
          val partition: TopicPartition = new TopicPartition(elem, p.toInt)
          // 设置topic分区的偏移量
          offsets.put(partition, o.toInt)
        }
      }
    } catch {
      case e: Exception => println(e)
    }
    finally {
      PhoenixUtils.close(connect, pstmt, result)
    }

    // 连接kafka
    var dataInputStream: InputDStream[ConsumerRecord[String, String]] = null
    if (offsets.isEmpty) {
      println("数据库中的offset没有初始值")
      dataInputStream = KafkaUtils.createDirectStream(
        ssc,
        PreferConsistent,
        Subscribe[String, String](topics, kafkaParams)
      )
    } else {
      println("offsets:" + offsets)
      dataInputStream = KafkaUtils.createDirectStream(
        ssc,
        PreferConsistent,
        Subscribe[String, String](topics, kafkaParams, offsets)
      )
    }

    import sparkSession.implicits._
    dataInputStream.foreachRDD(rdd => {
      try {
        val df = rdd.repartition(2)
          .filter(x => !x.value.isEmpty)
          .map(_.value)
          .map(line => {
            JSON.parseObject(line.toString, classOf[GantryRunningStatusData])
          }).map(info => {
          val calendar = Calendar.getInstance()
          // TODO 修改时间字段
          val time = DateUtil.parse(info.receivetime.toString, "yyyy-MM-dd HH:mm:ss")
          calendar.setTime(time)
          info.year = calendar.get(Calendar.YEAR).toString
          info.month = if ((calendar.get(Calendar.MONTH) + 1).toString.length < 2) {
            "0" + (calendar.get(Calendar.MONTH) + 1)
          } else {
            (calendar.get(Calendar.MONTH) + 1).toString
          }
          info.day = if (calendar.get(Calendar.DATE).toString.length < 2) {
            "0" + calendar.get(Calendar.DATE)
          } else {
            calendar.get(Calendar.DATE).toString
          }
          info.hour = if (calendar.get(Calendar.HOUR_OF_DAY).toString.length < 2) {
            "0" + calendar.get(Calendar.HOUR_OF_DAY)
          } else {
            calendar.get(Calendar.HOUR_OF_DAY).toString
          }
          info
        }).toDF()
        df.show(30, false)
        // 创建视图
        df.createOrReplaceTempView("temp_GantryRunningStatusData")
        sparkSession.sql("insert into table ods.ods_etc_gantryrunningstatusdata partition(year,month,day,hour) select * from temp_GantryRunningStatusData")
        // offset更新
        val ranges: Array[OffsetRange] = rdd.asInstanceOf[HasOffsetRanges].offsetRanges
        ranges.foreach(offset => {
          // 插入的字段
          val updateFields: String = "topic,partitionNum,untilOffset,groupId"
          // 插入的字段数据
          val valueFields: String = offset.topic + "," + offset.partition + "," + offset.untilOffset + "," + groupId
          print("offset表更新：")
          PhoenixUtils.replaceHbaseByPhoenix("offset_topic", updateFields, valueFields)
        })
      } catch {
        case e: Exception => println(e)
      }
    })

    ssc.start()
    ssc.awaitTermination()
  }
}
