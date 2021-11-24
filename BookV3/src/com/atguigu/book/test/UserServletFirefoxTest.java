package com.atguigu.book.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 利用 反射 调用 类的各种方法
 */

public class UserServletFirefoxTest {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String method = "regist";

        UserServletFirefoxTest userServletFirefoxTest = new UserServletFirefoxTest();

        Method method1 = UserServletFirefoxTest.class.getMethod(method);

        method1.invoke(userServletFirefoxTest);
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
