package sui_ji_sheng_cheng_10w;

import cn.binarywang.tools.generator.ChineseAddressGenerator;
import cn.binarywang.tools.generator.ChineseNameGenerator;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ooo {
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
            return "表创建失败";
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
    public static void scanTable(String tableName) throws IOException {
        //1.获取表对象
        Table table = conn.getTable(TableName.valueOf(tableName));
        //2.创建扫描器
        Scan scan = new Scan();
        //3.扫描数据
        ResultScanner resultScanner = table.getScanner(scan);
        for (Result result:resultScanner) {
            System.out.println(result.toString());
        }
    }

    //4.插入数据 插入单列 多列呢？
    public static void putData(String tableName) throws IOException {
        Table table = conn.getTable(TableName.valueOf(tableName));
        String rowKey = "0001";
        String columnFamily = "C1";
        String colName = "name";
        Put put = new Put(Bytes.toBytes(rowKey));
        put.addColumn(
                Bytes.toBytes(columnFamily),
                Bytes.toBytes(colName),
                Bytes.toBytes("张三"));
        table.put(put);
        table.close();

    }
    //5.查看所有数据并转中文



    //如何插入多个列？
    public static void putDatas(String tableName, String rowKey,String columnFamily, String[] colName, String[] value) throws IOException {
        Table table = conn.getTable(TableName.valueOf(tableName));
        Put put = new Put(Bytes.toBytes(rowKey));
        for (int i = 0; i < colName.length; i++) {
            put.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(colName[i]), Bytes.toBytes(value[i]));
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

    //1.删除表
    public static void deleteTable(String tableName) throws IOException {
        admin.disableTable(TableName.valueOf(tableName));
        admin.deleteTable(TableName.valueOf(tableName));
    }
    //实战：10w条的数据 如何通过Java、hbase shell 导入代码批量插入呢？
    //Application interface
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













    public static void main(String[] args) throws IOException {

//        System.out.println(name + ":" + age + ":" + sex + ":" + address + ":" + salary);
        // 循环一万次 插入数据 emp 表
        for (int i = 0; i < 10000; i++) {
            String name = ChineseNameGenerator.getInstance().generate();
            //生成随机年龄 20 ~50
            int age = (int) (Math.random() * 30 + 20);
            //生成随机性别
            String sex = (int) (Math.random() * 2) % 2 == 0 ? "男" : "女";
            String address = ChineseAddressGenerator.getInstance().generate();
            //生成随机工资
            int salary = (int) (Math.random() * 10000);
            String arr = "c1";
            String[] cloNames = new String[]{"name","age","sex","address","salary"};
            //生成随机姓名 随机年龄  随机性别  随机地址  随机工资
            String[] cloValues = new String[]{name,String.valueOf(age),sex,address,String.valueOf(salary)};
            putDatas("emp","000"+i ,arr,cloNames,cloValues);
        }
////


//        deleteTable("Water_bill");
//        createTable("Water_bill", new String[]{"C1"});
        scanTables("emp");
//        String[] arr = new String[]{"C1"};
//        String[] cloNames = new String[]{"name","age","sex","address"};
//        String[] cloValues = new String[]{"张三","18","男","北京市"};
//        putDatas("Water_bill","0002",arr,cloNames,cloValues);
//        scanTable("Water_bill");
    }
}
