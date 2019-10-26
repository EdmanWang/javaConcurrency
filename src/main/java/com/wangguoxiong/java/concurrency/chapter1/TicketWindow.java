package com.wangguoxiong.java.concurrency.chapter1;

public class TicketWindow extends Thread {

    private static final int MAX_TICKET = 500;

    private static int index = 0;

    private String threadName;

    public TicketWindow(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public void run() {
        while (index <= MAX_TICKET) {
            index++;
            System.out.println("窗口名为" + threadName + "现在叫到的号是--->" + index);
        }
    }
}
