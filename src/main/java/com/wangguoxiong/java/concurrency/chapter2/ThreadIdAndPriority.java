package com.wangguoxiong.java.concurrency.chapter2;

// 线程id及其优先级
public class ThreadIdAndPriority {

    static class CustomerThread implements Runnable {
        @Override
        public void run() {
            System.out.println("当前线程id是" + Thread.currentThread().getId() + "当前线程优先级是" + Thread.currentThread().getPriority());
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new CustomerThread(), "自定义1");
        Thread t2 = new Thread(new CustomerThread(), "自定义2");

        t1.setPriority(10);
        t2.setPriority(5);

        t1.start();
        t2.start();
    }
}
