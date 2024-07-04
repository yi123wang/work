package Hbase;




import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

import java.io.IOException;


public class HbaseClinet {
    public static void main(String[] args) throws IOException {
        //1.创建客户端
        Configuration conf = HBaseConfiguration.create();
        //2.创建连接
        Connection conn = ConnectionFactory.createConnection(conf);
        //3.创建管理者
        Admin admin = conn.getAdmin();
        //4.根据admin里面进行梳理！9
        TableName[] names = admin.listTableNames();
        for (TableName name : names) {
            System.out.println(name.getNameAsString());
        }
        //5.根据admin 实现创建一个表！
//        String tableName = "Water_bill";
//        if (admin.tableExists(TableName.valueOf(tableName))){
//            return;
//        }
//        //6.创建表 创建构建器
//        TableDescriptorBuilder descriptorBuilder = TableDescriptorBuilder.newBuilder(TableName.valueOf(tableName));
//        //7.创建表 列簇构建起
//        String familyName = "C1";
//        ColumnFamilyDescriptorBuilder columnFamilyDescriptorBuilder = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(familyName));
//
//        ColumnFamilyDescriptor columnFamilyDescriptor = columnFamilyDescriptorBuilder.build();
//        descriptorBuilder.setColumnFamily(columnFamilyDescriptor);
//        TableDescriptor build = descriptorBuilder.build();
//        admin.createTable(build);
    }
}