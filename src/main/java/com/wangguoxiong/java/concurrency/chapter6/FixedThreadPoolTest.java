package com.wangguoxiong.java.concurrency.chapter6;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class FixedThreadPoolTest {

    public static void main(String[] args) {
        /**
         * 使用newFixedThreadPool这样的方式存在的弊端
         * 1：默认的阻塞队列长度的 ---> Integer.MAX_VALUE
         * 这样的话，也是会导致大量的请求堆积，最终导致OOM
         */
        ExecutorService threadPool = Executors.newFixedThreadPool(10);

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
