package com.wangguoxiong.java.concurrency.chapter9.future_desgin_pattern;

import java.util.function.Consumer;

/**
 * 用来联系future和futureTask
 */
public class FutureService {

    public <T> Future<T> submit(final FutureTask<T> task, final Consumer<T> consumer) {
        AsyncFuture asyncFuture = new AsyncFuture();

        /**
         * 利用线程直接返回的特性，不会发生阻塞
         * 开辟例外一个线程去做其他的事。
         */
        new Thread(() -> {
            T reault = task.call();
            asyncFuture.done(reault);
            consumer.accept(reault);
        }).start();
        return asyncFuture;
    }
}
