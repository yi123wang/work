package scala

import org.apache.spark.sql.SparkSession

object emp {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("GenderRatioCalculator")
      .master("local[*]")
      .getOrCreate()

    // 读取数据
    val data = spark.read.option("header", "false").csv("/lzm/data.csv")

    // 将数据转换为DataFrame并定义列名
    val df = data.toDF("ID", "Name", "Date", "Gender", "Salary", "Department")

    // 计算男女总数
    val total = df.count()
    val maleCount = df.filter(df("Gender") === "Male").count()
    val femaleCount = df.filter(df("Gender") === "Female").count()

    // 计算比例
    val maleRatio = maleCount.toDouble / total
    val femaleRatio = femaleCount.toDouble / total

    // 打印结果
    println(s"男性比例：$maleRatio")
    println(s"女性比例：$femaleRatio")

    spark.stop()
  }
}
