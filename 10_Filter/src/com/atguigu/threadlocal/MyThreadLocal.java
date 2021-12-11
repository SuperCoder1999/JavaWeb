package com.atguigu.threadlocal;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MyThreadLocal {
    //创建 一个Map<String,Object> 保存每个线程 中保存的数据
    public static Map<String, Object> data = new HashMap<>();

    private static Random random = new Random();

    public static class Task implements Runnable {
        @Override
        public void run() {
            //在Run方法中,随机生成一个 变量(线程要关联的数据),然后以当前线程名为key,保存到data中
            Integer i = random.nextInt(1000);
            //获取当前线程名
            String name = Thread.currentThread().getName();
            System.out.println("线程["+name+"]生成的随机数是：" + i);
            data.put(name, i);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 查看 当前线程 中其他类的方法中,是否也能调用 线程中保存的 数据
            new OrderService().createOrder();

            //在Run方法结束前,以当前线程名获取数据
            Object o = data.get(name);
            System.out.println("在线程["+name+"]快结束时取出关联的数据是：" + o);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(new Task()).start();
        }
    }
}

class OrderService {
    public void createOrder() {
        String name = Thread.currentThread().getName();
        //打印当前线程 中保存的数据
        System.out.println("OrderService 当前线程[" + name + "]中保存的数据是: " +
                MyThreadLocal.data.get(name));
        new OrderDao().saveOrder();
    }
}

class OrderDao {
    public void saveOrder() {
        String name = Thread.currentThread().getName();
        //打印当前线程 中保存的数据
        System.out.println("OrderDao 当前线程[" + name + "]中保存的数据是: " +
                MyThreadLocal.data.get(name));
    }
}