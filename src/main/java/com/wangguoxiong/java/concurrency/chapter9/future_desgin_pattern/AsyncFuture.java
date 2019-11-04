package com.wangguoxiong.java.concurrency.chapter9.future_desgin_pattern;

public class AsyncFuture<T> implements Future<T> {

    private volatile Boolean isDone = false;

    public T result;

    public void done(T result) {
        synchronized (this) {
            this.isDone = true;
            this.result = result;
            this.notifyAll();
        }
    }

    @Override
    public T get() throws InterruptedException {
        synchronized (this){
            while (!isDone){
                this.wait();
            }
        }
        return result;
    }
}
