package com.atguigu.book.dao.impl;

import com.atguigu.book.dao.UserDao;
import com.atguigu.book.pojo.User;

public class UserDaoImpl extends BaseDao<User> implements UserDao {

    @Override
    public User queryUserByUserName(String userName) {
        String sql = "select * from t_user where `username`=?";
        return queryForOne(sql, User.class, userName);
    }

    @Override
    public User queryUserByUserNameAndPassword(String userName, String password) {
        String sql = "select * from t_user where `username`=? and `password`=?";
        return queryForOne(sql, User.class, userName, password);
    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into t_user values(null,?,?,?)";
        return update(sql, user.getUsername(), user.getPassword(), user.getEmail());
    }
}
