package com.gupaoedu;

import com.gupaoedu.domain.Blog;
import org.junit.Test;

import java.io.IOException;
import java.sql.*;

/**
 * @Author: qingshan
 * @Date: 2019/2/18 19:48
 * @Description: 咕泡学院，只为更好的你
 */
public class JdbcTest {
    @Test
    public void testJdbc() throws IOException {
        Connection conn = null;
        Statement stmt = null;
        Blog blog = new Blog();

        try {
            // 注册 JDBC 驱动
            Class.forName("com.mysql.jdbc.Driver");

            // 打开连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gp-mybatis", "root", "123456");

            // 执行查询
            stmt = conn.createStatement();
            String sql = "SELECT bid, name, author_id FROM blog";
            ResultSet rs = stmt.executeQuery(sql);

            // 获取结果集
            while (rs.next()) {
                Integer bid = rs.getInt("bid");
                String name = rs.getString("name");
                Integer authorId = rs.getInt("author_id");
                blog.setAuthorId(authorId);
                blog.setBid(bid);
                blog.setName(name);
            }
            System.out.println(blog);

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    /**
     * 原生JDBC的批量操作方式 ps.addBatch()
     * @throws IOException
     */
    @Test
    public void testJdbcBatch() throws IOException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            // 注册 JDBC 驱动
            Class.forName("com.mysql.jdbc.Driver");

            // 打开连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gp-mybatis?useUnicode=true&characterEncoding=utf-8&rewriteBatchedStatements=true", "root", "123456");
            ps = conn.prepareStatement(
                    "INSERT into blog values (?, ?, ?)");

            for (int i = 1000; i < 101000; i++) {
                Blog blog = new Blog();
                ps.setInt(1, i);
                ps.setString(2, String.valueOf(i));
                ps.setInt(3, 1001);
                ps.addBatch();
            }

            ps.executeBatch();
            // conn.commit();
            ps.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
