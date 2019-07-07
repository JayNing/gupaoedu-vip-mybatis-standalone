package com.gupaoedu.interceptor;

import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Field;
import java.util.Properties;

/**
 * @Author JAY
 * @Date 2019/7/7 14:46
 * @Description 将mybatis的内置逻辑分页换成物理分页
 **/

@Intercepts({@Signature(type = Executor.class, method = "query",args = {MappedStatement.class, Object.class,
        RowBounds.class, ResultHandler.class})})
public class ExamplePageInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0];
        RowBounds rb = (RowBounds) args[2];

        if (rb == RowBounds.DEFAULT) {
            return invocation.proceed();
        }
        //将mybatis内置的分页机制关闭
        args[2] = RowBounds.DEFAULT;

        String sql = ms.getBoundSql(ms.getSqlSource()).getSql();

        if (rb.getOffset() > 0){
            sql = sql + " limit " + rb.getOffset() + "," + rb.getLimit();
        }else {
            sql = sql + " limit " + rb.getLimit();
        }

        SqlSource sqlSource = new StaticSqlSource(ms.getConfiguration(),sql,null);

        Field field = ms.getClass().getDeclaredField("sqlSource");
        field.setAccessible(true);
        field.set(ms,sqlSource);

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        System.out.println("1111111111111111111111");
        System.out.println(properties.getProperty("username"));
    }
}
