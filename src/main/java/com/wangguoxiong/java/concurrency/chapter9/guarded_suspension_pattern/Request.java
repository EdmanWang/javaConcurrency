package com.wangguoxiong.java.concurrency.chapter9.guarded_suspension_pattern;

/**
 * 按照面向对象的思想去实现相关的设计
 * 第一步是需要知道那些事可以抽取成对象，
 * 第二步是需要考虑，对象具有的属性和方法有哪些。
 * 第三步这些对象是如何联系起来的
 */
public class Request {

    private String value;

    public Request(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
