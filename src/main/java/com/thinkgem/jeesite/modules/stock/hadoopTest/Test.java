package com.thinkgem.jeesite.modules.stock.hadoopTest;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

/**
 * Created by zhongyi on 2017/10/12 0012.
 */
public class Test {

    public static void main( String[] args )
    {
        FileSystem fs = null;
        BufferedReader br = null;
        FSDataOutputStream os = null;
        try {
            Configuration conf = new Configuration();

            // 不设置该代码会出现错误：java.io.IOException: No FileSystem for scheme: hdfs
            conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");

            String filePath = "hdfs://192.168.2.248:9000//user/hadoop/input/20140527.txt";
            Path path = new Path(filePath);

            // 这里需要设置URI，否则出现错误：java.lang.IllegalArgumentException: Wrong FS: hdfs://127.0.0.1:9000/test/test.txt, expected: file:///
            fs = FileSystem.get(new URI(filePath), conf);

            System.out.println( "READING ============================" );
            FSDataInputStream is = fs.open(path);
            br = new BufferedReader(new InputStreamReader(is));
            // 示例仅读取一行
            String content = "";
            int i= 0;
            while ((content = br.readLine()) != null) {
                System.out.println(content);
                if(i == 10 ){
                    break;
                }
                i++;
            }




//            System.out.println("WRITING ============================");
//            byte[] buff = "this is helloworld from java api!\n".getBytes();
//            os = fs.create(path);
//            os.write(buff, 0, buff.length);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                br.close();
//                os.close();
                fs.close();
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
