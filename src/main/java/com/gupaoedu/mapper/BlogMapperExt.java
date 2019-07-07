package com.gupaoedu.mapper;

import com.gupaoedu.domain.Blog;
import com.gupaoedu.mapper.BlogMapper;
/**
 *
 * 扩展类继承了MBG生成的接口和Statement
 *
 */
public interface BlogMapperExt extends BlogMapper {
    /**
     * 根据名称查询文章
     * @param name
     * @return
     */
    public Blog selectBlogByName(String name);
}
