package com.wangguoxiong.java.concurrency.chapter7;

public interface LifeCycleListener {

    void onEvent(ObserverRunnable.RunnableEvent runnableEvent);
}
