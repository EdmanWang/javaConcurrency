package com.wangguoxiong.java.concurrency.chapter8;

public class ReadWriteLockTest {

    public static void main(String[] args) {
        ShareData shareData = new ShareData(10);
        new ReadWorker(shareData).start();
        new ReadWorker(shareData).start();
        new ReadWorker(shareData).start();
        new ReadWorker(shareData).start();

        new WriteWorker(shareData, "wgx").start();
        new WriteWorker(shareData, "WGXJ").start();

    }
}
