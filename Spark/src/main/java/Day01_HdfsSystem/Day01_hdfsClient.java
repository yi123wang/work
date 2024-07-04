package Day01_HdfsSystem;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


public class Day01_hdfsClient {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        //1.创建客户端对象
        Configuration conf= new Configuration();
        //2.操作hdfs文件系统
        FileSystem fs= FileSystem.get(new URI("hdfs://master:9000"),conf,"root");
        //3.创建文件
        boolean isSucces =fs.mkdirs(new Path("/Big"));
        //4.上传文件
//        fs.copyFromLocalFile(new Path("D:\\U1.txt"),new Path("/BigFil"));
        //5.下载文件
        //fs.copyToLocalFile(new Path("/BigFil"),new Path("D:\\Download"));
        //6.实现代码hdfs列表显示
//        try {
//            FileSystem fs1 = FileSystem.get(conf);
//            FileStatus[] fileStatus = fs.listStatus(new Path("/BigFil"));
//            for (FileStatus status : fileStatus) {
//                System.out.println(status.getPath().getName());
//            }a
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //7.删除文件
//        fs.delete(new Path("/BigFil"));
        //8.实现代码的移动
        //fs.rename(new Path("/BigFil"),new Path("/date"));
        //9.实现代码的重命名
        //fs.rename(new Path("/date/BigFil"),new Path("/date/Big"));
        //10.实现代码关闭HDFS
        //fs.close();

    }
}
