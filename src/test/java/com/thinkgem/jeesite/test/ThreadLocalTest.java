package com.thinkgem.jeesite.test;

import java.util.Random;

/**
 * Created by zhongyi on 2018/1/11 0011.
 */
public class ThreadLocalTest {

    private static ThreadLocal<Integer> x = new ThreadLocal<Integer>();

    private static ThreadLocal<MyThreadScopeData> myThreadScopeDataThreadLocal = new ThreadLocal<MyThreadScopeData>();

    public static void main(String[] args) {

        for(int i=0 ; i < 5 ; i++){
            new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            int data = new Random().nextInt();
                            System.out.println(Thread.currentThread().getName() +"has put data:"+data);
                            x.set(data);
                            MyThreadScopeData myThreadScopeData = new MyThreadScopeData();
                            myThreadScopeData.setName("zyares" + data);
                            myThreadScopeData.setAge(28);
                            myThreadScopeDataThreadLocal.set(myThreadScopeData);
                            new A().get();
                            new B().get();
                        }
                    }
            ).start();
        }

    }

    static class A {
        public void get(){
            int data = x.get();
            MyThreadScopeData myThreadScopeData = myThreadScopeDataThreadLocal.get();
            System.out.println("A from"+Thread.currentThread().getName()+" get data :"+myThreadScopeData.getName()+
                    ",age:"+myThreadScopeData.getAge());
        }
    }

    static class B {
        public void get(){
            int data = x.get();
            MyThreadScopeData myThreadScopeData = myThreadScopeDataThreadLocal.get();
            System.out.println("A from"+Thread.currentThread().getName()+" get data :"+myThreadScopeData.getName()+
                    ",age:"+myThreadScopeData.getAge());
        }
    }
}
