package scala

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object Top_10_1 {
  def main(args: Array[String]): Unit = {
    // 创建SparkSession
    val spark = SparkSession.builder()
      .appName("Top 10 Analysis")
      .master("local[*]")
      .getOrCreate()

    // 读取系统日志文件
    val userData = spark.read.textFile("D:\\u1.txt")

    // 使用正则表达式匹配 Male 统计个数
    val malePattern = ".*Male.*"
    val maleLines = userData.filter(col("value").rlike(malePattern))
    val maleCount = maleLines.count()

    // 使用正则表达式匹配 Female 统计个数
    val femalePattern = ".*Female.*"
    val femaleLines = userData.filter(col("value").rlike(femalePattern))
    val femaleCount = femaleLines.count()

    // 根据年龄进行排序 top10
//    val top10ByAge = userData.select(
//        split(col("value"), ",").getItem(0).as("name"),  // 提取姓名
//        split(col("value"), ",").getItem(1).as("gender"),  // 提取性别
//        split(col("value"), ",").getItem(2).as("age"))  // 提取年龄
//      .orderBy(desc("age"))
//      .limit(10)

    // 打印年龄前十结果
//    top10ByAge.show()

    // 打印男女比例
    println(s"男性个数/女性个数: $maleCount/$femaleCount")
  }
}
