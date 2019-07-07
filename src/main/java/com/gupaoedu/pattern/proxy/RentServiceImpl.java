package com.gupaoedu.pattern.proxy;

public class RentServiceImpl implements RentService {
    public void rent(String str) {
        System.out.println("房东：身份验证通过，我的房子["+str+"]可以出租");
    }
}
