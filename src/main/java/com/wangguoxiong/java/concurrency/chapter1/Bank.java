package com.wangguoxiong.java.concurrency.chapter1;

public class Bank {

    public static void main(String[] args) {
        TicketWindow t1 = new TicketWindow("1");
        TicketWindow t2 = new TicketWindow("2");
        TicketWindow t3 = new TicketWindow("3");

        t1.start();
        t2.start();
        t3.start();
}
}
