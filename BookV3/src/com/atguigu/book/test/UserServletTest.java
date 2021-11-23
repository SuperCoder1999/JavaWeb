package com.atguigu.book.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 利用 反射 调用 类的各种方法
 */

public class UserServletTest {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String method = "regist";

        UserServletTest userServletTest = new UserServletTest();

        Method method1 = UserServletTest.class.getMethod(method);

        method1.invoke(userServletTest);
    }

    public void login() {
        System.out.println("这是login()方法调用了");
    }

    public void regist() {
        System.out.println("这是regist()方法调用了");
    }

    public void updateUser() {
        System.out.println("这是updateUser()方法调用了");
    }

    public void updateUserPassword() {
        System.out.println("这是updateUserPassword()方法调用了");
    }
}
