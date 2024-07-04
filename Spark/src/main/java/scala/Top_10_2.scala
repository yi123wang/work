package scala

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object Top_10_2 {
  def main(args: Array[String]): Unit = {
    // 创建SparkSession
    val spark = SparkSession.builder()
      .appName("Express Data Analyzer")
      .master("local[*]") // 指定本地模式的线程数，* 表示使用所有可用的线程
      .getOrCreate()

    // 读取快递信息文件
    val expressData = spark.read.textFile("D:\\u2.txt")

    // 提取城市和人名
    val cityPersonData = expressData
      .select(split(col("value"), ",").getItem(1).as("city"),  // 提取城市
        split(col("value"), ",").getItem(0).as("person"))  // 提取人名

    // 统计每个城市的个数并按个数倒序排序
    val cityCount = cityPersonData.groupBy("city").count().orderBy(desc("count")).limit(10)
    // 统计每个人的个数并按个数倒序排序
    val personCount = cityPersonData.groupBy("person").count().orderBy(desc("count")).limit(10)

    // 打印每个城市的个数
    print("每个城市的个数：")
    cityCount.show()
    // 打印每个人的个数
    print("每个人的个数：")
    personCount.show()
  }
}
