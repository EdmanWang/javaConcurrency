package com.wangguoxiong.java.concurrency.chapter9.threadLocal_pattern;

public class ContextTask implements Runnable {

    private QueryInfoFromDB queryInfoFromDB = new QueryInfoFromDB();

    private QueryInfoFormHttpAPI queryInfoFormHttpAPI = new QueryInfoFormHttpAPI();


    @Override
    public void run() {
        queryInfoFromDB.execute();
        queryInfoFormHttpAPI.execute();
        System.out.println("user name --> " + ActionContext.getInstance().get().getName() + "user age ====>" + ActionContext.getInstance().get().getSalary());
    }
}
