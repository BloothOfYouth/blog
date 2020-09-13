package com.hjf.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hjf.blog.dao.TypeDao;
import com.hjf.blog.entity.Blog;
import com.hjf.blog.entity.Type;
import com.hjf.blog.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author hjf
 */
@Service
public class TypeServiceImpl implements ITypeService {

    @Autowired
    private TypeDao typeDao;

    /**
     * 保存Type
     * @param type
     * @return 根据是否有一样的typeName判断，无返回true保存成功，有返回false保存失败
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addType(Type type) {
        Type t = typeDao.findOneTypeBytypeName(type.getTypeName());
        if(t != null){//type表有就不增加
            return false;
        }
        typeDao.addOneType(type);
        return true;
    }

    /**
     * 获得Type
     * @param id
     * @return
     */
    @Override
    public Type findType(Long id) {
        Type type = typeDao.findOneType(id);
        return type;
    }

    /**
     * 分页查询Type
     * @param start
     * @param count
     * @return
     */
    @Override
    public PageInfo<Type> listType(int start, int count) {
        PageHelper.startPage(start, count);
        List<Type> types = typeDao.findAllType();
        PageInfo<Type> pageInfo = new PageInfo<>(types);
        return pageInfo;
    }

    /**
     * 更新Type
     * @param type
     * @return 根据是否有一样的typeName判断，无返回true修改成功，有返回false修改失败
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateType(Type type) {
        Type t = typeDao.findOneTypeBytypeName(type.getTypeName());
        if(t != null){//type表有相同的typeName就不修改
            return false;
        }
        typeDao.updateOneType(type);
        return true;
    }

    /**
     * 删除Type
     * @param id
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteType(Long id) {
        typeDao.deleteOneType(id);
    }

    /**
     * 不分页的查询所有Type
     * @return
     */
    @Override
    public List<Type> findAllType() {
        List<Type> types = typeDao.findAllType();
        return types;
    }

    /**
     * 根据Type里的Blog集合的大小逆序排序
     * 不分页的查询所有Type
     * @return
     */
    @Override
    public List<Type> orderFindAllType() {
        List<Type> types = typeDao.findAllTypeForBlog();
        Collections.sort(types, new Comparator<Type>() {
            /**
             * 根据Type里的Blog集合的大小逆序排序
             * @param o1
             * @param o2
             * @return
             */
            @Override
            public int compare(Type o1, Type o2) {
                return o2.getBlogs().size() - o1.getBlogs().size();
            }
        });
        return types;
    }

    /**
     * 找六个Type
     * @return
     */
    @Override
    public List<Type> findSixType() {
        List<Type> types = typeDao.findSixType();
        return types;
    }

    /**
     * 为前端分页的查询所有Type
     * @return
     */
    @Override
    public PageInfo<Type> findAllTypesTop(int count) {
        PageHelper.startPage(1, count);
        List<Type> types = typeDao.OrderfindAllType();
        PageInfo<Type> pageInfo = new PageInfo<>(types);
        for (int i = 0; i < pageInfo.getList().size(); i++) {
            Long id = pageInfo.getList().get(i).getId();
            List<Blog> blogs = typeDao.findBlogsByTypeId(id);
            pageInfo.getList().get(i).setBlogs(blogs);
        }
        return pageInfo;
    }

}
