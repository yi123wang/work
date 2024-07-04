package Hbase;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

public class rt {
    private static Configuration conf = HBaseConfiguration.create();
    private static final TableName tableName = TableName.valueOf("user_info");

    // 1. 用户注册信息存储
    public static void registerUser(String rowKey, String name, int age, String address, String phone, String gender,
                                    String account, String password, String cardNumber, double balance) throws IOException {
        try (Connection conn = ConnectionFactory.createConnection(conf)) {
            try (Table table = conn.getTable(tableName)) {
                // basicInfo 列簇
                Put basicInfoPut = new Put(Bytes.toBytes(rowKey));
                basicInfoPut.addColumn(Bytes.toBytes("basicInfo"), Bytes.toBytes("name"), Bytes.toBytes(name));
                basicInfoPut.addColumn(Bytes.toBytes("basicInfo"), Bytes.toBytes("age"), Bytes.toBytes(age));
                basicInfoPut.addColumn(Bytes.toBytes("basicInfo"), Bytes.toBytes("address"), Bytes.toBytes(address));
                basicInfoPut.addColumn(Bytes.toBytes("basicInfo"), Bytes.toBytes("phone"), Bytes.toBytes(phone));
                basicInfoPut.addColumn(Bytes.toBytes("basicInfo"), Bytes.toBytes("gender"), Bytes.toBytes(gender));

                // userInfo 列簇
                Put userInfoPut = new Put(Bytes.toBytes(rowKey));
                userInfoPut.addColumn(Bytes.toBytes("userInfo"), Bytes.toBytes("account"), Bytes.toBytes(account));
                userInfoPut.addColumn(Bytes.toBytes("userInfo"), Bytes.toBytes("password"), Bytes.toBytes(password));
                userInfoPut.addColumn(Bytes.toBytes("userInfo"), Bytes.toBytes("cardNumber"), Bytes.toBytes(cardNumber));
                userInfoPut.addColumn(Bytes.toBytes("userInfo"), Bytes.toBytes("balance"), Bytes.toBytes(balance));

                table.put(basicInfoPut);
                table.put(userInfoPut);
            }
        }
    }

    // 2. 通过rowKey查询用户信息
    public static void getUserInfo(String rowKey) throws IOException {
        try (Connection conn = ConnectionFactory.createConnection(conf)) {
            try (Table table = conn.getTable(tableName)) {
                Get get = new Get(Bytes.toBytes(rowKey));
                Result result = table.get(get);
                if (!result.isEmpty()) {
                    System.out.println("基本信息：");
                    System.out.println("姓名：" + Bytes.toString(result.getValue(Bytes.toBytes("basicInfo"), Bytes.toBytes("name"))));
                    System.out.println("年龄：" + Bytes.toInt(result.getValue(Bytes.toBytes("basicInfo"), Bytes.toBytes("age"))));
                    System.out.println("地址：" + Bytes.toString(result.getValue(Bytes.toBytes("basicInfo"), Bytes.toBytes("address"))));
                    System.out.println("电话：" + Bytes.toString(result.getValue(Bytes.toBytes("basicInfo"), Bytes.toBytes("phone"))));
                    System.out.println("性别：" + Bytes.toString(result.getValue(Bytes.toBytes("basicInfo"), Bytes.toBytes("gender"))));

                    System.out.println("用户信息：");
                    System.out.println("账号：" + Bytes.toString(result.getValue(Bytes.toBytes("userInfo"), Bytes.toBytes("account"))));
                    System.out.println("密码：" + Bytes.toString(result.getValue(Bytes.toBytes("userInfo"), Bytes.toBytes("password"))));
                    System.out.println("卡号：" + Bytes.toString(result.getValue(Bytes.toBytes("userInfo"), Bytes.toBytes("cardNumber"))));
                    System.out.println("余额：" + Bytes.toDouble(result.getValue(Bytes.toBytes("userInfo"), Bytes.toBytes("balance"))));
                } else {
                    System.out.println("用户不存在");
                }
            }
        }
    }

    // 3. 通过rowKey删除用户信息
    public static void deleteUser(String rowKey) throws IOException {
        try (Connection conn = ConnectionFactory.createConnection(conf)) {
            try (Table table = conn.getTable(tableName)) {
                Delete delete = new Delete(Bytes.toBytes(rowKey));
                table.delete(delete);
                System.out.println("用户信息删除成功");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // 测试注册用户
        registerUser("1001", "张三", 25, "北京市", "13812345678", "男", "zhangsan", "123456", "1234567890123456", 1000.0);

        // 测试查询用户信息
        getUserInfo("1001");

        // 测试删除用户信息
        deleteUser("1001");
    }
}
