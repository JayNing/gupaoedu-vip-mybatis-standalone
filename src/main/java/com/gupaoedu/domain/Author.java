package com.gupaoedu.domain;

import java.io.Serializable;

/**
 * @Author: qingshan
 * @Date: 2019/4/13 19:34
 * @Description: 咕泡学院，只为更好的你
 */
public class Author implements Serializable {
    Integer authorId; // 作者ID
    String authorName; // 作者名称

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
