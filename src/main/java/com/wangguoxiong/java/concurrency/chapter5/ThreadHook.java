package com.wangguoxiong.java.concurrency.chapter5;

public class ThreadHook {

    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            closeSomething();
        }));

        doSomething();
    }

    public static void doSomething() {
        int i = 0;

        while (true) {
            try {
                Thread.sleep(500);
                i++;
                System.out.println("i am working......." + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (i > 10) {
                throw new RuntimeException();
            }
        }
    }

    public static void closeSomething() {
        System.out.println("开始关闭数据库连接，文件读取，网络连接");
        try {
            Thread.sleep(5_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("全部资源关闭完毕");
    }
}
