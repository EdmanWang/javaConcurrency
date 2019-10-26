package com.wangguoxiong.java.concurrency.chapter1;

// 策略模式的具体实现
public class StrategyPattern {
    /**
     * 即：一个业务功能有不同的算法去实现，这种情况下就是可以使用策略模式的思维
     * 方式去解决问题
     * 使用场景举例：
     * 每一年国家的税务比率是会发生变化的，但是如果按照常规去写代码的话，只要税务比率
     * 一变化，就需要修改代码，但是使用策略模式就是可以避免这种情况
     */

    private Double salary;

    private Double bonus;

    private Strategy strategy;

    public StrategyPattern(Double salary, Double bonus) {
        this.salary = salary;
        this.bonus = bonus;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public Double calculator() {
        // 调用不同的策略实现算法
        return strategy.execute(this.salary, this.bonus);
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Double getBonus() {
        return bonus;
    }

    public void setBonus(Double bonus) {
        this.bonus = bonus;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public static void main(String[] args) {
        // 第一种策略计算的结果
        StrategyPattern strategyPattern01 = new StrategyPattern(20000d, 1000d);
        strategyPattern01.setStrategy((s, b) -> {
            return s * 0.01 + b * 0.02; // 不同的算法实现
        });
        System.out.println("按照策略1您需要上交的个人所得税是" + strategyPattern01.calculator());
        // 二种策略计算的结果
        StrategyPattern strategyPattern02 = new StrategyPattern(20000d, 1000d);
        strategyPattern02.setStrategy((s, b) -> {
            return s * 0.1 + b * 0.02; // 不同的算法实现
        });
        System.out.println("按照策略2您需要上交的个人所得税是" + strategyPattern02.calculator());
    }

    // 第一种策略方式去计算需要缴纳的个人所得税
    static class StrategyImpl01 implements Strategy {

        @Override
        public Double execute(Double salary, Double bonus) {
            return salary * 0.01 + bonus * 0.02;
        }
    }

    // 第二种策略方式去计算需要缴纳的个人所得税
    static class StrategyImpl02 implements Strategy {
        @Override
        public Double execute(Double salary, Double bonus) {
            return salary * 0.01 + bonus * 0.02;
        }
    }

    @FunctionalInterface
    interface Strategy {
        Double execute(Double salary, Double bonus);
    }
}
