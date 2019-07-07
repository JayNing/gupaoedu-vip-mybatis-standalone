package com.gupaoedu.pattern.decorator;

/**
 * @Author: qingshan
 * @Date: 2019/3/11 00:37
 * @Description: 咕泡学院，只为更好的你
 *
 * 加冰块0.5元
 */
public class Ice extends Condiment{

    private Drink drink;

    public Ice(Drink drink) {
        this.drink = drink;
    }

    @Override
    public String getDesc() {
        return drink.getDesc() + "+冰";
    }

    @Override
    public double cost() {
        return 0.5 + drink.cost();
    }

}