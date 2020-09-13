package com.hjf.blog.dao;

import com.hjf.blog.entity.Blog;
import com.hjf.blog.entity.Tag;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * tag表的接口
 * @author hjf
 */
@Repository
public interface TagDao {

    /**
     * 保存Tag
     * @param tag
     * @return
     */
    void addOneTag(Tag tag);

    /**
     * 获得Tag
     * @param id
     * @return
     */
    Tag findOneTag(Long id);

    /**
     * 根据tagName查询Tag
     * @param tagName
     * @return
     */
    Tag findOneTagBytagName(String tagName);

    /**
     * 分页查询Tag
     * 这里使用PageHelper来实现分页
     * @param start
     * @param count
     * @return
     */
    List<Tag> listTag(int start, int count);

    /**
     * 查询所有Tag
     * @return
     */
    List<Tag> findAllTag();

    /**
     * publish = 1
     * 查询所有Tag
     * @return
     */
    List<Tag> findAllTagPublish();

    /**
     * 更新Tag
     * @param tag
     * @return
     */
    void updateOneTag(Tag tag);

    /**
     * 删除Tag
     * @param id
     */
    void deleteOneTag(Long id);

    /**
     * 参数遍历的查找
     * @param map
     * @return
     */
    List<Tag> findTagForeach(Map<String, Object> map);

    /**
     * 通过tagId找BlogId
     * @param tagId
     * @return
     */
    List<Long> findBlogIdByTagId(Long tagId);

    /**
     * 根据tag对应Blog的数量倒序排序
     * @return
     */
    List<Tag> OrderfindAllTag();

    /**
     * 根据TagId找Blog集合
     * @return
     */
    List<Blog> findBlogsByTagId(Long tagId);

    /**
     * 找6个Tag
     * @return
     */
    List<Tag> findSixTag();
}
