package com.thinkgem.jeesite.modules.stock.hadoopTest;

import java.io.*;

/**
 * Created by zhongyi on 2017/10/12 0012.
 */
public class FileTest {

    public static void main(String[] args) {
        readByBufferedReader("F://20140527.txt");
    }

    public static void readByBufferedReader(String fileName) {
        try {
            File file = new File(fileName);
            // 读取文件，并且以utf-8的形式写出去
            BufferedReader bufread;
            String read;
            bufread = new BufferedReader(new FileReader(file));
            int i = 0;
            while ((read = bufread.readLine()) != null) {
                System.out.println(read);
                if(i == 10){
                    break;
                }
                i++;
            }
            bufread.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
