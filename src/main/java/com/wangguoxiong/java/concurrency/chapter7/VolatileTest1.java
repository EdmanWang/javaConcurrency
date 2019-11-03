package com.wangguoxiong.java.concurrency.chapter7;

/**
 * volatile 关键字能保证可见性，有序性
 * 但是不能保证原子性
 */
public class VolatileTest1 {

    private static int initValue = 0;

    private static final int MAX_VALUE = 5;

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int localValue = initValue;
                while (localValue < MAX_VALUE) {
                    System.out.println(Thread.currentThread().getName() + "-->initValue will update to " + (++initValue));
                    localValue = initValue;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, "write").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                int localValue = initValue;
                while (localValue < MAX_VALUE) {
                    if (localValue != initValue) {
                        System.out.println(Thread.currentThread().getName() + "===>initValue update to " + initValue);
                        localValue = initValue;
                    }
                }
            }
        }, "read").start();
    }
}
