import java.sql.{PreparedStatement, ResultSet}

import com.cmnit.gatherdata.utils.{ConfigurationManager, PhoenixUtils}
import org.apache.hadoop.security.UserGroupInformation

object PhoenixTest {
  def main(args: Array[String]): Unit = {

    System.setProperty("java.security.krb5.conf", ConfigurationManager.getProperty("Test.java.security.krb5.conf"))
    UserGroupInformation.loginUserFromKeytab("test@HADOOP.COM", ConfigurationManager.getProperty("Test.keytab.file"))
    println("login user: " + UserGroupInformation.getLoginUser)


    val connection = PhoenixUtils.getconnection
    /*    // 创建表
        val sql = "CREATE TABLE offset_topic(topic varchar, partitionNum varchar, untilOffset varchar, groupId varchar,CONSTRAINT my_rowkey PRIMARY KEY (topic, partitionNum, groupId))"
        val ps = connection.prepareStatement(sql)
        ps.execute
        println("create success...")

        val updateFields = "topic,partitionNum,untilOffset,groupId"
        val valueFields = Array("test_202111,1,295,test_202110_test", "test_202110,0,1185,test_202110_test", "test_202111,2,298,test_202110_test", "test_202111,0,303,test_202110_test")
        for (elem <- valueFields) {
          // 更新表
          PhoenixUtils.replaceHbaseByPhoenix("offset_topic", updateFields, elem)
        }*/



    // 查询表
    var ps: PreparedStatement = null
    var resultSet: ResultSet = null
    try {
      val exeSQL = "select * from offset_topic where topic = ? and groupId = ?"
      val valueFields: String = "test_202111,test_202110_test"
      ps = PhoenixUtils.queryHbaseByPhoenix(exeSQL, valueFields, connection)
      resultSet = ps.executeQuery
      while (resultSet.next) {
        val topic = resultSet.getString("topic");
        val partitionNum = resultSet.getString("partitionNum");
        val untilOffset = resultSet.getString("untilOffset");
        val groupId = resultSet.getString("groupId");
        println(topic + "--" + partitionNum + "--" + untilOffset + "--" + groupId);
      }
    } catch {
      case e: Exception => println(e)
    }
    finally {
      PhoenixUtils.close(connection, ps, resultSet)
    }
  }

}
