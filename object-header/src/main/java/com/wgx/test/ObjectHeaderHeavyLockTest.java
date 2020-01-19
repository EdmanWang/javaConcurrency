package com.wgx.test;

import com.wgx.model.Person;
import org.openjdk.jol.info.ClassLayout;

/**
 * 重量级锁测试
 * 测试流程：无锁----> 轻量级锁---->重量级锁---->无锁
 */
public class ObjectHeaderHeavyLockTest {

    static Person person;

    public static void main(String[] args) throws InterruptedException {

        person = new Person();
        System.out.println("before lock.......");
        System.out.println(ClassLayout.parseInstance(person).toPrintable());

        new Thread(() -> {
            synchronized (person) {
                try {
                    Thread.sleep(5000);
                    System.out.println("custom thread release.....");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Thread.sleep(500);
        System.out.println("custom thread lock ing......");
        System.out.println(ClassLayout.parseInstance(person).toPrintable());
        sync();
        System.gc();

        System.out.println("after gc.....");
        System.out.println(ClassLayout.parseInstance(person).toPrintable());
    }

    public static void sync() {
        synchronized (person) {
            System.out.println("------------main lock ing-------------");
            System.out.println(ClassLayout.parseInstance(person).toPrintable());
        }
    }
}
