package com.wangguoxiong.java.concurrency.chapter10;

public class ComplexThreadLocal extends ThreadLocal<String> {

    @Override
    protected String initialValue() {
        return "EDMANWANG";
    }

    public static void main(String[] args) {
        final ComplexThreadLocal complexThreadLocal = new ComplexThreadLocal();
        new Thread(new Runnable() {
            @Override
            public void run() {
                complexThreadLocal.set("123");
                System.out.println(complexThreadLocal.get());
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                complexThreadLocal.set("abc");
                System.out.println(complexThreadLocal.get());
            }
        }).start();

        System.out.println(complexThreadLocal.get());
    }
}
