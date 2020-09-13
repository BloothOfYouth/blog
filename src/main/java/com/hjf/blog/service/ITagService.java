package com.hjf.blog.service;

import com.github.pagehelper.PageInfo;
import com.hjf.blog.entity.Tag;

import java.util.List;

/**
 * @author hjf
 */
public interface ITagService {

    /**
     * 保存Tag
     * @param tag
     * @return 根据是否有一样的tagName判断，无返回true保存成功，有返回false保存失败
     */
    boolean addTag(Tag tag);

    /**
     * 获得Tag
     * @param id
     * @return
     */
    Tag findTag(Long id);

    /**
     * 分页查询Tag
     * @param start
     * @param count
     * @return
     */
    PageInfo<Tag> listTag(int start, int count);

    /**
     * 更新Tag
     * @param tag
     * @return 根据是否有一样的tagName判断，无返回true修改成功，有返回false修改失败
     */
    boolean updateTag(Tag tag);

    /**
     * 删除Tag
     * @param id
     */
    void deleteTag(Long id);

    /**
     * 不分页的查询所有Tag
     * @return
     */
    List<Tag> findAllTag();

    /**
     * 通过拼接字符串的ids，拆成多个id，来查询Tag
     * @param ids
     * @return
     */
    List<Tag> findSomeTag(String ids);

    /**
     * 为前端分页查询所有Tag
     * @param count
     * @return
     */
    PageInfo<Tag> findAllTagsTop(int count);

    /**
     * 根据tag里的Blog集合的大小逆序排序
     * 不分页的查询所有Tag
     * @return
     */
    List<Tag> orderFindAllTag();

    /**
     * 找六个Tag
     * @return
     */
    List<Tag> findSixTag();
}
