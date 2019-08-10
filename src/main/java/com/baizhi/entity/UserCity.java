package com.baizhi.entity;

/**
 * Created by Administrator on 2019/8/6.
 */
public class UserCity {
    private  String name;
    private  String value;

    @Override
    public String toString() {
        return "UserCity{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    public UserCity() {
    }

    public UserCity(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
