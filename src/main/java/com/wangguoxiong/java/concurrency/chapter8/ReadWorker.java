package com.wangguoxiong.java.concurrency.chapter8;

public class ReadWorker extends Thread {
    private ShareData shareData;

    public ReadWorker(ShareData shareData) {
        this.shareData = shareData;
    }

    @Override
    public void run() {
        try {
            while (true) {
                char[] read = shareData.read();
                System.out.println(Thread.currentThread().getName() + " read " + String.valueOf(read));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
