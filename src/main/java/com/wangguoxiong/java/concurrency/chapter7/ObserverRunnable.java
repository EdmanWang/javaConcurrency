package com.wangguoxiong.java.concurrency.chapter7;

public abstract class ObserverRunnable implements Runnable {

    private LifeCycleListener lifeCycleListener;

    public ObserverRunnable(LifeCycleListener lifeCycleListener) {
        this.lifeCycleListener = lifeCycleListener;
    }

    enum RunnableState {
        RUNNING, DONE, ERROR;
     }

    protected void notifyChange(RunnableEvent runnableEvent) {
        lifeCycleListener.onEvent(runnableEvent);
    }

    static class RunnableEvent {
        private RunnableState state;
        private Thread thread;
        private Exception cause;

        public RunnableEvent(RunnableState state, Thread thread, Exception cause) {
            this.state = state;
            this.thread = thread;
            this.cause = cause;
        }

        public RunnableState getState() {
            return state;
        }

        public Thread getThread() {
            return thread;
        }

        public void setThread(Thread thread) {
            this.thread = thread;
        }

        public Exception getCause() {
            return cause;
        }
    }
}
