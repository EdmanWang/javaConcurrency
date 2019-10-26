package com.wangguoxiong.java.concurrency.chapter2;

// 守护线程
public class DaemonThread {

    static class CustomerDaemonThread implements Runnable{
        @Override
        public void run() {
            while (true){
                System.out.println("我是守护线程，我在运行");
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new CustomerDaemonThread(),"customer thread");
        // 设置线程为守护线程必须要在线程调用start方法之前
        t1.setDaemon(true);
        t1.start();

        System.out.println(t1.getThreadGroup().getName());
        try {
            Thread.sleep(15_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main 线程执行完毕");
    }
}
