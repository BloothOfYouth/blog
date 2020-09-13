package com.hjf.blog.service.impl;

import com.hjf.blog.dao.UserDao;
import com.hjf.blog.service.IUserService;
import com.hjf.blog.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hjf
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserDao userDao;

    /**
     * 检查用户登录
     * @param username
     * @param password
     * @return
     */
    @Override
    public User checkUser(String username, String password) {
        User user = userDao.findOneUserByUsernameAndPassword(username, password);
        return user;
    }

    /**
     * 通过账号查找用户
     *
     * @param username
     * @return
     */
    @Override
    public User checkUser(String username) {
        User user = userDao.findOneByUsername(username);
        return user;
    }
}
