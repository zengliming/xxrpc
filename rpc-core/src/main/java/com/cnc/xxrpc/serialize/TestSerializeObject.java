package com.cnc.xxrpc.serialize;

/**
 * @author tony
 * @desc TODO
 * @createDate 2021/3/25 3:00 下午
 */
public class TestSerializeObject {
    String name;
    int age;

    public TestSerializeObject() {
        this.name = "tony";
        this.age = 25;
    }

    @Override
    public String toString() {
        return "Demo{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
