package com.wangguoxiong.java.concurrency.chapter9.future_desgin_pattern;

public interface Future<T> {
    /**
     * get 函数是可以拿到调用函数执行的结果的
     *
     * @return
     * @throws InterruptedException
     */
    T get() throws InterruptedException;
}
