package com.cmnit.gatherdata

import com.cmnit.gatherdata.modules.module.{GantryBaseInfoDataModule, GantryBillHourSumModule, GantryDownloadRecordInfoModule, GantryETCBillModule, GantryRunningStatusDataModule, GantryVehDisDataModule, GantryVehDisHourSumModule, GantryVehDisPicModule, TestPhoenix, TollChargerShiftEnSumInfoModule, TollChargerShiftExSumInfoModule, TollEnBillInfoModule, TollExBillInfoModule, TollHeartBeatInfoModule, TollLoadGantryBillHourSumModule, TollLoadGantryBillInfoModule, TollOpLogInfoModule, TollRealTimeMonitorInfoModule, TollVehDisDataModule, TollVehDisHourSumModule, TollVehDisPicModule}
import com.cmnit.gatherdata.utils.ConfigurationManager
import org.apache.log4j.Logger
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object Main {
  val logger: Logger = Logger.getLogger(Main.getClass)

  def main(args: Array[String]): Unit = {
    // 获取运行环境
    val productOrTest = ConfigurationManager.getProperty("product.or.test")
    logger.info(s"====================== 当前运行环境：${productOrTest} ===================================")

    // 获取app名称
    var appName = args(0)
    if (appName == null || appName == "") {
      appName = ConfigurationManager.getProperty("which.app.to.run")
    }
    logger.info(s"=======================执行的程序名：${appName} ==========================================")

    // 获取日志级别
    val logLevel: String = ConfigurationManager.getProperty("log.level")

    // kerberos配置
    System.setProperty("java.security.auth.login.config", ConfigurationManager.getProperty("java.security.auth.login.config"))
    System.setProperty("java.security.krb5.conf", ConfigurationManager.getProperty("java.security.krb5.conf"))
    System.setProperty("zookeeper.sasl.clientconfig", "Client")
    System.setProperty("zookeeper.sasl.client", "true")

    // TODO 本地模式需要用户密码传入
    /*UserGroupInformation.loginUserFromKeytab(ConfigurationManager.getProperty("kerberos.principal"), ConfigurationManager.getProperty("keytab.file"))
    println("login user: " + UserGroupInformation.getLoginUser)*/

    // 离线计算参数
    val conf = new SparkConf()
    conf.set("spark.serializer", ConfigurationManager.getProperty("spark.serializer"))
    conf.set("spark.scheduler.mode", ConfigurationManager.getProperty("spark.scheduler.mode"))
    conf.set("spark.executor.extraJavaOptions", ConfigurationManager.getProperty("spark.executor.extraJavaOptions"))
    conf.set("spark.checkpointPath", ConfigurationManager.getProperty("spark.checkpointPath"))
    conf.set("spark.default.parallelism", ConfigurationManager.getProperty("spark.default.parallelism"))
    conf.set("spark.sql.shuffle.partitions", ConfigurationManager.getProperty("spark.sql.shuffle.partitions"))
    conf.set("sparkSession.debug.maxToStringFields", ConfigurationManager.getProperty("sparkSession.debug.maxToStringFields"))
    //流式计算参数
    conf.set("spark.streaming.concurrentJobs", "1")
    conf.set("spark.scheduler.mode", "FAIR")
    //确保application kill 后接收的数据能被处理完在关闭
    conf.set("spark.streaming.stopGracefullyOnShutdown", "true")

    val kafkaBootstrapServers = ConfigurationManager.getProperty("bootstrap.servers")
    val zkConnect = ConfigurationManager.getProperty("zookeeper.connect")

    //创建sparkSession
    val sparkSession: SparkSession = SparkSession
      .builder()
      // TODO 本地模式修改master类型
      //.master("local[*]")
      .appName(s"${appName}")
      .config(conf)
      .enableHiveSupport()
      .getOrCreate()
    sparkSession.sparkContext.setLogLevel(logLevel)
    sparkSession.sqlContext.setConf("hive.exec.dynamic.partition.mode", "nonstrict")

    //选取合适的app执行
    appName match {
      // 测试
      case "Test" => TestPhoenix.startStreaming(sparkSession, kafkaBootstrapServers, zkConnect)

      // 分区字段已确定
      // 196
      case "GantryETCBill" => GantryETCBillModule.startStreaming(sparkSession, kafkaBootstrapServers, zkConnect)
      case "GantryVehDisData" => GantryVehDisDataModule.startStreaming(sparkSession, kafkaBootstrapServers, zkConnect)
      // 197
      case "GantryVehDisPic" => GantryVehDisPicModule.startStreaming(sparkSession, kafkaBootstrapServers, zkConnect)
      case "TollVehDisData" => TollVehDisDataModule.startStreaming(sparkSession, kafkaBootstrapServers, zkConnect)
      // 198
      case "TollVehDisPic" => TollVehDisPicModule.startStreaming(sparkSession, kafkaBootstrapServers, zkConnect)
      case "TollLoadGantryBillInfo" => TollLoadGantryBillInfoModule.startStreaming(sparkSession, kafkaBootstrapServers, zkConnect)
      case "TollExBillInfo" => TollExBillInfoModule.startStreaming(sparkSession, kafkaBootstrapServers, zkConnect)
      // 199
      case "TollEnBillInfo" => TollEnBillInfoModule.startStreaming(sparkSession, kafkaBootstrapServers, zkConnect)

      // 暂未确定分区字段，使用receivetime
      // 196
      case "GantryBaseInfoData" => GantryBaseInfoDataModule.startStreaming(sparkSession, kafkaBootstrapServers, zkConnect)
      case "GantryBillHourSum" => GantryBillHourSumModule.startStreaming(sparkSession, kafkaBootstrapServers, zkConnect)
      case "GantryVehDisHourSum" => GantryVehDisHourSumModule.startStreaming(sparkSession, kafkaBootstrapServers, zkConnect)
      // 197
      case "GantryDownloadRecordInfo" => GantryDownloadRecordInfoModule.startStreaming(sparkSession, kafkaBootstrapServers, zkConnect)
      case "GantryRunningStatusData" => GantryRunningStatusDataModule.startStreaming(sparkSession, kafkaBootstrapServers, zkConnect)
      case "TollOpLogInfo" => TollOpLogInfoModule.startStreaming(sparkSession, kafkaBootstrapServers, zkConnect)
      // 198
      case "TollVehDisHourSum" => TollVehDisHourSumModule.startStreaming(sparkSession, kafkaBootstrapServers, zkConnect)
      case "TollLoadGantryBillHourSum" => TollLoadGantryBillHourSumModule.startStreaming(sparkSession, kafkaBootstrapServers, zkConnect)
      // 199
      case "TollRealTimeMonitorInfo" => TollRealTimeMonitorInfoModule.startStreaming(sparkSession, kafkaBootstrapServers, zkConnect)
      case "TollHeartBeatInfo" => TollHeartBeatInfoModule.startStreaming(sparkSession, kafkaBootstrapServers, zkConnect)
      case "TollChargerShiftExSumInfo" => TollChargerShiftExSumInfoModule.startStreaming(sparkSession, kafkaBootstrapServers, zkConnect)
      case "TollChargerShiftEnSumInfo" => TollChargerShiftEnSumInfoModule.startStreaming(sparkSession, kafkaBootstrapServers, zkConnect)

      case _ => {
        logger.error("输入要运行的程序不存在,程序退出")
        System.exit(1)
      }
    }
  }
}
