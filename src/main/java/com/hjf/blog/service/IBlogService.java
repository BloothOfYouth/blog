package com.hjf.blog.service;

import com.github.pagehelper.PageInfo;
import com.hjf.blog.entity.Blog;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author hjf
 */
public interface IBlogService {

    /**
     * 保存Blog
     * @param blog
     * @return
     */
    boolean addBlog(Blog blog);

    /**
     * 获得Blog
     * @param id
     * @return
     */
    Blog findBlog(Long id);

    /**
     * 获得Blog并更新浏览次数
     * @param id
     * @return
     */
    Blog findBlogAndUpdateViews(Long id);

    /**
     * 分页查询Blog
     * 条件查询 title , typeId , recommend
     * @param start
     * @param count
     * @return
     */
    PageInfo<Blog> listBlog(int start,int count,String title,Long typeId,Boolean recommend);

    /**
     * 更新Blog
     * @param blog
     * @return
     */
    boolean updateBlog(Blog blog);

    /**
     * 删除Blog
     * @param id
     */
    void deleteBlog(Long id);

    /**
     * 为前端直接查询所有Blog
     * @return
     */
    PageInfo<Blog> listBlogs(int start, int count);

    /**
     * 为前端直接查询BLog的最新推荐Top
     * @param count
     * @return
     */
    PageInfo<Blog> listBlogsTop(int count);

    /**
     * 查询Blog的总数
     * @return
     */
    Integer getTotalBlogsCount();

    /**
     * 条件查询博客
     * 通过title或者description查询
     * @return
     */
    Map listQueryBlogs(String query, int start, int count);

    /**
     * 通过type的id分页查询有关所有的Blog
     * @param typeId
     * @param start
     * @param count
     * @return
     */
    PageInfo<Blog> listBlogsByTypeId(Long typeId, int start, int count);

    /**
     * 通过tag的id分页查询有关所有的Blog
     * @param tagId
     * @param start
     * @param count
     * @return
     */
    PageInfo<Blog> listBlogsByTagId(Long tagId, Integer start, int count);

    /**
     * 返回归档页面的信息
     * @return
     */
    Map<String,List<Blog>> archiverBlog();

    /**
     * 找三个博客
     * @return
     */
    List<Blog> findthreeBlogs();
}
