package com.wangguoxiong.java.concurrency.chapter5;

import java.util.Optional;

/**
 * 1：wait方法是object的方法，不是线程私有的，而sleep方法是线程私有的
 * 2：使用wait方法需要显示的创建一个锁对象，而使用sleep不需要
 * 3：使用wait方法会释放该线程拥有的锁资源，而sleep不会释放锁资源
 * 4：使用wait方法需要改对象锁唤醒，不包括wait(long millis) ,而sleep方法是不需要唤醒的
 */
public class DifferenceOfWaitAndSleep {

    public static final Object LOCK = new Object();

    public static void main(String[] args) {

        final DifferenceOfWaitAndSleep difference = new DifferenceOfWaitAndSleep();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (LOCK) {
                        difference.m1();
                        try {
                            LOCK.wait();
//                            Thread.sleep(10_000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "T1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (LOCK) {
                        try {
                            Thread.sleep(5_000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        difference.m2();
                    }
                }
            }
        }, "T2").start();
    }

    public void m1() {
        Optional.of("当前线程是【" + Thread.currentThread().getName() + "】-----> m1").ifPresent(System.out::println);
    }

    public void m2() {
        Optional.of("当前线程是【" + Thread.currentThread().getName() + "】-----> m2").ifPresent(System.out::println);
    }
}
