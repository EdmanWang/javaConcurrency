package com.wangguoxiong.java.concurrency.chapter5;

import java.util.Collection;

public interface Lock {

    class TimeOutExecption extends Exception {
        public TimeOutExecption(String message) {
            super(message);
        }
    }

    void lock() throws InterruptedException;

    void lock(long millis) throws InterruptedException, TimeOutExecption;

    void unLock();

    Collection<Thread> getBlockThreads();

    int getBlockThreadSize();

}
