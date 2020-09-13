package com.hjf.blog.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * (Type)实体类
 *
 * @author hjf
 * @since 2020-03-28 14:54:56
 */
public class Type implements Serializable {

    /**
    * type表id
    */
    private Long id;
    /**
    * 类型名
    */
    private String typeName;
    /**
     * 一种类型有多个博客
     */
    private List<Blog> blogs = new ArrayList<>();

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", typeName='" + typeName + '\'' +
                ", blogs=" + blogs +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }
}