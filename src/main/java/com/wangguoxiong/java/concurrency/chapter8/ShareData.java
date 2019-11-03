package com.wangguoxiong.java.concurrency.chapter8;

// 共享资源
public class ShareData {
    private char[] chars;

    private static int index = 0;

    private final ReadWriteLock readWriteLock = new ReadWriteLock();

    public ShareData(int size) {
        chars = new char[size];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = '*';
        }
    }

    public char[] read() throws InterruptedException {
        try {
            readWriteLock.readLock();
            return this.doRead();
        } finally {
            readWriteLock.readUnLock();
        }
    }

    public void write(String str) throws InterruptedException {
        try {
            readWriteLock.writeLock();
            char c = this.getChar(str);
            this.doWrite(c);
        } finally {
            readWriteLock.writeUnLock();
        }
    }

    private void doWrite(char c) {
        for (int i = 0; i < chars.length; i++) {
            chars[i] = c;
        }
    }

    private char getChar(String str) {
        if (index >= str.length() - 1) {
            index = 0;
        }
        index++;
        return str.charAt(index);
    }

    private char[] doRead() {
        char[] newChar = new char[chars.length];
        for (int i = 0; i < chars.length; i++) {
            newChar[i] = chars[i];
        }
        return newChar;
    }
}
