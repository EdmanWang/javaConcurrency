package com.wangguoxiong.java.concurrency.chapter9.future_desgin_pattern;

// 同步调用
public class SyncInvoke {

    public static void main(String[] args) {

        String result = getResult();
        System.out.println(result);

        System.out.println("done....");
    }

    public static String getResult() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "FINISH";
    }
}
