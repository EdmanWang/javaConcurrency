package com.wangguoxiong.java.concurrency.chapter7;

import java.util.List;

public class LifeCycleObserver implements LifeCycleListener {

    private static final Object LOCK = new Object();

    public void concurrencyQuery(List<String> ids) {
        if (ids.isEmpty()) {
            return;
        }
        ids.stream().forEach(id -> new Thread(new ObserverRunnable(this) {
            @Override
            public void run() {
                System.out.println("query for the id---->" + id);
                notifyChange(new RunnableEvent(RunnableState.RUNNING, Thread.currentThread(), null));
                try {
                    Thread.sleep(2000);
//                    int a = 10 / 0;
                    notifyChange(new RunnableEvent(RunnableState.DONE, Thread.currentThread(), null));
                } catch (InterruptedException e) {
                    notifyChange(new RunnableEvent(RunnableState.ERROR, Thread.currentThread(), e));
                }
            }
        }).start());
    }

    @Override
    public void onEvent(ObserverRunnable.RunnableEvent event) {
        synchronized (LOCK) {
            System.out.println("the runnable [" + event.getThread().getName() + "]"
                    + "data change and state is" + event.getState());
            if (event.getCause() != null) {
                event.getCause().getStackTrace();
            }
        }
    }
}
