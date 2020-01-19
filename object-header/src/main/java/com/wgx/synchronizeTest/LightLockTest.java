package com.wgx.synchronizeTest;

import com.wgx.model.People;
import org.openjdk.jol.info.ClassLayout;

public class LightLockTest {

    // 共享资源
    static People people = new People();

    public static void main(String[] args) throws InterruptedException {

        System.out.println(ClassLayout.parseInstance(people).toPrintable());

        Thread t1 = new Thread(() -> {
            sync();
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

        },"t1");

        Thread t2 = new Thread(() -> {
//            try {
//                Thread.sleep(300);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            sync();
        },"t2");

        t1.start();
        t2.start();

        Thread.sleep(1000);
    }

    public static void sync() {
        System.out.println("---------进入同步代码块---------");
        System.out.println(ClassLayout.parseInstance(people).toPrintable());
        synchronized (people) {
            System.out.println("线程 " + Thread.currentThread().getName() + "拿到锁资源");
            System.out.println(ClassLayout.parseInstance(people).toPrintable());
        }
    }
}
