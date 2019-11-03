package com.wangguoxiong.java.concurrency.chapter8;

// 读写锁
public class ReadWriteLock {
    // 正在读的线程数
    private static int isReadNumber = 0;
    // 将要读的线程数
    private static int willReadNumber = 0;
    // 正在写的线程数
    private static int isWriteNumber = 0;
    // 将要写的线程数
    private static int willWriteNumber = 0;
    // 更偏向写的标签
    private Boolean perferWrite;

    public ReadWriteLock(Boolean perferWrite) {
        this.perferWrite = perferWrite;
    }

    public ReadWriteLock() {
        this(true);
    }

    public synchronized void readLock() throws InterruptedException {
        willReadNumber++;
        try {
            while (isWriteNumber > 0 || (perferWrite && willWriteNumber > 0)) {
                this.wait();
            }
            isReadNumber++;
        } finally {
            willReadNumber--;
        }
    }

    public synchronized void readUnLock() {
        isReadNumber--;
        this.notifyAll();
    }

    public synchronized void writeLock() throws InterruptedException {
        willWriteNumber++;
        try {
            while (isWriteNumber > 0 || isReadNumber > 0) {
                this.wait();
            }
            isWriteNumber++;
        } finally {
            willWriteNumber--;
        }
    }

    public synchronized void writeUnLock() {
        isWriteNumber--;
        this.notifyAll();
    }
}
