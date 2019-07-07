package com.gupaoedu.dbutils.dao;

import com.gupaoedu.dbutils.HikariUtil;
import com.gupaoedu.dbutils.dto.BlogDto;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class BlogDao {

    private static QueryRunner queryRunner;
    static {
        queryRunner = HikariUtil.getQueryRunner();
    }

    // 返回单个对象，通过new BeanHandler<>(Class<?> clazz)来设置封装
    public static void selectBlog(Integer bid) throws SQLException {
        String sql = "select * from blog where bid = ? ";
        Object[] params = new Object[]{bid};
        BlogDto blogDto = queryRunner.query(sql, new BeanHandler<>(BlogDto.class), params);
        System.out.println(blogDto);
    }

    //返回列表，通过new BeanListHandler<>(Class<?> clazz)来设置List的泛型
    public static void selectList() throws SQLException {
        String sql = "select * from blog";
        List<BlogDto> list = queryRunner.query(sql, new BeanListHandler<>(BlogDto.class));
        //list.forEach(System.out::println);
    }
}
