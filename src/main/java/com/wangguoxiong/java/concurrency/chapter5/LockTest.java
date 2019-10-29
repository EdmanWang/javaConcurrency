package com.wangguoxiong.java.concurrency.chapter5;

import java.util.stream.Stream;

public class LockTest {

    public static void main(String[] args) {
        final BooleanLock booleanLock = new BooleanLock();

        Stream.of("T1", "T2", "T3", "T4").forEach(name -> {
            new Thread(() -> {
                try {
                    try {
                        booleanLock.lock(2_000L);
                        worker();
                    } catch (Lock.TimeOutExecption timeOutExecption) {
                        System.out.println(Thread.currentThread().getName() + "超时");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    booleanLock.unLock();
                }
            }, name).start();
        });
    }

    public static void worker() {
        System.out.println("当前线程号" + Thread.currentThread().getName() + "正在工作");
        try {
            Thread.sleep(6_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
