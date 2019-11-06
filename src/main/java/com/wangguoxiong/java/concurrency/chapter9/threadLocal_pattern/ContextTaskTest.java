package com.wangguoxiong.java.concurrency.chapter9.threadLocal_pattern;


import java.util.stream.IntStream;

public class ContextTaskTest {

    public static void main(String[] args) {
        IntStream.rangeClosed(1,20).forEach(i->{
            new Thread(new ContextTask()).start();
        });
    }
}
