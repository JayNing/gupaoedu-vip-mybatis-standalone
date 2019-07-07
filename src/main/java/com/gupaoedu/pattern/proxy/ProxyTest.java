package com.gupaoedu.pattern.proxy;

/**
 * @Author: qingshan
 * @Date: 2019/3/7 15:01
 * @Description: 咕泡学院，只为更好的你
 */
public class ProxyTest {
    public static void main(String[] args) {
        /**
         * 中介帮助房东出租房子的过程
         */
        RentServiceProxy HelloHandler = new RentServiceProxy();
        RentService proxy = (RentService) HelloHandler.getProxy(new RentServiceImpl());
        proxy.rent("海滨一号");
    }
}