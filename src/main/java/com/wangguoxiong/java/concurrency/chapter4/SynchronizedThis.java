package com.wangguoxiong.java.concurrency.chapter4;

public class SynchronizedThis {

    static class CustomerClass {
        public synchronized  void m1() {
            try {
                Thread.sleep(20_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "---- m1");
        }

        public synchronized void m2() {
            System.out.println(Thread.currentThread().getName() + "++++ m2");
        }
    }

    public static void main(String[] args) {
        CustomerClass customerClass = new CustomerClass();

        new Thread(new Runnable() {
            @Override
            public void run() {
                customerClass.m1();
            }
        }, "m1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                customerClass.m2();
            }
        }, "m2").start();
    }
}
