package com.atguigu.book.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {
    private static DruidDataSource dataSource;//这里 韩顺平老师 的是DataSource类

    //让该线程上的所有方法,获取的connection 都是同一个
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    static {
        Properties properties = new Properties();
        //获取 JdbcUtils 属性配置文件 jdbc.properties
        InputStream resourceAsStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
        try {
            properties.load(resourceAsStream);
            dataSource = (DruidDataSource)DruidDataSourceFactory.createDataSource(properties);
            //需要向下转型
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接池中的连接
     * @return 如果返回null,说明获取连接失败<br/>有值就是获取连接成功
     */
    public static Connection getConnection() {
        Connection connection = threadLocal.get();
        if (connection == null) {//如果当前 线程中没有保存 connection,就创建
            try {
                connection = dataSource.getConnection();
                //创建后 保存到线程中
                threadLocal.set(connection);
                //获取connection,说明开始操作数据库,这时开启 手动管理事务
                connection.setAutoCommit(false);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return connection;
    }
    /*public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }*/

    /**
     * 关闭连接,将连接放回数据库连接池中
     * @param
     */
   /* public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }*/
    public static void commitAndClose() {
        //这里不用从调用者传入connection,
        // 而且调用commitAndClose()方法的也不是BaseDao了,所以其他方法中也传入不了connection
        Connection connection = threadLocal.get();
        if (connection != null) {//如果用过connection,就关闭.如果压根没用到,就不管
            try {
                connection.commit();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
//        remove方法判断该当前线程对应的threadLocals变量是否为null，
//         不为null就直接删除当前线程中指定的threadLocals变量.
//        为什么使用它,暂时不清楚
        threadLocal.remove();
    }

    public static void rollbackAndClose() {
        //这里不用从调用者传入connection,
        // 而且调用commitAndClose()方法的也不是BaseDao了,所以其他方法中也传入不了connection
        Connection connection = threadLocal.get();
        if (connection != null) {//如果用过connection,就关闭.如果压根没用到,就不管
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                try {
                    //如果,不是关于Dao中出现的异常,那么这里也会执行finally的connection.close()方法吗?
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
//        remove方法判断该当前线程对应的threadLocals变量是否为null，
//         不为null就直接删除当前线程中指定的threadLocals变量.
//        为什么使用它,暂时不清楚
        threadLocal.remove();
    }
}
