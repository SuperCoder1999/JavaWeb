package com.atguigu.book.dao;//在 包名 上进行修改,将这个移到上一层

import com.atguigu.book.pojo.User;

/**
 * 观察 网页,指定具体功能
 */
public interface UserDao {
    /**
     * 注册功能,查看用户名是否存在(这个和 之后的savaUser 有些交集,功能的作用位置/时间都不同,不能合并)
     * @return 返回 null,说明目前没有这名用户,可以注册
     */
    public User queryUserByUserName(String userName);

    /**
     * 登录功能
     * @return 返回null,说明用户名或密码错误;否则登陆成功
     */
    public User queryUserByUserNameAndPassword(String userName, String password);

    /**
     * 保存注册用户信息
     * @return 返回-1,说明保存失败.
     */
    public int saveUser(User user);
}
