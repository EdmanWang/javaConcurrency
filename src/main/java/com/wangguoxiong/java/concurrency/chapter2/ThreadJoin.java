package com.wangguoxiong.java.concurrency.chapter2;

// 线程join方法
public class ThreadJoin {

    static class CustomerThread implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println("我在处理业务逻辑");
                Thread.sleep(25_000);
                System.out.println("业务逻辑处理完毕");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
           }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new CustomerThread());
        t1.start();

        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("t1线程执行完毕");
    }
}
