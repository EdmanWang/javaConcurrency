package com.wgx.demo;

/**
 * volatile 线程可见性测试
 * <p>
 * 测试的思路
 * 两个线程共同访问共享资源
 * 1: 如果没有加volatile关键字的话，两个线程相互是不可见的
 * 2：但是加上的volatile 关键字的话，两个线程对共享变量是可见的
 * 3: volatile 实现共享变量的可见性，依赖的是jmm内存模型，jmm内存模型是cpu结构模型
 *   jmm模型是cpu结构模型的一种抽象实现
 *
 * 4：jmm模型的8种原子操作
 *    1: read 将共享变量从主存种读出来
 *    2：load 将从主存种读出来的共享变量拷贝一份放在工作内存种
 *    3：use  在工作内存种使用拷贝的共享变量
 *    4：assign 对操作后的共享变量进行赋值操作
 *    5：store  在工作内存中对重新赋值后的共享变量副本进行存储
 *    6：write 将共享变量副本写入主存
 *
 *    7：lock
 *    8: unlock
 *
 *    lock 和 unlock 这两个原子操作是基于synchronize 关键字去实现的
 *
 * 5：java 中 volatile 关键字保证共享变量的可见性是通过 【MESI】 即：缓存一致性协议，总线嗅探机制
 *    MESI  ----->  指的是 四种状态
 *        m ----> Modify -----> 修改
 *        e ----> exclusively -------> 独占
 *        s ----> share   ----->共享
 *        i ----> invalid ----->无效
 *
 *  6：volatile 关键字还可以保证有序性
 *    保证有序性的关键就是  ------> 用volatile 修饰的属性加了内存屏障，在并发情况下不允许指令重排
 *
 *
 */
public class VisibleTest {

    // 共享变量
    private static boolean initFlag = false;

    public static void main(String[] args) {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!initFlag) {
                }
                System.out.println("线程嗅探到initFlag改变......");
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
