package com.wangguoxiong.java.concurrency.chapter1;

// 使用runnable 接口的方式来创建线程
public class ThreadCreate2 {

    private static final Object lock = new Object();

    static class Task implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                    try {
                        System.out.println("我在处理" + Thread.currentThread().getName() + "业务逻辑");
                        Thread.sleep(15_000);
                        System.out.println("我已经处理" + Thread.currentThread().getName() + "完了相关的业务逻辑");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Task());
        Thread t2 = new Thread(new Task());

        t1.start();
        t2.start();

        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
