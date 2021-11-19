package com.atguigu.book.test;

import com.atguigu.book.utils.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;


public class JdbcTest {
    @Test
    public void test() {
        Connection connection = JdbcUtils.getConnection();
        System.out.println(connection);
    }
}
