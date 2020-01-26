package com.wgx.lock;

import java.util.concurrent.Semaphore;

/**
 * 共享锁代码示例  Semaphore
 */
public class SemaphoreSample {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);
        for (int i = 0; i < 5; i++) {
            new Thread(new Task(semaphore, "wgx+" + i)).start();
        }
    }

    static class Task extends Thread {
        Semaphore semaphore;

        public Task(Semaphore semaphore, String tname) {
            this.semaphore = semaphore;
            this.setName(tname);
        }

        public void run() {
            try {
                /**
                 * 使用semaphore做流量限流
                 */
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + ":acquire() at time:" + System.currentTimeMillis());

                Thread.sleep(1000);

                semaphore.release();
                System.out.println(Thread.currentThread().getName() + ":release() at time:" + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
