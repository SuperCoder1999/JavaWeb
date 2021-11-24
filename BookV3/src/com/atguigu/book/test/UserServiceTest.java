package com.atguigu.book.test;

import com.atguigu.book.pojo.User;
import com.atguigu.book.service.UserService;
import com.atguigu.book.service.impl.UserServiceImpl;
import org.junit.Test;


public class UserServiceTest {

    @Test
    public void existsUsername() {
        UserService userService = new UserServiceImpl();
        System.out.println(userService.existsUsername("admin"));
    }

    @Test
    public void login() {
        UserServiceImpl userService = new UserServiceImpl();
        System.out.println(userService.login("admin45", "admin"));
    }

    @Test
    public void registerUser() {
        UserServiceImpl userService = new UserServiceImpl();
        userService.registerUser(
                new User("admin123", "admin123", "1234@qq.com"));
        userService.registerUser(
                new User("admin1234", "admin1234", "12345@qq.com"));
    }
}