package Day_02_MapreduceTest;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordcountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    private final static IntWritable one = new IntWritable(1);
    private Text ip = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] parts = value.toString().split(" ");
        if (parts.length > 0) {
            ip.set(parts[0]);
            context.write(ip, one);
        }
    }
}



//package Day_02_MapreduceTest;
//
//import java.io.IOException;
//import org.apache.hadoop.io.IntWritable;
//import org.apache.hadoop.io.LongWritable;
//import org.apache.hadoop.io.Text;
//import org.apache.hadoop.mapreduce.Mapper;
////继承的Mapper类型选择新版本API：
////        4. 编写Reducer类
//public class WordcountMapper extends Mapper<LongWritable, Text, Text,
//        IntWritable>{
//
//    Text k = new Text();
//    IntWritable v = new IntWritable(1);
//
//    @Override
//    protected void map(LongWritable key, Text value, Context context) throws
//            IOException, InterruptedException {
//
//        // 1 获取⼀⾏
//        String line = value.toString();
//
//        // 2 切割
//        String[] words = line.split(" ");
//
//        // 3 输出
//        for (String word : words) {
//
//            k.set(word);
//            context.write(k, v);
//        }
//    }
//}
