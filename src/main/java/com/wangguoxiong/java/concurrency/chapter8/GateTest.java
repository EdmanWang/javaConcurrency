package com.wangguoxiong.java.concurrency.chapter8;

public class GateTest {

    public static void main(String[] args) {
        final Gate gate = new Gate();

        new User("sb", "shanghai", gate).start();
        new User("hz", "hangzhou", gate).start();
        new User("bj", "beijing", gate).start();
    }
}
