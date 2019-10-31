package com.wangguoxiong.java.concurrency.chapter6;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列
 * 1:队列满了的时候，如果还有元素需要假如队列中，会阻塞。直达队列中有位置能存下将要进入队列的元素。
 * 2:当队列是空的时候，再去队列中取元素，这部步操作也是会阻塞，直到队列中有元素让其去获取
 */
public class BlockQueue {

    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>(3);
        blockingQueue.offer("java");
        blockingQueue.offer("html");
        blockingQueue.offer("css");

        System.out.println( blockingQueue.poll());

        blockingQueue.offer("wgx", 3, TimeUnit.SECONDS);

        System.out.println( blockingQueue.poll());
        System.out.println(blockingQueue.size());
        System.out.println( blockingQueue.poll());
        System.out.println( blockingQueue.poll());
        System.out.println( blockingQueue.poll(5,TimeUnit.SECONDS));
    }
}
