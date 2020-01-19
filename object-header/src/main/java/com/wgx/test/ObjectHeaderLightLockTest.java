package com.wgx.test;

import com.wgx.model.Person;
import org.openjdk.jol.info.ClassLayout;

/**
 * 轻量级锁测试
 */
public class ObjectHeaderLightLockTest {

    static Person person;

    public static void main(String[] args) throws InterruptedException {
        person = new Person();

        System.out.println("before lock....");
        System.out.println(ClassLayout.parseInstance(person).toPrintable());


        // 线程1 去争夺锁资源
        new Thread(() -> {
            synchronized (person) {
                try {
                    System.out.println("t1 thread lock.....");
                    System.out.println(ClassLayout.parseInstance(person).toPrintable());
                    Thread.sleep(3000);
                    System.out.println("t1 thread release.....");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Thread.sleep(3004);
        sync();

        System.out.println("after lock");
        System.out.println(ClassLayout.parseInstance(person).toPrintable());
    }

    public static void sync() {
        System.out.println(ClassLayout.parseInstance(person).toPrintable());
        synchronized (person) {
            System.out.println("------------main lock ing-------------");
            System.out.println(ClassLayout.parseInstance(person).toPrintable());
        }
    }
}
