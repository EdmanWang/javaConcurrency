package com.wangguoxiong.java.concurrency.chapter6;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * 线程池api测试
 */
public class ThreadPoolExecuterTest {

    public static void main(String[] args) {

        /**
         * corePoolSize ; 线程池核心线程数
         * maximunPoolSize : 线程池最大线程数
         * keepAliveTime : 新创建的线程数，存活的时间
         * TimeUnit.SECONDS ： 新创建的线程数，存活的时间具体的单位
         * LinkedBlockingDeque ： 定义的阻塞队列
         */
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(1, 3, 2, TimeUnit.SECONDS, new LinkedBlockingDeque<>(3));

        IntStream.rangeClosed(1, 6).forEach(i -> {
            threadPool.execute(new Task());
        });
        threadPool.shutdown();
    }

    static class Task implements Runnable {
        @Override
        public void run() {
            System.out.println("该任务由" + Thread.currentThread().getName() + "进行服务!!!!!!!");
            try {
                Thread.sleep(2_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
