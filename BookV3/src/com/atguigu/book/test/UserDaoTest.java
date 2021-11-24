package com.atguigu.book.test;

import com.atguigu.book.dao.UserDao;
import com.atguigu.book.dao.impl.UserDaoImpl;
import com.atguigu.book.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoTest {

    @Test
    public void queryUserByUserName() {
        UserDao userDao = new UserDaoImpl();
        if (userDao.queryUserByUserName("admin123") == null) {
            System.out.println("用户名可以用");
        } else {
            System.out.println("用户名已存在");
        }
    }

    @Test
    public void queryUserByUserNameAndPassword() {
        UserDao userDao = new UserDaoImpl();
        if (userDao.queryUserByUserNameAndPassword("admin", "admin12") == null) {
            System.out.println("登录失败,用户名或密码错误");
        } else {
            System.out.println("登录成功");
        }
    }

    @Test
    public void saveUser() {
        UserDao userDao = new UserDaoImpl();
        if (userDao.saveUser(new User("admin12", "admin", "123@qq.com")) == -1) {
            System.out.println("注册失败,用户名存在");
        } else {
            System.out.println("注册成功");
        }
    }
}