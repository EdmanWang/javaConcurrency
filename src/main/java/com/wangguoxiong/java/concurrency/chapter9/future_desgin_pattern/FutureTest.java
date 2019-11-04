package com.wangguoxiong.java.concurrency.chapter9.future_desgin_pattern;

public class FutureTest {

    public static void main(String[] args) throws InterruptedException {
        FutureService futureService = new FutureService();
        Future<String> submit = futureService.submit(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "FINISH";
        }, System.out::println);

        // 继续去做其他的事情
        for (int i = 0; i < 100; i++) {
            System.out.println(i);
        }
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
