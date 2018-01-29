package com.thinkgem.jeesite.modules.zyares.netty;

import java.nio.charset.Charset;

/**
 * Created by zhongyi on 2018/1/24 0024.
 */
public class ByteTest {

    public static void main(String[] args) {
//        String s = "张三\r\n";
//        System.out.println(s.getBytes(Charset.defaultCharset()).length);

        int shift = 512 ^ 1 << 9;
        System.out.println(shift);
        //512
        System.out.println(1<<9);

        long i=1;
        for(int j=0;j< 11 ;j++){
            i = i*2;
        }
        System.out.println(i);
    }
}
