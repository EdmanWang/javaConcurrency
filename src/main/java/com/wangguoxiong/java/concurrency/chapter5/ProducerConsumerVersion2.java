package com.wangguoxiong.java.concurrency.chapter5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * 使用场景：
 * 外部有10台机器做数据采集，但是主机为了最大化利用cpu只能开5个线程去执行数据的采集工作。
 * 这里就是涉及到了一个线程池的概念
 */
public class ProducerConsumerVersion2 {

    private static final int MAX_THREAD_NUMBER = 5;

    private static final LinkedList<Control> CONTROLS = new LinkedList<>();

    public static void main(String[] args) {
        final List<Thread> list = new ArrayList<>();
        Arrays.asList("M1", "M2", "M3", "M4", "M5", "M6", "M7", "M8", "M9", "M10").stream().map(ProducerConsumerVersion2::createThread).forEach(thread -> {
            list.add(thread);
            thread.start();
        });

        list.stream().forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Optional.of("all of thread finish").ifPresent(System.out::println);
    }

    private static Thread createThread(String threadName) {
        return new Thread(() -> {
            Optional.of("the worker 【" + Thread.currentThread().getName() + "】begin capture data.....").ifPresent(System.out::println);
            synchronized (CONTROLS) {
                while (CONTROLS.size() > MAX_THREAD_NUMBER) {
                    try {
                        CONTROLS.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                CONTROLS.addLast(new Control());
            }

            Optional.of("the worker 【" + Thread.currentThread().getName() + "】 WORKING.....").ifPresent(System.out::println);
            try {
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (CONTROLS) {
                Optional.of("the worker 【" + Thread.currentThread().getName() + "】 END.....").ifPresent(System.out::println);
                CONTROLS.removeFirst();
                CONTROLS.notifyAll();
            }

        }, threadName);
    }


    static class Control {

    }
}
