package com.hjf.blog.dao;

import com.hjf.blog.entity.Blog;
import com.hjf.blog.entity.Type;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * type表的接口
 * @author hjf
 */
@Repository
public interface TypeDao {

    /**
     * 保存Type
     * @param type
     * @return
     */
    void addOneType(Type type);

    /**
     * 获得Type
     * @param id
     * @return
     */
    Type findOneType(Long id);

    /**
     * 根据typeName查询Type
     * @param typeName
     * @return
     */
    Type findOneTypeBytypeName(String typeName);

    /**
     * 分页查询Type
     * 这里使用PageHelper来实现分页
     * @param start
     * @param count
     * @return
     */
    List<Type> listType(int start, int count);

    /**
     * 查询所有Blog有的Type
     * @return
     */
    List<Type> findAllTypeForBlog();

    /**
     * 查询所有Type
     * @return
     */
    List<Type> findAllType();

    /**
     * 更新Type
     * @param type
     * @return
     */
    void updateOneType(Type type);

    /**
     * 删除Type
     * @param id
     */
    void deleteOneType(Long id);

    /**
     * 根据type对应Blog的数量倒序排序
     * @return
     */
    List<Type> OrderfindAllType();

    /**
     * 根据TypeId找Blog集合
     * @param id
     * @return
     */
    List<Blog> findBlogsByTypeId(Long id);

    /**
     * 找6个Type
     * @return
     */
    List<Type> findSixType();
}
