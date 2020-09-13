package com.hjf.blog.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * (Tag)实体类
 *
 * @author hjf
 * @since 2020-03-28 14:54:56
 */
public class Tag implements Serializable {

    /**
    * tag表id
    */
    private Long id;
    /**
    * 标签名
    */
    private String tagName;
    /**
     * 一个标签有多个博客
     */
    private List<Blog> blogs= new ArrayList<>();

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", tagName='" + tagName + '\'' +
                ", blogs=" + blogs +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }
}