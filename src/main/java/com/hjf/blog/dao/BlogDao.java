package com.hjf.blog.dao;

import com.hjf.blog.entity.Blog;
import com.hjf.blog.entity.Tag;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * blog表的接口
 * @author hjf
 */
@Repository
public interface BlogDao {

    /**
     * 保存Blog
     * 并返回自增主键
     * @param blog
     */
    void addOneBlog(Blog blog);

    /**
     * 把Blog的Tag集合记录加入到tag_blog表中
     * @param blog
     */
    void addBlog_Tags(Blog blog);

    /**
     * 不建议使用
     * 把Blog的Tag集合记录更新到tag_blog表中
     * 更新建议  先删除原有的数据，把现有的数据添加上去
     * @param blog
     */
    @Deprecated
    void updateBlog_Tags(Blog blog);

    /**
     * 获得Blog(有Tags用这个方法)
     * @param id
     * @return
     */
    Blog findOneBlogHaveTags(Long id);

    /**
     * 获得Blog(没有Tags用这个方法)
     * @param id
     * @return
     */
    Blog findOneBlogNoTags(Long id);

    /**
     * 通过Blog的id查询tag_blog表的Tag集合
     * @param id
     * @return
     */
    List<Tag> findTagsByBlogId(Long id);

    /**
     * 条件查询Blog
     * @return
     */
    List<Blog> findAllBlog(String title,Long typeId,Boolean recommend);

    /**
     * 更新Blog
     * @param blog
     */
    void updateOneBlog(Blog blog);

    /**
     * 删除Blog
     * @param id
     */
    void deleteOneBlog(Long id);

    /**
     * 通过Blog的id删除对应要删的Tags
     * @param id
     */
    void deleteTagsByBlogId(Long id,List<Tag> tags);

    /**
     * 直接查询所有Blog
     * @return
     */
    List<Blog> findAllBlogs();

    /**
     * 查询最新推荐的Blog
     * @return
     */
    List<Blog> findBlogsTop();

    /**
     * 查询Blog的总数
     * @return
     */
    Integer getTotalBlogsCount();

    /**
     * 通过title或者description查询Blog
     * @return
     */
    List<Blog> findQueryBlogs(String query);

    /**
     * 更新Blog的views
     * @param blog
     */
    void updateViews(Blog blog);

    /**
     * 根据type的id查询Blog
     * @param typeId
     * @return
     */
    List<Blog> findBlogsByTypeId(Long typeId);

    /**
     * 根据tag的id集合查询Blog集合
     * @param tagId
     * @return
     */
    List<Blog> findBlogsByBlogIds(Long tagId);

    /**
     * 返回归档的所有年份
     * @return
     */
    List<String> findGroupYear();

    /**
     * 根据年份查询Blog集合
     * @param year
     * @return
     */
    List<Blog> findBlogsByYear(String year);

    /**
     * 找3个Blog
     * @return
     */
    List<Blog> findthreeBlogs();
}
