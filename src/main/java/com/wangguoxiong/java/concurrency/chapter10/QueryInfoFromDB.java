package com.wangguoxiong.java.concurrency.chapter10;

public class QueryInfoFromDB {

    public void execute(Context context) {
        context.setUserName(Thread.currentThread().getName() + "edmanwang");
    }
}
