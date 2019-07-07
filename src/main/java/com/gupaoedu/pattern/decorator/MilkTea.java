package com.gupaoedu.pattern.decorator;

/**
 * @Author: qingshan
 * @Date: 2019/3/11 00:34
 * @Description: 咕泡学院，只为更好的你
 */
public class MilkTea extends Drink {
    public MilkTea() {
        desc = "奶茶";
    }

    @Override
    public double cost() {
        return 22;
    }
}
