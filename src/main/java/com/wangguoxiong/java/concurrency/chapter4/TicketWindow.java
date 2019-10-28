package com.wangguoxiong.java.concurrency.chapter4;

public class TicketWindow implements Runnable {

    private static final int MAX_TICKET = 500;

    private static int index = 0;

    private String threadName;

    private final Object LOCK = new Object();

    @Override
    public  void run() {
        while (true) {
            synchronized (LOCK){
                if (index >= MAX_TICKET) {
                    break;
                }
                index++;
                System.out.println("当前线程是" + Thread.currentThread().getName() + "当前叫到的号码是" + index);
            }
        }
    }
}
