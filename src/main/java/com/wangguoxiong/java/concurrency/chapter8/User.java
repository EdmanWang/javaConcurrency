package com.wangguoxiong.java.concurrency.chapter8;

public class User extends Thread {

    private String myName;

    private String myAddress;

    private Gate gate;

    public User(String myName, String myAddress, Gate gate) {
        this.myName = myName;
        this.myAddress = myAddress;
        this.gate = gate;
    }

    @Override
    public void run() {
        while (true) {
            gate.pass(this);
        }
    }

    public String getMyName() {
        return myName;
    }

    public void setMyName(String myName) {
        this.myName = myName;
    }

    public String getMyAddress() {
        return myAddress;
    }

    public void setMyAddress(String myAddress) {
        this.myAddress = myAddress;
    }

    @Override
    public String toString() {
        return "User{" +
                "myName='" + myName + '\'' +
                ", myAddress='" + myAddress + '\'' +
                '}';
    }
}
