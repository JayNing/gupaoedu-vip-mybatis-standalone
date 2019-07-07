package com.gupaoedu.interceptor;

import com.mysql.jdbc.PreparedStatement;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;

import java.lang.reflect.Field;
import java.sql.Statement;
import java.util.Properties;

/**
 * @Author JAY
 * @Date 2019/7/7 15:28
 * @Description 在未启用日志组件的情况下，输出执行的SQL，并且统计SQL的执行时间
 **/
@Intercepts({@Signature(type = StatementHandler.class,method = "query",args = {Statement.class, ResultHandler.class})})
public class ExampleSQLInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        long startTime = System.currentTimeMillis();
        System.out.println("任务开始时间：" + startTime);

        //获取并打印执行的sql
        try {
            StatementHandler target = (StatementHandler) invocation.getTarget();
            String sql = target.getBoundSql().getSql();
            Object parameterValue = target.getBoundSql().getParameterObject();

            System.out.println("执行的sql语句：" + sql.replace("?",parameterValue == null ? "" : parameterValue.toString()));

            return invocation.proceed();
        }finally {
            long endTime = System.currentTimeMillis();
            System.out.println("任务结束时间：" + endTime);
            System.out.println("任务耗时：" + (endTime - startTime) + "毫秒");
        }
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
