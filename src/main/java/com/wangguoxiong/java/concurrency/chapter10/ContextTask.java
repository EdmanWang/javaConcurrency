package com.wangguoxiong.java.concurrency.chapter10;

public class ContextTask implements Runnable {

    private QueryInfoFromDB queryInfoFromDB = new QueryInfoFromDB();

    private QueryInfoFormHttpAPI queryInfoFormHttpAPI = new QueryInfoFormHttpAPI();

    private Context context = new Context();

    @Override
    public void run() {
        queryInfoFromDB.execute(context);
        queryInfoFormHttpAPI.execute(context);
        System.out.println("user name --> " + context.getUserName() + "user age ====>" + context.getAge());
    }
}
