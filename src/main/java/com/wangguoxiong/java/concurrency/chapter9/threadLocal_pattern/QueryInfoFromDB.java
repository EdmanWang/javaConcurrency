package com.wangguoxiong.java.concurrency.chapter9.threadLocal_pattern;

public class QueryInfoFromDB {

    public void execute() {
        ActionContext.getInstance().get().setName(Thread.currentThread().getName() + " edmanwang");
    }
}
