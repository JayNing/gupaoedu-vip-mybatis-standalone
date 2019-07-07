package com.gupaoedu.dbutils.dto;


public class BlogDto {
    private Integer bid;
    private String name;
    private Integer authorId;

    public BlogDto() {
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
        return "BlogDto{" +
                "bid=" + bid +
                ", name='" + name + '\'' +
                ", authorId='" + authorId + '\'' +
                '}';
    }
}
