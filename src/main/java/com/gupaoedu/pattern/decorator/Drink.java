package com.gupaoedu.pattern.decorator;

/**
 * @Author: qingshan
 * @Date: 2019/3/11 00:33
 * @Description: 咕泡学院，只为更好的你
 *
 * 饮料——抽象类
 * https://blog.csdn.net/zaoan_2010/article/details/83308891
 */
public abstract class Drink {
    // 饮料名称
    protected String desc;

    public String getDesc() {
        return desc;
    }

    // 饮料价格
    public abstract double cost();
}
