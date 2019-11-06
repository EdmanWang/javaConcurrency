package com.wangguoxiong.java.concurrency.chapter9.guarded_suspension_pattern;

import java.util.Random;

public class ServerThread extends Thread {

    private RequestQueue requestQueue;

    private volatile Boolean flag = true;

    private Random random;

    public ServerThread(RequestQueue requestQueue) {
        this.requestQueue = requestQueue;
        this.random = new Random();
    }

    @Override
    public void run() {
        while (flag) {
            Request request = requestQueue.getRequest();
            if (request == null) {
                System.out.println("server receive is null");
                continue;
            }
            System.out.println("server receive value ----> " + request.getValue());
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public void close() {
        this.flag = false;
        this.interrupt();
    }
}
