package com.wgx.test;

import com.wgx.model.Person;
import org.openjdk.jol.info.ClassLayout;

/**
 * 测试对象头找那个的hashCode
 */
public class ObjectHeaderHashCodeTest {

    public static void main(String[] args) {
        Person person = new Person();
        System.out.println("before hash");
        System.out.println(ClassLayout.parseInstance(person).toPrintable());

        // 计算hash
        System.out.println("hashCode = " + Integer.toHexString(person.hashCode()));
        System.out.println("after hash");
        System.out.println(ClassLayout.parseInstance(person).toPrintable());
    }
}
