package com.gupaoedu.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Author: qingshan
 * @Date: 2019/1/16 13:52
 * @Description: 咕泡学院，只为更好的你
 */
public class Blog implements Serializable{
    Integer bid; // 文章ID
    String name; // 文章标题
    Integer authorId; // 文章作者ID

    List<Map<String, Object>> configInfo;

    public List<Map<String, Object>> getConfigInfo() {
        return configInfo;
    }

    public void setConfigInfo(List<Map<String, Object>> configInfo) {
        this.configInfo = configInfo;
    }

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

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "bid=" + bid +
                ", name='" + name + '\'' +
                ", authorId='" + authorId + '\'' +
                ", configInfo='" + configInfo + '\'' +
                '}';
    }
}
