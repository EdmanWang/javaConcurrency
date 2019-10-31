package com.wangguoxiong.java.concurrency.chapter6;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * 简单的线程池实现
 */
public class SimpleThreadPool {

    private final int size;

    private static final int POOL_SIZE = 5;

    // 任务队列
    private final static LinkedList<Runnable> TASK_QUEUE = new LinkedList<>();

    // 线程池
    private final static List<CustomerThread> THREAD_POOL = new ArrayList<>();

    private final static ThreadGroup CUSTOMER_THREAD_GROUP_PREFIX = new ThreadGroup("WGX_GROUP");

    private volatile static String THREAD_INDEX_PREFIX = "customer_thread";

    public SimpleThreadPool() {
        this(POOL_SIZE);
    }

    // 创建线程池
    public SimpleThreadPool(int size) {
        this.size = size;
        // 初始化线程池
        init();
    }

    // 完成对线程池的初始化
    private void init() {
        for (int i = 0; i < size; i++) {
            createThread(CUSTOMER_THREAD_GROUP_PREFIX, THREAD_INDEX_PREFIX + "--" + i);
        }
    }

    // 使用自己封装的线程
    private void createThread(ThreadGroup group, String threadName) {
        CustomerThread customerThread = new CustomerThread(group, threadName);
        customerThread.start();
        THREAD_POOL.add(customerThread);
    }

    public void submit(Runnable task) {
        synchronized (TASK_QUEUE) {
            TASK_QUEUE.addLast(task);
            TASK_QUEUE.notifyAll();
        }
    }

    public static void main(String[] args) {
        /**
         * 1：在完成线程初始化的时候，就完成了对线程的启动
         * 2：需要监控任务队列是否有任务
         */
        SimpleThreadPool simpleThreadPool = new SimpleThreadPool();
        IntStream.rangeClosed(1, 40).forEach(i -> {
            simpleThreadPool.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("任务" + i + "由线程" + Thread.currentThread().getName() + "进行服务------------");
                    try {
                        Thread.sleep(2_000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("任务" + i + "由线程" + Thread.currentThread().getName() + "服务完成............");
                }
            });
        });
    }


    // 自定义线程的几种状态
    public enum CustomerThreadStatus {
        FREE, RUNNING, BLOCKED, DEAD
    }

    /**
     * 实现自定义的线程
     */
    private static class CustomerThread extends Thread {

        private CustomerThreadStatus customerThreadStatus;

        /**
         * 设置线程属于哪一个线程组
         * 并设置线程名
         *
         * @param group
         * @param name
         */
        public CustomerThread(ThreadGroup group, String name) {
            super(group, name);
            customerThreadStatus = CustomerThreadStatus.FREE;
        }

        public CustomerThreadStatus getThreadStatus() {
            return this.customerThreadStatus;
        }

        @Override
        public void run() {
            OUTER:
            while (this.customerThreadStatus != CustomerThreadStatus.DEAD) {
                Runnable runnable;
                /**
                 * 重写的run方法的实现
                 * 1：由于是对任务队列里面的任务进行判断，是否有任务需要执行
                 */
                synchronized (TASK_QUEUE) {
                    while (TASK_QUEUE.isEmpty()) {
                        try {
                            customerThreadStatus = CustomerThreadStatus.BLOCKED;
                            TASK_QUEUE.wait();
                        } catch (InterruptedException e) {
                            break OUTER;
                        }
                    }
                    runnable = TASK_QUEUE.removeFirst();
                }

                if (runnable != null) {
                    customerThreadStatus = CustomerThreadStatus.RUNNING;
                    runnable.run();
                    customerThreadStatus = CustomerThreadStatus.FREE;
                }
            }
        }

        public void closeThread() {
            this.customerThreadStatus = CustomerThreadStatus.DEAD;
        }
    }
}
