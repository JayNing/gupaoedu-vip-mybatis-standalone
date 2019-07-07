package com.gupaoedu.domain.associate;

import java.util.List;

/**
 * @Author: qingshan
 * @Date: 2019/4/13 19:05
 * @Description: 咕泡学院，只为更好的你
 */
public class AuthorAndBlog {
    Integer author_id; // 作者ID
    String author_name; // 作者名称
    List<BlogAndComment> blog; // 文章和评论列表

    @Override
    public String toString() {
        return "AuthorAndBlog{" +
                "author_id=" + author_id +
                ", author_name='" + author_name + '\'' +
                ", blog=" + blog +
                '}';
    }

    public Integer getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Integer author_id) {
        this.author_id = author_id;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public List<BlogAndComment> getBlog() {
        return blog;
    }

    public void setBlog(List<BlogAndComment> blog) {
        this.blog = blog;
    }
}
