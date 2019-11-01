package com.wangguoxiong.java.concurrency.chapter6;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 非阻塞队列
 * 1:从队列中获取元素的时候，如果队列是空的话，直接返回null
 */
public class NoneBlockQueue {

    public static void main(String[] args) {
        ConcurrentLinkedQueue<String> clq = new ConcurrentLinkedQueue<String>();
        clq.add("java");
        clq.add("html");
        clq.add("css");
        clq.add("wgx");

        /**
         * peek 只是获取对首元素，不移除元素
         * poll 获取对首元素，并且移除该元素
         */
        System.out.println(clq.peek());
        System.out.println(clq.size());
        System.out.println(clq.poll());
        System.out.println(clq.poll());
    }
}
