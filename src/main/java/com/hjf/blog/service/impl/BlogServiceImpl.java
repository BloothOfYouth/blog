package com.hjf.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hjf.blog.dao.BlogDao;
import com.hjf.blog.entity.Blog;
import com.hjf.blog.entity.Tag;
import com.hjf.blog.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hjf
 */
@Service
public class BlogServiceImpl implements IBlogService {

    @Autowired
    private BlogDao blogDao;

    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;

    @Autowired
    private TransactionDefinition transactionDefinition;

    /**
     * 保存Blog
     * @param blog
     * @return
     */
    @Override
    public boolean addBlog(Blog blog) {
        // 开启事务
        TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);

        try {
            blog.setViews(0);
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blogDao.addOneBlog(blog);
            //表单提交时没有ids，就是没有Tags集合，就不用再tag_blog表中加数据
            if(blog.getTags() != null && blog.getTags().size() != 0){
                blogDao.addBlog_Tags(blog);
            }
            dataSourceTransactionManager.commit(transactionStatus);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            dataSourceTransactionManager.rollback(transactionStatus);
            return false;
        }
    }

    /**
     * 获得Blog
     * @param id
     * @return
     */
    @Override
    public Blog findBlog(Long id) {
        List<Tag> tags = blogDao.findTagsByBlogId(id);
        Blog blog = null;
        //根据Blog有没有tag调用不同方法
        if(tags != null && tags.size() != 0){
            blog = blogDao.findOneBlogHaveTags(id);
        }else{
            blog = blogDao.findOneBlogNoTags(id);
        }
        blog.initTagIds();
        return blog;
    }

    /**
     * 获得Blog并更新浏览次数
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Blog findBlogAndUpdateViews(Long id) {
        List<Tag> tags = blogDao.findTagsByBlogId(id);
        Blog blog = null;
        //根据Blog有没有tag调用不同方法
        if(tags != null && tags.size() != 0){
            blog = blogDao.findOneBlogHaveTags(id);
        }else{
            blog = blogDao.findOneBlogNoTags(id);
        }
        Integer views = blog.getViews();
        //浏览次数加一
        blog.setViews(++views);
        //更新数据库浏览次数
        blogDao.updateViews(blog);
        blog.initTagIds();
        return blog;
    }

    /**
     * 分页查询Blog
     * 条件查询 title , typeId , recommend
     * @param start
     * @param count
     * @return
     */
    @Override
    public PageInfo<Blog> listBlog(int start,int count,String title,Long typeId,Boolean recommend) {
        PageHelper.startPage(start, count);
        List<Blog> blogs = blogDao.findAllBlog(title, typeId, recommend);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        return pageInfo;
    }

    /**
     * 更新Blog
     * @param blog
     * @return
     */
    @Override
    public boolean updateBlog(Blog blog) {
        // 开启事务
        TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);

        try {
            blog.setUpdateTime(new Date());
            blogDao.updateOneBlog(blog);
            List<Tag> tags = blogDao.findTagsByBlogId(blog.getId());
            if(blog.getTags() != null && blog.getTags().size() != 0){
                //表单提交时有ids，就是有Tags集合，先判断之前tag_blog表中是否有数据，没有就添加，有就更新(先删，后添加)
                if(tags != null && tags.size() != 0){ //有就更新 (重要 先删，后添加)
                    /*blogDao.updateBlog_Tags(blog);*/
                    blogDao.deleteTagsByBlogId(blog.getId(),tags);
                    blogDao.addBlog_Tags(blog);
                }else{ //没有就添加
                    blogDao.addBlog_Tags(blog);
                }
            }else{
                //表单提交时没有ids，就是没有Tags集合，先判断之前tag_blog表中是否有数据，没有就不管，有就删除
                if(tags != null && tags.size() != 0){ //有就删除
                    blogDao.deleteTagsByBlogId(blog.getId(),tags);
                }
                //没有就不管
            }
            dataSourceTransactionManager.commit(transactionStatus);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            dataSourceTransactionManager.rollback(transactionStatus);
            return false;
        }
    }

    /**
     * 删除Blog
     * @param id
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteBlog(Long id) {
        List<Tag> tags = blogDao.findTagsByBlogId(id);
        //如果blog有对应的tags，先删除tag_blog表中对应的数据
        if(tags != null && tags.size() != 0){
            blogDao.deleteTagsByBlogId(id,tags);
        }
        blogDao.deleteOneBlog(id);
    }

    /**
     * 为前端直接分页无条件查询所有Blog
     * @return
     */
    @Override
    public PageInfo<Blog> listBlogs(int start, int count) {
        PageHelper.startPage(start, count);
        List<Blog> blogs = blogDao.findAllBlogs();
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        return pageInfo;
    }

    /**
     * 为前端直接查询BLog的最新推荐Top
     * @param count
     * @return
     */
    @Override
    public PageInfo<Blog> listBlogsTop(int count) {
        PageHelper.startPage(1,count);
        List<Blog> blogs = blogDao.findBlogsTop();
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        return pageInfo;
    }

    /**
     * 查询Blog的总数
     * @return
     */
    @Override
    public Integer getTotalBlogsCount() {
        Integer totalBlogsCount = blogDao.getTotalBlogsCount();
        return totalBlogsCount;
    }

    /**
     * 条件查询博客
     * 通过title或者description查询
     * @return
     */
    @Override
    public Map listQueryBlogs(String query, int start, int count) {
        PageHelper.startPage(start,count);
        List<Blog> blogs = blogDao.findQueryBlogs(query);
        int blogsCount = blogs.size();
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        HashMap<String, Object> map = new HashMap<>();
        map.put("blogsCount",blogsCount);
        map.put("pageInfo",pageInfo);
        return map;
    }

    /**
     * 通过type的id分页查询有关所有的Blog
     * @param typeId
     * @param start
     * @param count
     * @return
     */
    @Override
    public PageInfo<Blog> listBlogsByTypeId(Long typeId, int start, int count) {
        PageHelper.startPage(start,count);
        List<Blog> blogs = blogDao.findBlogsByTypeId(typeId);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        return pageInfo;
    }

    /**
     * 通过tag的id分页查询有关所有的Blog
     * @param tagId
     * @param start
     * @param count
     * @return
     */
    @Override
    public PageInfo<Blog> listBlogsByTagId(Long tagId, Integer start, int count) {
        PageHelper.startPage(start,count);
        List<Blog> blogs = blogDao.findBlogsByBlogIds(tagId);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        for (int i = 0; i < pageInfo.getList().size(); i++) {
            Long id = pageInfo.getList().get(i).getId();
            List<Tag> tags = blogDao.findTagsByBlogId(id);
            pageInfo.getList().get(i).setTags(tags);
        }
        return pageInfo;
    }

    /**
     * 返回归档页面的信息
     * @return
     */
    @Override
    public Map<String, List<Blog>> archiverBlog() {
        List<String> years = blogDao.findGroupYear();
        Map<String, List<Blog>> map = new HashMap<>();
        for (String year : years) {
            List<Blog> blogs = blogDao.findBlogsByYear(year);
            map.put(year,blogs);
        }
        return map;
    }

    /**
     * 找三个博客
     * @return
     */
    @Override
    public List<Blog> findthreeBlogs() {
        List<Blog> blogs = blogDao.findthreeBlogs();
        return blogs;
    }
}
