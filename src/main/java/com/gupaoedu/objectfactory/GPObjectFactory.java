package com.gupaoedu.objectfactory;

import com.gupaoedu.domain.Blog;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/**
 * @Author: qingshan
 * @Date: 2019/3/9 15:25
 * @Description: 咕泡学院，只为更好的你
 * <p>
 * 自定义ObjectFactory，通过反射的方式实例化对象
 * 一种是无参构造函数，一种是有参构造函数——第一个方法调用了第二个方法
 */
public class GPObjectFactory extends DefaultObjectFactory {
    @Override
    public Object create(Class type) {
        System.out.println("创建对象方法：" + type);
        if (type.equals(Blog.class)) {
            Blog blog = (Blog) super.create(type);
            blog.setName("object factory");
            blog.setBid(1111);
            blog.setAuthorId(2222);
            return blog;
        }
        Object result = super.create(type);
        return result;
    }
}
