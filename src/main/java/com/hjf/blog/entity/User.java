package com.hjf.blog.entity;

import java.util.ArrayList;
import java.util.Date;
import java.io.Serializable;
import java.util.List;

/**
 * (User)实体类
 *
 * @author hjf
 * @since 2020-03-28 14:54:56
 */
public class User implements Serializable {

    /**
    * user表id
    */
    private Long id;
    /**
    * 用户昵称
    */
    private String nickname;
    /**
    * 用户账号
    */
    private String username;
    /**
    * 用户密码
    */
    private String password;
    /**
    * 用户邮箱
    */
    private String email;
    /**
    * 用户头像地址
    */
    private String avatar;
    /**
    * 用户类型
    */
    private String type;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 更新时间
    */
    private Date updateTime;
    /**
     * 一个用户有多个博客
     */
    private List<Blog> blogs = new ArrayList<>();

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                ", type='" + type + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", blogs=" + blogs +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }
}