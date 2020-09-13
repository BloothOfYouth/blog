package com.hjf.blog.dao;

import com.hjf.blog.entity.Blog;
import com.hjf.blog.entity.User;
import org.springframework.stereotype.Repository;

/**
 * user表的接口
 * @author hjf
 */
@Repository
public interface UserDao {
    /**
     * 通过账户和密码查找用户表
     * @param username
     * @param password
     * @return
     */
    User findOneUserByUsernameAndPassword(String username,String password);

    /**
     * 通过账户查找用户表
     * @param username
     * @return
     */
    User findOneByUsername(String username);

    /**
     * 返回User
     * @return
     */
    User findOneUser();
}
