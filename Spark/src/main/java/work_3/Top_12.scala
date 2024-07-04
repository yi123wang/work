package work_3

import org.apache.spark.sql.{SparkSession, Row}
import org.apache.spark.sql.types._

object Top_12 {
  def main(args: Array[String]): Unit = {
    // 创建 SparkSession
    val spark = SparkSession.builder()
      .appName("GenderRatio")
      .master("local[*]")
      .getOrCreate()

    // 读取 u1 文件为 RDD
    val rdd = spark.sparkContext.textFile("file:///D:/emp.txt")

    // 定义 Schema
    val schema = StructType(Array(
      StructField("name", StringType, nullable = true),
      StructField("gender", StringType, nullable = true),
      StructField("age", IntegerType, nullable = true),
      StructField("city", StringType, nullable = true)
    ))

    // 转换为 DataFrame
    val df = spark.createDataFrame(rdd.map(line => {
      val fields = line.split(", ")
      Row(fields(0), fields(1), fields(2).toInt, fields(3))
    }), schema)

    // 按性别统计人数
    val genderCounts = df.groupBy("gender").count().orderBy("gender")

    // 打印结果
    genderCounts.show()

    // 停止 SparkSession
    spark.stop()
  }
}
