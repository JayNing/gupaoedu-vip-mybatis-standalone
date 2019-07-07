package com.gupaoedu;

import com.gupaoedu.domain.associate.AuthorAndBlog;
import com.gupaoedu.domain.Blog;
import com.gupaoedu.domain.associate.BlogAndAuthor;
import com.gupaoedu.domain.associate.BlogAndComment;
import com.gupaoedu.mapper.BlogMapper;
import com.gupaoedu.mapper.BlogMapperExt;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;
import org.junit.Test;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: qingshan
 * @Date: 2019/1/16 13:44
 * @Description: 咕泡学院，只为更好的你
 * MyBatis Maven演示工程
 */
public class MyBatisTest {

    /**
     * 使用MyBatis API方式
     * @throws IOException
     */
    @Test
    public void testStatement() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession session = sqlSessionFactory.openSession();
        try {
            Blog blog = (Blog) session.selectOne("com.gupaoedu.mapper.BlogMapper.selectBlogById", 1);
            System.out.println(blog);
        } finally {
            session.close();
        }
    }

    /**
     * 通过 SqlSession.getMapper(XXXMapper.class)  接口方式
     * @throws IOException
     */
    @Test
    public void testSelect() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession session = sqlSessionFactory.openSession(); // ExecutorType.BATCH
        try {
            BlogMapper mapper = session.getMapper(BlogMapper.class);

            Blog blog = mapper.selectBlogById(1999);
            System.out.println(blog);
        } finally {
            session.close();
        }
    }

    /**
     * 测试插入
     * @throws IOException
     */
    @Test
    public void testInsert() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession session = sqlSessionFactory.openSession();
        try {
            BlogMapper mapper = session.getMapper(BlogMapper.class);
            Blog blog = new Blog();
            blog.setBid(1997);
            blog.setName("测试插入");
            blog.setAuthorId(1111);
            System.out.println(mapper.insertBlog(blog));
            session.commit();
        } finally {
            session.close();
        }
    }

    @Test
    public void testUpdate() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession session = sqlSessionFactory.openSession();
        try {
            BlogMapper mapper = session.getMapper(BlogMapper.class);
            Blog blog = new Blog();
            blog.setBid(1997);
            blog.setName("测试更新");
            blog.setAuthorId(222);
            System.out.println(mapper.updateByPrimaryKey(blog));
            session.commit();
        } finally {
            session.close();
        }
    }

    /**
     * 测试插入TypeHandler
     * @throws IOException
     */
    @Test
    public void testInsertMapJsonTypeHandler() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession session = sqlSessionFactory.openSession();
        try {
            BlogMapper mapper = session.getMapper(BlogMapper.class);
            Blog blog = new Blog();
            blog.setBid(1999);
            blog.setName("测试插入");
            blog.setAuthorId(1111);
            List<Map<String, Object>> list = getMapList();
            blog.setConfigInfo(list);
            System.out.println(mapper.insertBlog(blog));
            session.commit();
        } finally {
            session.close();
        }
    }

    private List<Map<String,Object>> getMapList() {
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i=0;i<5;i++){
            Map<String, Object> map = new HashMap<>();
            map.put("code",i);
            map.put("name","小宁" + i + "号");
            list.add(map);
        }
        return list;
    }

    /**
     * # 和 $ 的区别
     * @throws IOException
     */
    @Test
    public void testSelectByBean() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession session = sqlSessionFactory.openSession();
        try {
            BlogMapper mapper = session.getMapper(BlogMapper.class);
            Blog queryBean = new Blog();
            queryBean.setName("咕泡学院");
            List<Blog> blog = mapper.selectBlogByBean(queryBean);
            System.out.println("查询结果："+blog);
        } finally {
            session.close();
        }
    }

    /**
     * 逻辑分页
     * @throws IOException
     */
    @Test
    public void testSelectByRowBounds() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession session = sqlSessionFactory.openSession();
        try {
            BlogMapper mapper = session.getMapper(BlogMapper.class);
            int start = 0; // offset
            int pageSize = 5; // limit
            RowBounds rb = new RowBounds(start, pageSize);
            List<Blog> list = mapper.selectBlogList(rb); // 使用逻辑分页
            for(Blog b :list){
                System.out.println(b);
            }
        } finally {
            session.close();
        }
    }

    /**
     * Mapper.xml的继承性
     * @throws IOException
     */
    @Test
    public void testMapperExt() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession session = sqlSessionFactory.openSession();
        try {
            BlogMapperExt mapper = session.getMapper(BlogMapperExt.class);
            Blog blog = mapper.selectBlogByName("咕泡学院");
            System.out.println(blog);
            // 继承了父Mapper的方法
            Blog blog1 = mapper.selectBlogById(1);
            System.out.println(blog1);
        } finally {
            session.close();
        }
    }

    /**
     * 一对一，一篇文章对应一个作者
     * 嵌套结果，不存在N+1问题
     */
    @Test
    public void testSelectBlogWithAuthorResult() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        BlogMapper mapper = session.getMapper(BlogMapper.class);

        BlogAndAuthor blog = mapper.selectBlogWithAuthorResult(1);
        System.out.println("-----------:"+blog);
    }

    /**
     * 一对一，一篇文章对应一个作者
     * 嵌套查询，会有N+1的问题
     */
    @Test
    public void testSelectBlogWithAuthorQuery() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        BlogMapper mapper = session.getMapper(BlogMapper.class);

        BlogAndAuthor blog = mapper.selectBlogWithAuthorQuery(1);
        System.out.println("-----------:"+blog.getClass());
        // 如果开启了延迟加载，会在使用的时候才发出SQL
        // equals,clone,hashCode,toString也会触发延迟加载
        // System.out.println("-----------调用toString方法:"+blog);
        System.out.println("-----------getAuthor:"+blog.getAuthor().toString());
        // 如果 aggressiveLazyLoading = true ，也会触发加载，否则不会
        //System.out.println("-----------getName:"+blog.getName());
    }

    /**
     * 一对多关联查询：一篇文章对应多条评论
     * @throws IOException
     */
    @Test
    public void testSelectBlogWithComment() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession session = sqlSessionFactory.openSession();
        try {
            BlogMapper mapper = session.getMapper(BlogMapper.class);
            BlogAndComment blog = mapper.selectBlogWithCommentById(1);
            System.out.println(blog);
        } finally {
            session.close();
        }
    }

    /**
     * 多对多关联查询：作者的文章的评论
     * @throws IOException
     */
    @Test
    public void testSelectAuthorWithBlog() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession session = sqlSessionFactory.openSession();
        try {
            BlogMapper mapper = session.getMapper(BlogMapper.class);
            List<AuthorAndBlog> authors = mapper.selectAuthorWithBlog();
            for (AuthorAndBlog author : authors){
                System.out.println(author);
            }
        } finally {
            session.close();
        }
    }

}
