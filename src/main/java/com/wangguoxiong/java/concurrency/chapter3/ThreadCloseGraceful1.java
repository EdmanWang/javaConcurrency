package com.wangguoxiong.java.concurrency.chapter3;

// 优雅的关闭线程1
public class ThreadCloseGraceful1 {

    private static Boolean flag = false;

    static class Worker extends Thread {

        @Override
        public void run() {
            while (true) {
                System.out.println("我在处理业务");
                try {
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (flag) {
                    System.out.println("执行线程需要中断");
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Worker worker = new Worker();
        worker.start();

        try {
            Thread.sleep(10_000);
            flag = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main 等待结束");
    }
}
