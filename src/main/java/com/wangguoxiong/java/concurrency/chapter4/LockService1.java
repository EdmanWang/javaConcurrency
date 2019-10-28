package com.wangguoxiong.java.concurrency.chapter4;

public class LockService1 {

    private final Object Lock1 = new Object();

    private LockService2 lockService2;

    public LockService2 getLockService2() {
        return lockService2;
    }

    public void setLockService2(LockService2 lockService2) {
        this.lockService2 = lockService2;
    }

    public void lockServiceMethod1(){
        synchronized (Lock1){
            lockService2.lockService2RunMethod();
        }
    }

    public void lockService1RunMethod() {
        synchronized (Lock1){
            System.out.println("lockService1RunMethod----running");
        }
    }
}
