package com.wangguoxiong.java.concurrency.chapter10;

public class QueryInfoFormHttpAPI {

    public void execute(Context context) {
        String userName = context.getUserName();
        context.setAge(Thread.currentThread().getId());
    }
}
