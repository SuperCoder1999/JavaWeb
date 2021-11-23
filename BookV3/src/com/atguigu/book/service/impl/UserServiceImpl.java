package com.atguigu.book.service.impl;

import com.atguigu.book.dao.impl.UserDaoImpl;
import com.atguigu.book.pojo.User;
import com.atguigu.book.service.UserService;

public class UserServiceImpl implements UserService {

    //判断 这些用户信息,需要访问 MySQL数据库,所以创建一个 UserDaoImpl对象,用户查询/获取信息
    private UserDaoImpl userDao = new UserDaoImpl();

    @Override
    public boolean existsUsername(String userName) {
        return userDao.queryUserByUserName(userName) == null ;
    }

    @Override
    public User login(String userName, String password) {
        return userDao.queryUserByUserNameAndPassword(userName, password);
    }

    @Override
    public void registerUser(User user) {
        userDao.saveUser(user);
    }
}
