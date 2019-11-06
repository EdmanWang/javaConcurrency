package com.wangguoxiong.java.concurrency.chapter9.threadLocal_pattern;

public class QueryInfoFormHttpAPI {

    public void execute() {
        ActionContext.getInstance().get().setSalary(Thread.currentThread().getId());
    }
}
