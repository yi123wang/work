package scala

import org.apache.spark.sql.SparkSession

/**
 * @author Daim
 * @date 2024/3/11 14:17
 * @version 1.0
 */
object Top_10 {
  def main(args: Array[String]): Unit = {
    // 创建 SparkSession
    val spark = SparkSession.builder()
      .appName("Nginx Log Analyzer")
      .master("local[*]")  // 指定本地模式的线程数，* 表示使用所有可用的线程
      .getOrCreate()

    import spark.implicits._
    // 读取系统日志文件
    val logData = spark.read.textFile("D:\\access.log")

    // 提取 IP 地址
    val ipRegex = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}".r
    val ips = logData.flatMap(
      line => ipRegex.findAllIn(line)
    ).map(ip => (ip,1))

    // 统计每个 IP 地址出现的次数
    val ipCounts = ips.rdd.countByValue()

    // 对 IP 地址按出现次数进行降序排序
    val top10IPs = ipCounts.toSeq.sortBy(-_._2).take(10)

    // 打印结果
    top10IPs.foreach { case (ip, count) =>
      println(s"IP: $ip\tCount: $count")
    }

    // 停止 SparkSession
    spark.stop()
  }
}