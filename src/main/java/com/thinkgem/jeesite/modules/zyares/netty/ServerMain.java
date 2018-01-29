package com.thinkgem.jeesite.modules.zyares.netty;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zhongyi on 2018/1/29 0029.
 */
public class ServerMain {

    public static volatile boolean running = true;

    public static void main(String[] args) {

       final ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:app");

        try {
            context.start();

            Runtime.getRuntime().addShutdownHook( new Thread(){
                @Override
                public void run() {
                    context.stop();
                    running = false;
                    ServerMain.class.notify();
                }
            });

            synchronized (ServerMain.class){
                while (running){
                    ServerMain.class.wait();
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            System.exit(0);
        }
    }



}
