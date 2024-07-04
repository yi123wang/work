package Hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


public class HbaseClient_2 {
    private static Configuration conf;
    private static Connection conn;
    private static Admin admin;

    static {
        try {
            conf = HBaseConfiguration.create();
            conn = ConnectionFactory.createConnection(conf);
            admin = conn.getAdmin();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //1.方法用于表和列簇的创建
    public static String createTable(String tableName, String[] columnFamily) throws IOException {
        //1.判断表是否存在
        if (admin.tableExists(TableName.valueOf(tableName))) {
            //2.创建表
            return "表重复/表创建失败";
        }
        //6.创建表 创建构建器
        TableDescriptorBuilder descriptorBuilder = TableDescriptorBuilder.newBuilder(TableName.valueOf(tableName));
        //7.创建列簇
        for (int i = 0; i < columnFamily.length; i++) {
            descriptorBuilder.setColumnFamily(ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(columnFamily[i])).build());
        }
        TableDescriptor descriptor = descriptorBuilder.build();
        admin.createTable(descriptor);
        admin.close();
        return "表创建成功";
    }
    //2.查看表
    public static TableName[] listTables() throws IOException {
        TableName[] tableNames = admin.listTableNames();
        return tableNames;
    }
    //3.查看表里面的数据呢？
//    public static void scanTable(String tableName) throws IOException {
//        //1.获取表对象
//        Table table = conn.getTable(TableName.valueOf(tableName));
//        //2.创建扫描器
//        Scan scan = new Scan();
//        //3.扫描数据
//        ResultScanner resultScanner = table.getScanner(scan);
//        for (Result result:resultScanner) {
//            System.out.println(result);
//        }
//    }
    public static void scanTables(String tableName) throws IOException {
        Table table = conn.getTable(TableName.valueOf(tableName));
        Scan scan = new Scan();
        ResultScanner resultScanner = table.getScanner(scan);
        for (Result result : resultScanner) {
            System.out.println(Bytes.toString(result.getRow()) + " : ");
            for (Cell cell : result.listCells()) {
                // 获取Cell的数据，并确保使用UTF-8编码转换为字符串
                String family = new String(cell.getFamilyArray(), cell.getFamilyOffset(), cell.getFamilyLength(), StandardCharsets.UTF_8);
                String qualifier = new String(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength(), StandardCharsets.UTF_8);
                String value = new String(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength(), StandardCharsets.UTF_8);
                System.out.println(family + ":" + qualifier + ":" + value);
            }
        }
    }
    //4.插入数据（单个）
//    public static void putDatas(String tableName) throws IOException {
//        Table table = conn.getTable(TableName.valueOf(tableName));
//        String rowKey ="001";
//        String columnFamily="c1";
//        String colName ="name";
//        Put put = new Put(Bytes.toBytes(rowKey));
//        put.addColumn(
//                Bytes.toBytes(columnFamily),
//                Bytes.toBytes(colName),
//                Bytes.toBytes("张三"));
//        table.put(put);
//        table.close();
//    }
    //插入多个数据
    public static void putDatas(String tableName, String rowKey,String[] columnFamily, String[] colName, String[] value) throws IOException {
        Table table = conn.getTable(TableName.valueOf(tableName));
        Put put = new Put(Bytes.toBytes(rowKey));
        for (int i = 0; i < columnFamily.length; i++) {
            put.addColumn(
                    Bytes.toBytes(columnFamily[i]),
                    Bytes.toBytes(colName[i]),
                    Bytes.toBytes(value[i]));
        }
        table.put(put);
        table.close();
    }

    //5.查看一条数据
    public static void getData(String tableName, String rowKey) throws IOException {
        Table table = conn.getTable(TableName.valueOf(tableName));
        Get get = new Get(Bytes.toBytes(rowKey));
        Result result = table.get(get);
        System.out.println(result);
    }
    //6.删除一条数据
    public static void deleteData(String tableName, String rowKey) throws IOException {
        Table table = conn.getTable(TableName.valueOf(tableName));
        Delete delete = new Delete(Bytes.toBytes(rowKey));
        table.delete(delete);
        table.close();
    }

    //7.删除表
    public static String deleteTable(String tableName) throws IOException {
        if (!admin.tableExists(TableName.valueOf(tableName))) {
            return "表不存在";
        }
        admin.disableTable(TableName.valueOf(tableName));
        admin.deleteTable(TableName.valueOf(tableName));
        return "表删除成功";
    }




    public static void main(String[] args) throws IOException {

        //创建表
//        String tableName = "water_bill";
//        String[] columnFamilies = {"info"};
//        System.out.println(createTable(tableName, columnFamilies));

        //查看所有表名
//        TableName[] tableNames = listTables();
//        for (TableName name:tableNames) {
//            System.out.println(name);
//        }

        //查看表中数据
        scanTables("Water_Bill");
//        scanTables("emp");

        //添加数据到water_bill
//        putData("water_bill");
        //添加多个数据到表中
//        String[] arr = new String[]{"C1"};
//        String[] cloNames = new String[]{"name","age","sex","address"};
//        String[] cloValues = new String[]{"张三","18","男","北京市"};
//        putDatas("water_bill","0002",arr,cloNames,cloValues);
//        scanTables("water_bill");

        //删除表
//        deleteTable("emp");



    }
}