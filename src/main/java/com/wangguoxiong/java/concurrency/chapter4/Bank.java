package com.wangguoxiong.java.concurrency.chapter4;

public class Bank {

    public static void main(String[] args) {

        final TicketWindow ticketWindow = new TicketWindow();
        Thread t1 = new Thread(ticketWindow);
        Thread t2 = new Thread(ticketWindow);
        Thread t3 = new Thread(ticketWindow);
        t1.start();
        t2.start();
        t3.start();
    }
}
