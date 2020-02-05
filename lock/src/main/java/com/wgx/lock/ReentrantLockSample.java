package com.wgx.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁的简单测试
 */
public class ReentrantLockSample {

    /**
     * 公平锁
     */
    private static ReentrantLock reentrantLock = new ReentrantLock(true);

    /**
     * 测试思路
     * 1：创建两个线程
     * 2：线程去获取锁
     *
     * @param args
     */

    /**
     * 可重入锁 底层实现的原理 。例子如下
     * 1：不管是线程t1 还是线程t2,谁先拿到cpu执行权，执行reentrantLock.lock(); 这时候第一个执行lock的线程拿到锁资源
     * 2：第二个线程在执行lock方法的时候，1：第一次尝试获取锁资源失败，这个时候，用一种双向链表的数据结构将还线程放在阻塞队列中
     * 3：这个时候，将要执行的线程会第二次去尝试获取资源，获取不到的时候，进行阻塞
     * 4：直到获取到锁的线程执行到unlock();函数额时候，释放锁资源，这个方法回去唤醒那些正在等待的线程
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            reentrantLock.lock();
            try {
                System.out.println("-----线程t1获取锁资源-----");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("-----线程t1释放锁资源-----");
            reentrantLock.unlock();
        }, "王国雄01");

        Thread t2 = new Thread(() -> {
            reentrantLock.lock();
            try {
                System.out.println("-----线程t2获取锁资源-----");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("-----线程t2释放锁资源-----");
            reentrantLock.unlock();
        }, "王国雄02");

        t1.start();

        Thread.sleep(10000);

        t2.start();

    }
}
