package com.wgx.volatileDemo;

/**
 * volatile 线程可见性测试
 * <p>
 * 测试的思路
 * 两个线程共同访问共享资源
 * ：1: 如果没有家volatile关键字的话，两个线程相互是不可见的
 * 2：但是加上的volatile 关键字的话，两个线程对共享变量是可见的
 */
public class VisibleTest {

    private static  boolean initFlag = false;

    public static void main(String[] args) {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!initFlag){
                }
                System.out.println("线程嗅探到initFlag改变");
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程b修改initFlag");
                initFlag = true;
            }
        });

        t1.start();

        try {
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t2.start();
    }
}
