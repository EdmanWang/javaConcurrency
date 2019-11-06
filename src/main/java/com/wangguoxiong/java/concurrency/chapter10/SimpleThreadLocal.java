package com.wangguoxiong.java.concurrency.chapter10;

public class SimpleThreadLocal {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        threadLocal.set("edmanwang");
        System.out.println(threadLocal.get());
    }
}
