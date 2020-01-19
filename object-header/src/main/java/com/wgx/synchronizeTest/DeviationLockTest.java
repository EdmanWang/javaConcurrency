package com.wgx.synchronizeTest;

import com.wgx.model.People;
import org.openjdk.jol.info.ClassLayout;

public class DeviationLockTest {

    public static void main(String[] args) {

        People people = new People();

        System.out.println(ClassLayout.parseInstance(people).toPrintable());
    }
}
