package com.wgx.synchronizeTest;

import com.wgx.model.People;
import org.openjdk.jol.info.ClassLayout;

public class DeviationLockTest {

    static volatile People  people = new People();


    public static void main(String[] args) {
        System.out.println(ClassLayout.parseInstance(people).toPrintable());


        synchronized (people) {
            System.out.println("-----进入  synchronized  第一次----");
            System.out.println(ClassLayout.parseInstance(people).toPrintable());
            synchronized (people) {
                System.out.println("-----进入  synchronized  第二次----");
                System.out.println(ClassLayout.parseInstance(people).toPrintable());
                synchronized (people) {
                    System.out.println("-----进入  synchronized  第三次----");
                    System.out.println(ClassLayout.parseInstance(people).toPrintable());
                }
            }
        }


        System.out.println(ClassLayout.parseInstance(people).toPrintable());
    }
}
