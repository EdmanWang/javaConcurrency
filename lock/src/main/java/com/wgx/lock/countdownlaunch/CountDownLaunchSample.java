package com.wgx.lock.countdownlaunch;

import java.util.concurrent.CountDownLatch;

/**
 * @author ：edmanwang
 */
public class CountDownLaunchSample {

    public static void main(String[] args) throws InterruptedException {
        long now = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(2);
        /**
         * 下面的两个线程会唤醒主线程两次
         * 1：这也就解释了。如果没有达到permit次数的时候，主线程会一直阻塞
         * 2：但是工作线程的数量如果是超过了permit数量德华，无法达到 CountDownLaunch 的效果
         */
        new Thread(new SeeDoctorTask(countDownLatch)).start();
        new Thread(new QueueTask(countDownLatch)).start();
        //等待线程池中的2个任务执行完毕，否则一直
        /**
         * 这个地方阻塞的是主线程
         */
        countDownLatch.await();
        System.out.println("over，回家 cost:"+(System.currentTimeMillis()-now));
    }
}
