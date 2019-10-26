package com.wangguoxiong.java.concurrency.chapter1;

// 使用thread创建线程的几种构造函数
public class ThreadCreate3 {

    public static void main(String[] args) {
        /**
         * 1：当线程对象t1没有重写run方法，线程不会与任何的执行动作
         * 2：当线程对象t1没有指定当前线程的线程组，则会拿到父类的线程组【main】
         */
        Thread t1 = new Thread();

        Thread t2 = new Thread(()->{});

        Thread t3 = new Thread(new ThreadGroup("customer group 4"), ()->{});

        Thread t4 = new Thread("thread - 4");

        Thread t5 = new Thread(new ThreadGroup("customer group 5"),"thread - 5");

        Thread t6 = new Thread(()->{},"thread - 6");

        Thread t7 = new Thread(new ThreadGroup("customer group 7"), ()->{},"thread - 7");

        Thread t8 = new Thread(new ThreadGroup("customer group 8"), ()->{},"thread - 7",1024);
    }
}
