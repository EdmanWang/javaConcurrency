package com.wangguoxiong.java.concurrency.chapter5;

import java.util.Optional;
import java.util.stream.Stream;

public class ProducerConsumerVersion1 {

    public static int index = 0;

    public static boolean isProducer = false;

    private static final Object LOCK = new Object();

    public static void producer() {
        synchronized (LOCK) {
            if (isProducer) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                Optional.of("生产者[" + Thread.currentThread().getName() + "]生产了" + (++index))
                        .ifPresent(System.out::println);
                isProducer = true;
                LOCK.notify();
            }
        }
    }

    public static void consumer() {
        synchronized (LOCK) {
            if (isProducer) {
                Optional.of("消费者[" + Thread.currentThread().getName() + "]消费了" + index)
                        .ifPresent(System.out::println);
                isProducer = false;
                LOCK.notify();
            } else {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {

        Stream.of("P1", "P2").forEach((name) -> {
            new Thread(() -> {
                while (true) {
                    producer();
                }
            }, name).start();
        });

        Stream.of("C1", "C2").forEach((name) -> {
            new Thread(() -> {
                while (true) {
                    consumer();
                }
            }, name).start();
        });
    }
}
