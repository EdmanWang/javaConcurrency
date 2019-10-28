package com.wangguoxiong.java.concurrency.chapter3;

// 强制关闭线程

/**
 * 应用场景：
 * 向外提供一个api接口，
 * api接口内部创建了三个线程去并发执行任务：例如文件复制
 */
public class ThreadCloseForce {

    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();

        ThreadServiceApi threadServiceApi = new ThreadServiceApi();
        threadServiceApi.execute(() -> {
//            while (true) {
//                System.out.println("我在进行文件复制工作");
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
            System.out.println("我在进行文件复制工作");
            try {
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadServiceApi.shutDown(15_000);
        long endTime = System.currentTimeMillis();
        System.out.println("总共耗费时间是" + (endTime - startTime));
    }
}
