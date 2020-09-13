package com.hjf.blog.service;

import com.github.pagehelper.PageInfo;
import com.hjf.blog.entity.Type;

import java.util.List;

/**
 * @author hjf
 */
public interface ITypeService {

    /**
     * 保存Type
     * @param type
     * @return 根据是否有一样的typeName判断，无返回true保存成功，有返回false保存失败
     */
    boolean addType(Type type);

    /**
     * 获得Type
     * @param id
     * @return
     */
    Type findType(Long id);

    /**
     * 分页查询Type
     * @param start
     * @param count
     * @return
     */
    PageInfo<Type> listType(int start, int count);

    /**
     * 更新Type
     * @param type
     * @return 根据是否有一样的typeName判断，无返回true修改成功，有返回false修改失败
     */
    boolean updateType(Type type);

    /**
     * 删除Type
     * @param id
     */
    void deleteType(Long id);

    /**
     * 不分页的查询所有Type
     * @return
     */
    List<Type> findAllType();

    /**
     * 为前端分页的查询所有Type
     * @return
     */
    PageInfo<Type> findAllTypesTop(int count);

    /**
     * 根据Type里的Blog集合的大小逆序排序
     * 不分页的查询所有Type
     * @return
     */
    List<Type> orderFindAllType();

    /**
     * 找六个Type
     * @return
     */
    List<Type> findSixType();
}
