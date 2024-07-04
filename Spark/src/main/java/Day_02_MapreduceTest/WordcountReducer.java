package Day_02_MapreduceTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WordcountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

    private TreeMap<Integer, String> topTenIPs = new TreeMap<>();

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int sum = 0;
        for (IntWritable value : values) {
            sum += value.get();
        }
        topTenIPs.put(sum, key.toString());
        if (topTenIPs.size() > 10) {
            topTenIPs.remove(topTenIPs.firstKey());
        }
    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        for (Map.Entry<Integer, String> entry : topTenIPs.descendingMap().entrySet()) {
            context.write(new Text(entry.getValue()), new IntWritable(entry.getKey()));
        }
    }
}






//package Day_02_MapreduceTest;
//
//import java.io.IOException;
//import org.apache.hadoop.io.IntWritable;
//import org.apache.hadoop.io.Text;
//import org.apache.hadoop.mapreduce.Reducer;
//public class WordcountReducer extends Reducer<Text, IntWritable, Text,
//        IntWritable>{
//    int sum;
//    IntWritable v = new IntWritable();
//    @Override
//    protected void reduce(Text key, Iterable<IntWritable> values,Context
//            context) throws IOException, InterruptedException {
//
////        选择继承的Reducer类
////        5. 编写Driver驱动类
//                // 1 累加求和
//                sum = 0;
//        for (IntWritable count : values) {
//            sum += count.get();
//        }
//
//        // 2 输出
//        v.set(sum);
//        context.write(key,v);
//    }
//}