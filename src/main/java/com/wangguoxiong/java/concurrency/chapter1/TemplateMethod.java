package com.wangguoxiong.java.concurrency.chapter1;

// 模板方法模式的实现
public class TemplateMethod extends Template{
    @Override
    public void method() {
        System.out.println("我是一位街舞运动员，为大家表演一段街舞");
    }

    public static void main(String[] args) {
        TemplateMethod templateMethod = new TemplateMethod();
        templateMethod.testMethod();
    }
}

abstract class Template{
    public abstract void method();

    protected void testMethod(){
        System.out.println("请开始你的表演--------");
        method();
        System.out.println("你的表演已经结束------");
    }
}
