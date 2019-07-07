package com.gupaoedu.domain.associate;

import com.gupaoedu.domain.Author;

import java.io.Serializable;

/**
 * @Author: qingshan
 * @Date: 2019/4/13 19:37
 * @Description: 咕泡学院，只为更好的你
 */
public class BlogAndAuthor implements Serializable {
    Integer bid; // 文章ID
    String name; // 文章标题
    Author author; // 作者

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "BlogAndAuthor{" +
                "bid=" + bid +
                ", name='" + name + '\'' +
                ", author=" + author +
                '}';
    }
}
