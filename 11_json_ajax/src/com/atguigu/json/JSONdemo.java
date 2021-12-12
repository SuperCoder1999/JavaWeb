package com.atguigu.json;

import com.atguigu.pojo.Person;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONdemo {

    /**
     * 1.2.1、javaBean和json的互转
     */
    @Test
    public void test1() {
        Person person = new Person(1, "nj");
        // 创建Gson对象实例
        Gson gson = new Gson();

        // toJson(Object object) 方法将Java对象转换成json字符串
        String personJsonString = gson.toJson(person);
        System.out.println(personJsonString);

        // fromJson() 把json字符串 转换成 Java对象
        Person person1 = gson.fromJson(personJsonString, Person.class);
        System.out.println(person1);
    }

    /**
     * 1.2.2、List 和json 的互转
     */
    @Test
    public void test2() {
        ArrayList<Person> people = new ArrayList<>();
        people.add(new Person(1, "nj"));
        people.add(new Person(2, "ta"));

        Gson gson = new Gson();

        // list 转换成 json字符串
        String peopleJsonString = gson.toJson(people);
        System.out.println(peopleJsonString);

        // json字符串转变为 由Java对象组成的 list
        List<Person> list = gson.fromJson(peopleJsonString,
                (new TypeToken<List<Person>>(){

                }).getType());
        System.out.println(list);
        System.out.println(list.get(1));
    }

    /**
     * 1.2.3、map 和json 的互转
     */
    @Test
    public void test3() {
        HashMap<Integer, Person> personMap = new HashMap<>();
        personMap.put(1, new Person(1, "nj"));
        personMap.put(2, new Person(2, "ta"));

        Gson gson = new Gson();

        // map 转换成 json字符串
        String mapJsonString = gson.toJson(personMap);
        System.out.println(mapJsonString);

        // json 转换成 由Person组成的 Map
        Map<Integer, Person> map = gson.fromJson(mapJsonString,
                (new TypeToken<HashMap<Integer, Person>>(){

                }).getType());

        System.out.println(map);
        System.out.println(map.get(1));
    }
}