package com.wangguoxiong.java.concurrency.chapter9.guarded_suspension_pattern;

import java.util.LinkedList;

public class RequestQueue {

    private static final LinkedList<Request> queue = new LinkedList<>();

    public Request getRequest() {
        synchronized (queue) {
            while (queue.isEmpty()) {
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                   return null;
                }
            }
            return queue.removeFirst();
        }
    }

    public void putRequest(Request request) {
        synchronized (queue) {
            queue.addLast(request);
            queue.notifyAll();
        }
    }
}
