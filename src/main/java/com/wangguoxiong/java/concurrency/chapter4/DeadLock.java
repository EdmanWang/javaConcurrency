package com.wangguoxiong.java.concurrency.chapter4;

public class DeadLock {

    public static void main(String[] args) {
        LockService1 lockService1 = new LockService1();
        LockService2 LockService2 = new LockService2(lockService1);
        lockService1.setLockService2(LockService2);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    lockService1.lockServiceMethod1();
                    LockService2.lockService2Method();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    lockService1.lockServiceMethod1();
                    LockService2.lockService2Method();
                }
            }
        }).start();
    }
}
