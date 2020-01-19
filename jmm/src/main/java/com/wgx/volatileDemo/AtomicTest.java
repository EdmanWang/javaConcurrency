package com.wgx.volatileDemo;

/**
 * 测试原子性
 */
public class AtomicTest {

    static int index = 0;

    private static final Object OBJECT = new Object();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    synchronized (OBJECT) {
                        index++; // index++不是一个原子操作
                    }
                }
            }).start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /**
         * 如果上面没有写上线程休眠，最终执行的结果是有区别的。
         * 因为主线程也是持有一份index的副本
         */
        System.out.println(index);
    }
}
