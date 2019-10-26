package com.wangguoxiong.java.concurrency.chapter2;

public class ThreadInterrupt {

    static class CustomerThread implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(30_000);
            } catch (InterruptedException e) {
                /**
                 * 只要线程一打断，这边就会接收到被打断因子
                 */
                System.out.println("我已经被打断了");
                // 下面这句话是有问题的
                System.out.println(Thread.interrupted());
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new CustomerThread());
        t1.start();

        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main 线程执行完毕，我现在需要打断t1线程");
        t1.interrupt();
    }
}
