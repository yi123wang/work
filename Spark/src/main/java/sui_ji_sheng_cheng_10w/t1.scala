package sui_ji_sheng_cheng_10w

import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.client.ConnectionFactory
import org.apache.hadoop.hbase.mapreduce.TableInputFormat
import org.apache.hadoop.hbase.util.Bytes
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{DataFrame, SparkSession}

object t1 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("hbase")
    val sc = new SparkContext(conf)
    val config = HBaseConfiguration.create()
    config.set(TableInputFormat.INPUT_TABLE, "emp") // 设置要读取的 HBase 表
    val connection = ConnectionFactory.createConnection(config)

    val spark = SparkSession.builder().config(conf).getOrCreate()

    try {
      val hbaseRDD = sc.newAPIHadoopRDD(config, classOf[TableInputFormat], classOf[org.apache.hadoop.hbase.io.ImmutableBytesWritable], classOf[org.apache.hadoop.hbase.client.Result])
      val resultRDD = hbaseRDD.map{ case (_, result) =>
        val nameValue = result.getValue(Bytes.toBytes("c1"), Bytes.toBytes("name"))
        val ageValue = result.getValue(Bytes.toBytes("c1"), Bytes.toBytes("age"))
        val sexValue = result.getValue(Bytes.toBytes("c1"), Bytes.toBytes("sex"))
        val addressValue = result.getValue(Bytes.toBytes("c1"), Bytes.toBytes("address"))
        val salaryValue = result.getValue(Bytes.toBytes("c1"), Bytes.toBytes("salary"))

        val name = if (nameValue != null) Bytes.toString(nameValue) else "N/A"
        val age = if (ageValue != null) Bytes.toInt(ageValue) else 0
        val sex = if (sexValue != null) Bytes.toString(sexValue) else "N/A"
        val address = if (addressValue != null) Bytes.toString(addressValue) else "N/A"
        val salary = if (salaryValue != null) Bytes.toInt(salaryValue) else 0

        (name, age, sex, address, salary)
      }

      import spark.implicits._
      val df: DataFrame = resultRDD.toDF("name", "age", "sex", "address", "salary")
      // 统计性别比例
      val genderCounts = df.groupBy("sex").count()
      val totalCount = df.count()
      val genderRatio = genderCounts.withColumn("ratio", genderCounts("count") / totalCount * 100)
      // 按性别降序排序
      val sortedGenderRatio = genderRatio.sort($"count".desc)
      // 取出前10名用户
      val top10Users = sortedGenderRatio.limit(10)
      top10Users.show()
    } catch {
      case e: Exception => println("Error occurred: " + e.getMessage)
    } finally {
      sc.stop() // 关闭 SparkContext
      connection.close() // 关闭 HBase 连接
      spark.stop() // 关闭 SparkSession
    }
  }
}
