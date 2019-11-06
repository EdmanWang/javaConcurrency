package com.wangguoxiong.java.concurrency.chapter9.guarded_suspension_pattern;

import java.util.Random;

public class ClientThread extends Thread {

    private final RequestQueue requestQueue;

    private final Random random;

    private final String sendValue;

    public ClientThread(RequestQueue requestQueue, String sendValue) {
        this.requestQueue = requestQueue;
        this.random = new Random();
        this.sendValue = sendValue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("client send value ----> " + (sendValue + i));
            requestQueue.putRequest(new Request(sendValue + i));
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
