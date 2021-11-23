package com.atguigu.book.service;

import com.atguigu.book.pojo.User;

public interface UserService {

    /**
     * 判断 用户名是否 已经注册
     * @param userName 传入用户名
     * @return 返回 false 说明 已经注册
     */
    public boolean existsUsername(String userName);

    /**
     * 判断 用户登录是否成功
     * @param userName 用户名
     * @param password 密码
     * @return 返回User对象 说明登录成功(返回User对象是为了 后序操作);返回null说明登录失败
     */
    public User login(String userName, String password);

    /**
     * 判断 注册是否成功
     * @param user 注册的用户信息
     */
    public void registerUser(User user);
}
