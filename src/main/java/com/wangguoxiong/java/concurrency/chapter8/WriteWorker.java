package com.wangguoxiong.java.concurrency.chapter8;

public class WriteWorker extends Thread {

    private ShareData shareData;
    private String str;

    public WriteWorker(ShareData shareData, String str) {
        this.shareData = shareData;
        this.str = str;
    }

    @Override
    public void run() {
        try {
            while (true) {
                shareData.write(str);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
