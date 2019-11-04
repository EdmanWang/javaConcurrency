package com.wangguoxiong.java.concurrency.chapter9.future_desgin_pattern;

public interface FutureTask<T> {
    /**
     * 执行返回的是一个future对象
     * @return
     */
    T call();
}
