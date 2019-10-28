package com.wangguoxiong.java.concurrency.chapter4;

public class SynchronizedStatic {

    static class CustomerThread {

        static {
            synchronized (CustomerThread.class){
                try {
                    Thread.sleep(20_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public static synchronized void m1() {
            try {
                Thread.sleep(15_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "++++ m1");
        }

        public static synchronized void m2() {
            try {
                Thread.sleep(20_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "++++ m2");
        }

        public static void m3() {
            System.out.println(Thread.currentThread().getName() + "++++ m3");
        }
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                CustomerThread.m1();
            }
        }, "m1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                CustomerThread.m2();
            }
        }, "m2").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                CustomerThread.m3();
            }
        }, "m3").start();
    }
}
