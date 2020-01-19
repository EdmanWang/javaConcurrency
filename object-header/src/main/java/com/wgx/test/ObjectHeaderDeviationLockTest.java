package com.wgx.test;

import com.wgx.model.Person;
import org.openjdk.jol.info.ClassLayout;

public class ObjectHeaderDeviationLockTest {

    static Person person;

    public static void main(String[] args) throws InterruptedException {
        person = new Person();
        System.out.println("before lock");
        System.out.println(ClassLayout.parseInstance(person).toPrintable());
        sync();
        System.out.println("after lock");
        System.out.println(ClassLayout.parseInstance(person).toPrintable());
    }

    public static void sync(){
        synchronized (person){
            System.out.println("------------synchronized-------------");
        }
    }
}
