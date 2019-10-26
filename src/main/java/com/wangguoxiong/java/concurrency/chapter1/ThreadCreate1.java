package com.wangguoxiong.java.concurrency.chapter1;

public class ThreadCreate1 extends Thread {

    @Override
    public void run() {
       while (true){

       }
    }

    public static void main(String[] args) {
        ThreadCreate1 threadCreate1 = new ThreadCreate1();
        threadCreate1.start();

        try {
            Thread.sleep(25 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main thread finish done");
    }
}
