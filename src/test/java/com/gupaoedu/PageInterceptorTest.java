package com.gupaoedu;

import com.gupaoedu.domain.Blog;
import com.gupaoedu.mapper.BlogMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Author: qingshan
 * @Date: 2019/1/16 22:18
 * @Description: 咕泡学院，只为更好的你
 */
public class PageInterceptorTest {
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void prepare() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        inputStream.close();
    }

    /**
     * 要先在配置文件中开启插件
     */
    @Test
    public void testPlugin() {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            int start = 5; // offset
            int pageSize = 5; // limit
            RowBounds rb = new RowBounds(start, pageSize);
            BlogMapper mapper = session.getMapper(BlogMapper.class);
            List<Blog> list = mapper.selectBlogList(rb);
        } finally {
            session.close();
        }
    }
}
