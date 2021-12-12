package com.atguigu.book.dao.impl;

import com.atguigu.book.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 各个Dao的父类,完成常用方法
 */
public abstract class BaseDao<T> {//感觉在这里添加泛型,更好些:能判断test包下面输入方法的参数类型

    //创建 QueryRunner 对象
    private QueryRunner queryRunner = new QueryRunner();

    /**
     * 完成 create/update/delete操作
     * @return 返回>=1说明影响到数据库,如果返回-1说明没有影响到数据库
     */
    public int update(String sql, Object... args) {
        Connection conn = null;
        try {
            conn = JdbcUtils.getConnection();
            int rows = queryRunner.update(conn, sql, args);
            return rows;
        } catch (SQLException e) {
            System.out.println("BaseDao 异常:");
            e.printStackTrace();
            //如果操作出现异常,就向上抛,由调用者函数,调用JDBCUtils.rollbackAndClose()方法
            throw new RuntimeException(e);
        }/*
        这里就不能操作 WebUtils.close(conn);了, 因为关闭这个连接前,是要决定 是commit还是rollback.
        这里不关闭  等到 Servlet程序中关于数据库的操作全部结束再关闭,
        或者Servlet程序结束 后 由事务过滤器 关闭.
        finally {
            JdbcUtils.close(conn);
        }*/
        //return -1;//推荐写这种能返回-1的.不过 queryRunner.update()默认也是返回-1
    }

    /**
     * 完成 单行查询
     * @param sql sql语句
     * @param clazz 构造BeanHandler对象
     * @param args 可变参数
                    T 类泛型,用于约束 传入参数
     * @return 如果返回 null,代表没有查到内容
     */
    public T queryForOne(String sql, Class<T> clazz, Object... args) {
        Connection conn = null;

        try {
            conn = JdbcUtils.getConnection();
            return queryRunner.query(conn, sql, new BeanHandler<T>(clazz), args);
        } catch (SQLException e) {
            System.out.println("BaseDao 异常:");
            e.printStackTrace();
            //如果操作出现异常,就向上抛,由调用者函数,调用JDBCUtils.rollbackAndClose()方法
            throw new RuntimeException(e);
        }/*
        这里就不能操作 WebUtils.close(conn);了, 因为关闭这个连接前,是要决定 是commit还是rollback.
        这里不关闭  等到 Servlet程序中关于数据库的操作全部结束再关闭,
        或者Servlet程序结束 后 由事务过滤器 关闭.
        finally {
            JdbcUtils.close(conn);
        }*/
        //return null;
    }

    /**
     * 完成 多行查询
     * @param sql sql语句
     * @param clazz 构造BeanListHandler对象
     * @param args 可变参数
    T 类泛型,用于约束 传入参数
     * @return 如果返回 null,代表没有查到内容
     */
    public List<T> queryForMany(String sql, Class<T> clazz, Object... args) {
        Connection conn = null;

        try {
            conn = JdbcUtils.getConnection();
            return queryRunner.query(conn, sql, new BeanListHandler<T>(clazz), args);
        } catch (SQLException e) {
            System.out.println("BaseDao 异常:");
            e.printStackTrace();
            //如果操作出现异常,就向上抛,由调用者函数,调用JDBCUtils.rollbackAndClose()方法
            throw new RuntimeException(e);
        }/*
        这里就不能操作 WebUtils.close(conn);了, 因为关闭这个连接前,是要决定 是commit还是rollback.
        这里不关闭  等到 Servlet程序中关于数据库的操作全部结束再关闭,
        或者Servlet程序结束 后 由事务过滤器 关闭.
        finally {
            JdbcUtils.close(conn);
        }*/
        //return null;
    }

    /**
     * 完成 单行单列查询
     * @param sql sql语句
     * @param args 可变参数
     * @return 如果返回 null,代表没有查到内容
     */
    public Object queryForSingleValue(String sql, Object... args) {
        Connection conn = null;
        try {
            conn = JdbcUtils.getConnection();
            return queryRunner.query(conn, sql, new ScalarHandler(), args);
        } catch (SQLException e) {
            System.out.println("BaseDao 异常:");
            e.printStackTrace();
            //如果操作出现异常,就向上抛,由调用者函数,调用JDBCUtils.rollbackAndClose()方法
            throw new RuntimeException(e);
        }/*
        这里就不能操作 WebUtils.close(conn);了, 因为关闭这个连接前,是要决定 是commit还是rollback.
        这里不关闭  等到 Servlet程序中关于数据库的操作全部结束再关闭,
        或者Servlet程序结束 后 由事务过滤器 关闭.
        finally {
            JdbcUtils.close(conn);
        }*/
        //return null;
    }
}
