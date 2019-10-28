package com.wangguoxiong.java.concurrency.chapter4;

public class LockService2 {

    private final Object Lock2 = new Object();

    private LockService1 lockService1;

    public LockService2(LockService1 lockService1) {
        this.lockService1 = lockService1;
    }

    public void lockService2Method() {
        synchronized (Lock2){
            lockService1.lockService1RunMethod();
        }
    }


    public void lockService2RunMethod() {
        synchronized (Lock2){
            System.out.println("lockService2RunMethod----running");
        }
    }
}
