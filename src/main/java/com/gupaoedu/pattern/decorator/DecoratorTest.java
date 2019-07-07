package com.gupaoedu.pattern.decorator;

/**
 * @Author: qingshan
 * @Date: 2019/3/11 00:38
 * @Description: 咕泡学院，只为更好的你
 *
 *
 */
public class DecoratorTest {
    public static void main(String[] args) {
        Drink milkTea = new MilkTea();
        double cost = milkTea.cost();
        System.out.println(milkTea.getDesc()+":"+cost+"元");

        //奶茶加糖加冰
        Drink milkTea2 = new MilkTea();
        milkTea2 = new Ice(new Sugar(milkTea2));
        double cost2 = milkTea2.cost();
        System.out.println(milkTea2.getDesc()+":"+cost2+"元");

        System.out.println("--------------------");

        //柠檬茶加两份冰
        Drink lemonTea = new LemonTea();
        System.out.println(lemonTea.getDesc()+":"+lemonTea.cost()+"元");
        lemonTea = new Ice(new Ice(lemonTea));
        double cost3 = lemonTea.cost();
        System.out.println(lemonTea.getDesc()+":"+cost3+"元");
    }
}
