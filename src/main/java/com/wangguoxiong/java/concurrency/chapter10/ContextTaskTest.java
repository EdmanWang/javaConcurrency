package com.wangguoxiong.java.concurrency.chapter10;

import java.util.stream.IntStream;

public class ContextTaskTest {

    public static void main(String[] args) {
        IntStream.rangeClosed(1,5).forEach(i->{
            new Thread(new ContextTask()).start();
        });
    }
}
