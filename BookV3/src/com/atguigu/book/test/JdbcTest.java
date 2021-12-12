package com.atguigu.book.test;

import com.atguigu.book.utils.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;


public class JdbcTest {
    /**
     * 测试 JdbcUtils工具类
     */
    @Test
    public void test() {
        /*for (int i = 0; i < 1000; i++) {
            Connection connection = JdbcUtils.getConnection();
            JdbcUtils.close(connection);
            System.out.println(connection + "+" + i);
        }*/
    }
}
