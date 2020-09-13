package com.hjf.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hjf.blog.dao.TagDao;
import com.hjf.blog.entity.Blog;
import com.hjf.blog.entity.Tag;
import com.hjf.blog.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @author hjf
 */
@Service
public class TagServiceImpl implements ITagService {

    @Autowired
    private TagDao tagDao;

    /**
     * 保存Tag
     * @param tag
     * @return 根据是否有一样的tagName判断，无返回true保存成功，有返回false保存失败
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addTag(Tag tag) {
        Tag t = tagDao.findOneTagBytagName(tag.getTagName());
        if(t != null){//tag表有相同的tagName就不修改
            return false;
        }
        tagDao.addOneTag(tag);
        return true;
    }

    /**
     * 获得Tag
     * @param id
     * @return
     */
    @Override
    public Tag findTag(Long id) {
        Tag tag = tagDao.findOneTag(id);
        return tag;
    }

    /**
     * 分页查询Tag
     * @param start
     * @param count
     * @return
     */
    @Override
    public PageInfo<Tag> listTag(int start, int count) {
        PageHelper.startPage(start, count);
        List<Tag> tags = tagDao.findAllTag();
        PageInfo<Tag> pageInfo = new PageInfo<>(tags);
        return pageInfo;
    }

    /**
     * 更新Tag
     * @param tag
     * @return 根据是否有一样的tagName判断，无返回true修改成功，有返回false修改失败
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateTag(Tag tag) {
        Tag t = tagDao.findOneTagBytagName(tag.getTagName());
        if(t != null){//tag表有相同的tagName就不修改
            return false;
        }
        tagDao.updateOneTag(tag);
        return true;
    }

    /**
     * 删除Tag
     * @param id
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteTag(Long id) {
        tagDao.deleteOneTag(id);
    }

    /**
     * 不分页的查询所有Tag
     * @return
     */
    @Override
    public List<Tag> findAllTag() {
        List<Tag> tags = tagDao.findAllTag();
        return tags;
    }

    /**
     * 通过拼接字符串的ids，拆成多个id，来查询Tag
     * @param ids
     * @return
     */
    @Override
    public List<Tag> findSomeTag(String ids) {
        Map<String, Object> map = convertToMapByids(ids);
        List<Long> ids_ = (List<Long>) map.get("ids");
        //由tag的id组成的集合不等于null和集合大小不等于0才找相应的Tags的集合
        if(ids_.size() != 0 && ids_ != null){
            List<Tag> tags = tagDao.findTagForeach(map);
            return tags;
        }else{
            return null;
        }
    }

    /**
     * 为前端分页查询所有Tag
     * @param count
     * @return
     */
    @Override
    public PageInfo<Tag> findAllTagsTop(int count) {
        PageHelper.startPage(1, count);
        List<Tag> tags = tagDao.OrderfindAllTag();
        PageInfo<Tag> pageInfo = new PageInfo<>(tags);
        for (int i = 0; i < pageInfo.getList().size(); i++) {
            Long id = pageInfo.getList().get(i).getId();
            List<Blog> blogs = tagDao.findBlogsByTagId(id);
            pageInfo.getList().get(i).setBlogs(blogs);
        }
        return pageInfo;
    }

    /**
     * 根据tag里的Blog集合的大小逆序排序
     * 不分页的查询所有Tag
     * @return
     */
    @Override
    public List<Tag> orderFindAllTag() {
        List<Tag> tags = tagDao.findAllTagPublish();
        Collections.sort(tags, new Comparator<Tag>() {
            /**
             * 根据Tag里的Blog集合的大小逆序排序
             * @param o1
             * @param o2
             * @return
             */
            @Override
            public int compare(Tag o1, Tag o2) {
                return o2.getBlogs().size() - o1.getBlogs().size();
            }
        });
        return tags;
    }

    /**
     * 找六个Tag
     * @return
     */
    @Override
    public List<Tag> findSixTag() {
        List<Tag> tags = tagDao.findSixTag();
        return tags;
    }

    /**
     * 用来把拼接字符串的ids，拆成多个id
     * @param ids_
     * @return
     */
    private Map<String,Object> convertToMapByids(String ids_){
        HashMap<String, Object> map = new HashMap<>();
        List<Long> ids = new ArrayList<Long>();
        if(!StringUtils.isEmpty(ids_)){
            String[] split = ids_.split(",");
            for (int i = 0; i < split.length; i++) {
                Long id = Long.parseLong(split[i]);
                ids.add(id);
            }
        }
        map.put("ids",ids);
        return  map;
    }
}
