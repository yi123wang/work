package sui_ji_sheng_cheng_10w

import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.client.ConnectionFactory
import org.apache.hadoop.hbase.mapreduce.TableInputFormat
import org.apache.hadoop.hbase.util.Bytes
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{DataFrame, SparkSession}


object OOOO {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("hbase")
    val sc = new SparkContext(conf)
    val config = HBaseConfiguration.create()
    config.set(TableInputFormat.INPUT_TABLE, "emp") // 设置要读取的 HBase 表
    val connection = ConnectionFactory.createConnection(config)

    val spark = SparkSession.builder().config(conf).getOrCreate()

    try {
//      val context = new HBaseContext(sc, config)
      val hbaseRDD = sc.newAPIHadoopRDD(config, classOf[TableInputFormat], classOf[org.apache.hadoop.hbase.io.ImmutableBytesWritable], classOf[org.apache.hadoop.hbase.client.Result])
      val resultRDD = hbaseRDD.map{ case (key, result) =>
        val rowKey = Bytes.toStringBinary(key.get())
        val nameValue = result.getValue(Bytes.toBytes("c1"), Bytes.toBytes("name"))
        val name = if (nameValue != null) Bytes.toString(nameValue) else "N/A"
        (rowKey, name)
      }

      import spark.implicits._
      val df: DataFrame = resultRDD.toDF("rowkey", "name")
      df.show()
    } catch {
      case e: Exception => println("Error occurred: " + e.getMessage)
    } finally {
      sc.stop() // 关闭 SparkContext
      connection.close() // 关闭 HBase 连接
      spark.stop() // 关闭 SparkSession
    }
  }

}
