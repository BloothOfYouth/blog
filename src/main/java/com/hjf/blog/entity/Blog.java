package com.hjf.blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.io.Serializable;
import java.util.List;

/**
 * (Blog)实体类
 *
 * @author hjf
 * @since 2020-03-28 14:49:38
 */
public class Blog implements Serializable {

    /**
    * blog表id
    */
    private Long id;
    /**
    * 博客标题
    */
    private String title;
    /**
    * 博客正文
    */
    private String content;
    /**
    * 博客首图地址
    */
    private String firstPicture;
    /**
    * 标记 (原创，转载，翻译)
    */
    private String flag;
    /**
    * 浏览次数
    */
    private Integer views;
    /**
    * 赞赏是否开启
    */
    private Boolean appreciation;
    /**
    * 转载声明是否开启
    */
    private Boolean shareStatement;
    /**
    * 评论是否开启
    */
    private Boolean commentabled;
    /**
    * 是否发布
    */
    private Boolean published;
    /**
    * 是否推荐
    */
    private Boolean recommend;
    /**
    * 创建时间
    */
    @DateTimeFormat(pattern="yyyy-MM-dd")//set
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")//get
    private Date createTime;
    /**
    * 更新时间
    */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")//set
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")//get
    private Date updateTime;
    /**
    * 外键，类型
    */
    private Type type;
    /**
    * 外键，用户
    */
    private User user;
    /**
     * 一个博客有多个标签
     */
    private List<Tag> tags = new ArrayList<>();
    /**
     * 一个博客有多个评论
     */
    private List<Comment> comments = new ArrayList<>();
    /**
     * 多个tag的id
     */
    private String tagIds;

    /**
     * 博客描述
     */
    private String description;

    /**
     * 初始化tagIds
     * 把tags集合的id拼接成tagIds 1,2,3
     */
    public void initTagIds(){
       this.tagIds = tagsToIds(this.getTags());
    }

    /**
     * 把tags集合的id拼接成tagIds 1,2,3
     * @param tags
     * @return
     */
    private String tagsToIds(List<Tag> tags){
        if(!tags.isEmpty()){
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for (Tag tag : tags) {
                if(flag){
                    ids.append(",");
                }else{
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        }else{
            return tagIds;
        }
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", firstPicture='" + firstPicture + '\'' +
                ", flag='" + flag + '\'' +
                ", views=" + views +
                ", appreciation=" + appreciation +
                ", shareStatement=" + shareStatement +
                ", commentabled=" + commentabled +
                ", published=" + published +
                ", recommend=" + recommend +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", type=" + type +
                ", user=" + user +
                ", tags=" + tags +
                ", comments=" + comments +
                ", tagIds='" + tagIds + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFirstPicture() {
        return firstPicture;
    }

    public void setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Boolean getAppreciation() {
        return appreciation;
    }

    public void setAppreciation(Boolean appreciation) {
        this.appreciation = appreciation;
    }

    public Boolean getShareStatement() {
        return shareStatement;
    }

    public void setShareStatement(Boolean shareStatement) {
        this.shareStatement = shareStatement;
    }

    public Boolean getCommentabled() {
        return commentabled;
    }

    public void setCommentabled(Boolean commentabled) {
        this.commentabled = commentabled;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public Boolean getRecommend() {
        return recommend;
    }

    public void setRecommend(Boolean recommend) {
        this.recommend = recommend;
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getTagIds() {
        return tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}