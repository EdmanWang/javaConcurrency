package com.wangguoxiong.java.concurrency.chapter4;

import java.util.stream.Stream;

public class SynchronizedThis {

    static class CustomerClass {
        public synchronized void m1() {
            try {
                Thread.sleep(20_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "---- m1");
        }

        /**
         * 当synchronized 同步的是方法的时候，在没有加wait方法的时候，同步的方法头
         * 当加上了wait方法的时候，同步在wai方法的地方
         */
        public synchronized void m2() {
            System.out.println(Thread.currentThread().getName() + "++++ m2");

            try {
                if (Thread.currentThread().getName().equals("T1")){
                    this.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(20_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        CustomerClass customerClass = new CustomerClass();

        Stream.of("T1", "T2").forEach(threadName -> {
            new Thread(() -> {
                customerClass.m2();
            }, threadName).start();
        });
    }
}
