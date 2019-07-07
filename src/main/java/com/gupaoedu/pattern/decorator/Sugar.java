package com.gupaoedu.pattern.decorator;

/**
 * @Author: qingshan
 * @Date: 2019/3/11 00:36
 * @Description: 咕泡学院，只为更好的你
 *
 * 加糖1元
 */
public class Sugar extends Condiment {
    private Drink drink;

    public Sugar(Drink drink) {
        this.drink = drink;
    }

    public String getDesc() {
        return drink.getDesc() + "+糖";
    }

    public double cost() {
        return 1 + drink.cost();
    }
}
