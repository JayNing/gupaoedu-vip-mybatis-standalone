package com.gupaoedu;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author: qingshan
 * @Date: 2019/3/5 19:53
 * @Description: 咕泡学院，只为更好的你
 */
public class JdbcC3p0Test {
    @Test
    public void testC3p0() throws IOException,PropertyVetoException {
        // 硬编码，没有使用到配置文件
        ComboPooledDataSource dataSource1=new ComboPooledDataSource();
        dataSource1.setDriverClass("com.mysql.jdbc.Driver");
        dataSource1.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/gp-mybatis?useUnicode=true");
        dataSource1.setUser("root");
        dataSource1.setPassword("123456");
        //使用自定义配置
        // ComboPooledDataSource dataSource2 = new ComboPooledDataSource();
        //建立连接
        try{
            Connection conn = dataSource1.getConnection();
            String sql = "SELECT bid, name, author_id FROM blog";
            PreparedStatement pst=conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Integer bid  = rs.getInt("bid");
                String name = rs.getString("name");
                Integer authorId = rs.getInt("author_id");

                System.out.print("bid: " + bid);
                System.out.print(",     标题: " + name);
                System.out.print(",     作者编号: " + authorId);
                System.out.print("\n");
            }
            rs.close();
            pst.close();
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
