package com.hjf.blog.entity;

import java.util.ArrayList;
import java.util.Date;
import java.io.Serializable;
import java.util.List;

/**
 * (Comment)实体类
 *
 * @author hjf
 * @since 2020-03-28 14:54:41
 */
public class Comment implements Serializable {

    /**
    * comment表id
    */
    private Long id;
    /**
    * 评论人的昵称
    */
    private String nickname;
    /**
    * 评论人的邮箱
    */
    private String email;
    /**
    * 评论人的内容
    */
    private String content;
    /**
    * 评论人的头像地址
    */
    private String avatar;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 一个评论对应一个博客
    */
    private Blog blog;
    /**
    * comment表parent_comment_id引用父类comment表id
     * 一个父评论有很多子评论
    */
    private List<Comment> comments = new ArrayList<>();
    /**
     * 一个子评论有父评论
     */
    private Comment parentComment;

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", content='" + content + '\'' +
                ", avatar='" + avatar + '\'' +
                ", createTime=" + createTime +
                ", blog=" + blog +
                ", comments=" + comments +
                ", parentComment=" + parentComment +
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }
}