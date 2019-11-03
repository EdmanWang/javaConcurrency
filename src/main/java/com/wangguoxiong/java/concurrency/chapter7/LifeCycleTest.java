package com.wangguoxiong.java.concurrency.chapter7;

import java.util.Arrays;

public class LifeCycleTest {

    public static void main(String[] args) {
        LifeCycleObserver lifeCycleObserver = new LifeCycleObserver();
        lifeCycleObserver.concurrencyQuery(Arrays.asList("1","2"));
    }
}
