package com.wangguoxiong.java.concurrency.chapter9.guarded_suspension_pattern;

public class SuspensionTest {

    public static void main(String[] args) {
        RequestQueue requestQueue = new RequestQueue();
        new ClientThread(requestQueue, "edmanwang").start();
        ServerThread serverThread = new ServerThread(requestQueue);
        serverThread.start();

        try {
            Thread.sleep(15_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        serverThread.close();
    }
}
