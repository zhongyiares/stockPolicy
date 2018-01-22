package com.thinkgem.jeesite.test;

import java.util.Random;

/**
 * Created by zhongyi on 2018/1/11 0011.
 */
public class ThreadLocalTest2 {

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
                            MyThreadScopeData2 myThreadScopeData2 =  MyThreadScopeData2.getInstance();
                            myThreadScopeData2.setName("zyares" + data);
                            myThreadScopeData2.setAge(28);
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
            MyThreadScopeData2 myThreadScopeData = MyThreadScopeData2.getInstance();
            System.out.println("A from"+Thread.currentThread().getName()+" get data :"+myThreadScopeData.getName()+
                    ",age:"+myThreadScopeData.getAge());
        }
    }

    static class B {
        public void get(){
            int data = x.get();
            MyThreadScopeData2 myThreadScopeData = MyThreadScopeData2.getInstance();
            System.out.println("A from"+Thread.currentThread().getName()+" get data :"+myThreadScopeData.getName()+
                    ",age:"+myThreadScopeData.getAge());
        }
    }
}

 class MyThreadScopeData2 {

//     private MyThreadScopeData2(){}

//     private static MyThreadScopeData2 instance = null;

     private static ThreadLocal<MyThreadScopeData2> map = new ThreadLocal<MyThreadScopeData2>();

     public static MyThreadScopeData2 getInstance(){
         MyThreadScopeData2 instance = map.get();
         if(instance == null){
             instance = new MyThreadScopeData2();
             map.set(instance);
         }
         return instance;
     }

    private String name;

    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
