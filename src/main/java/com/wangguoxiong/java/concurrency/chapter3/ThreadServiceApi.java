package com.wangguoxiong.java.concurrency.chapter3;

// 创建三个线程并发去处业务逻辑
public class ThreadServiceApi {

    private Thread executeThread;

    private static Boolean flag = false;

    public void execute(Runnable task) {
        executeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Thread taskThread = new Thread(task);
                taskThread.setDaemon(true);
                taskThread.start();

                try {
                    taskThread.join();
                    flag = true;
                } catch (InterruptedException e) {
                }
            }
        });
        executeThread.start();
    }

    public void shutDown(long millis) {
        long start = System.currentTimeMillis();
        while (!flag) {
            long now = System.currentTimeMillis();
            if (start + millis < now) {
                System.out.println("任务超时，需要结束任务");
                executeThread.interrupt();
                break;
            }

            try {
                executeThread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        flag = false;
    }
}
