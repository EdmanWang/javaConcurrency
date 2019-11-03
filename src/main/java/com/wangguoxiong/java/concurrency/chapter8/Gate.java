package com.wangguoxiong.java.concurrency.chapter8;

// gate 是一个门，作为线程的共享资源，还有概念是临界值
public class Gate {

    private volatile int count;

    private String userName;

    private String userAddress;

    public synchronized void pass(User user) {
        this.count++;
        this.userName = user.getMyName();
        this.userAddress = user.getMyAddress();
        verify();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void verify() {
        if (this.userName.charAt(0) != this.userAddress.charAt(0)) {
            System.out.print("该通过门的用户信息错误---->");
        }
        System.out.println(toString());
    }

    @Override
    public synchronized String toString() {
        return "通过人数是=" + count +
                ",户名是='" + userName + '\'' +
                ",住址是='" + userAddress + '\'';
    }
}
