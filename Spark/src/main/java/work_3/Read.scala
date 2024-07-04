package work_3

import org.apache.hadoop.hbase.{HBaseConfiguration, TableName}
import org.apache.hadoop.hbase.client.{ConnectionFactory, Scan}


object Read {
  def main(args: Array[String]): Unit = {
    val conf = HBaseConfiguration.create()
    val connection = ConnectionFactory.createConnection(conf)
    val admin = connection.getAdmin()

    val table = connection.getTable(TableName.valueOf("Water_Bill"))
    val scan = new Scan()
    val result = table.getScanner(scan)
    for (i <- 0 until 10) {
      val row = result.next()
      val key = row.getRow()
      println(key)
    }

  }

}
