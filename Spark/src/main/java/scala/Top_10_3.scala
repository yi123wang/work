package scala

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object Top_10_3 {
  def main(args: Array[String]): Unit = {
    // 创建SparkSession
    val spark = SparkSession.builder()
      .appName("Web Data Analyzer")
      .master("local[*]")
      .getOrCreate()

    // 读取数据
    val webData = spark.read.textFile("D:\\u3.txt")

    // 提取字段
    val data = webData.select(
      split(col("value"), ",").getItem(0).as("ip"), // 提取ip
      split(col("value"), ",").getItem(1).as("website"), // 提取website
      split(col("value"), ",").getItem(2).as("visit_count"), // 提取访问次数
      split(col("value"), ",").getItem(3).as("stay_time"), // 提取停留时间
      split(col("value"), ",").getItem(4).as("traffic_consumed") // 提取消耗流量
    )

    // 1. 统计ip的访问次数及停留时间
    val ipStats = data.groupBy("ip").agg(sum("visit_count").as("total_visits"), sum("stay_time").as("total_stay_time"))
    print("1. 统计ip的访问次数及停留时间:")
    ipStats.show()

    // 2. 统计最热门的站点
    val topWebsites = data.groupBy("website").agg(sum("visit_count").as("total_visits")).orderBy(desc("total_visits")).limit(10)
    print("2. 统计最热门的站点:")
    topWebsites.show()

    // 3. 统计耗费流量最多的ip
    val topTrafficIP = data.groupBy("ip").agg(sum("traffic_consumed").as("total_traffic")).orderBy(desc("total_traffic")).limit(1)
    print("3. 统计耗费流量最多的ip:")
    topTrafficIP.show()

    // 4. 统计最多的手机型号
    val topPhoneModels = data.groupBy("phone_model").agg(sum("visit_count").as("total_visits")).orderBy(desc("total_visits")).limit(1)
    print("4. 统计最多的手机型号:")
    topPhoneModels.show()

    // 关闭SparkSession
    spark.stop()
  }
}
