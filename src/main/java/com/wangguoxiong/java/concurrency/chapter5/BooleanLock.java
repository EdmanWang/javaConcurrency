package com.wangguoxiong.java.concurrency.chapter5;

import com.sun.org.apache.regexp.internal.RE;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public class BooleanLock implements Lock {

    private Boolean initVlaue;

    private Collection<Thread> collection = new ArrayList<>();

    private Thread currentThread;

    public BooleanLock() {
        this.initVlaue = false;
    }

    @Override
    public synchronized void lock() throws InterruptedException {
        /**
         * 三个线程并发去争夺cpu执行权，不管是哪一个拿到了，我都去判断一下，是否在你之前已经有人拿到了。如果有人在你之前拿到了，你就乖乖的再等着
         */
        while (initVlaue) {
            // 四个线程将在这里阻塞  **需要明白在哪里阻塞的**
            this.wait();
            collection.add(Thread.currentThread());
        }
        initVlaue = true;
        currentThread = Thread.currentThread();
        collection.remove(Thread.currentThread());
    }

    @Override
    public synchronized void lock(long millis) throws InterruptedException, TimeOutExecption {
        if (millis < 0) {
            lock();
        }
        long hasRemaining = millis;
        long end = System.currentTimeMillis() + millis;

        while (initVlaue) {
            if (hasRemaining < 0) {
                throw new TimeOutExecption("time out");
            }
            collection.add(Thread.currentThread());
            this.wait(millis);
            hasRemaining = end - System.currentTimeMillis();
        }
        initVlaue = true;
        currentThread = Thread.currentThread();
    }

    @Override
    public synchronized void unLock() {
        if (currentThread == Thread.currentThread()) {
            Optional.of("线程【" + Thread.currentThread().getName() + "】释放锁资源").ifPresent(System.out::println);
            initVlaue = false;
            this.notifyAll();
        }
    }

    @Override
    public Collection<Thread> getBlockThreads() {
        return Collections.unmodifiableCollection(collection);
    }

    @Override
    public int getBlockThreadSize() {
        return collection.size();
    }
}
