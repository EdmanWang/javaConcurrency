package com.wangguoxiong.java.concurrency.chapter3;

// 优雅的关闭线程1
public class ThreadCloseGraceful2 {

    static class Worker implements Runnable {

        @Override
        public void run() {
            while (true) {
                if (Thread.interrupted()){
                    System.out.println("线程被打断");
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread worker = new Thread(new Worker());
        worker.start();

        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        worker.interrupt();
    }
}
