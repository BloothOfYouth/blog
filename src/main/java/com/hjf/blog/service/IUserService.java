package com.hjf.blog.service;

import com.hjf.blog.entity.User;

/**
 * @author hjf
 */
public interface IUserService {
    /**
     * 通过账号和密码检查用户登录
     * @param username
     * @param password
     * @return
     */
    User checkUser(String username,String password);

    /**
     * 通过账号查找用户
     * @param username
     * @return
     */
    User checkUser(String username);
}
