package com.wangguoxiong.java.concurrency.chapter9.threadLocal_pattern;

public class ActionContext extends ThreadLocal<Context> {
    @Override
    protected Context initialValue() {
        return new Context();
    }

    private static class ActionHolder {
        private static final ActionContext instance = new ActionContext();
    }

    public static ActionContext getInstance() {
        return ActionHolder.instance;
    }
}
