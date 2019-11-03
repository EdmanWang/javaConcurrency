package com.wangguoxiong.java.concurrency.chapter7;

public class VolatileTest2 {

    private static volatile int index = 0;

    private static final int MAX_INDEX = 500;

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (index < MAX_INDEX) {
                    try {
                        System.out.println("线程" + Thread.currentThread().getName() + "将index的值增加到" + (++index));
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "t1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (index < MAX_INDEX) {
                    System.out.println("线程" + Thread.currentThread().getName() + "将index的值增加到" + (++index));
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "t2").start();
    }
}
