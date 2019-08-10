package com.baizhi;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

import javax.print.attribute.standard.MediaSize;
import java.util.Date;

/**
 * Created by Administrator on 2019/8/5.
 */
@ExcelTarget(value = "student")
public class Student {
    @Excel(name = "ID",width = 10,height = 15)
    private  String id;
    @Excel(name = "姓名")
    private  String name;
    @Excel(name = "年龄")
    private  Integer age;
    @Excel(name = "成绩")
    private  Double score;
    @Excel(name = "生日",format = "yyyy-MM-dd",width = 20)
    private Date bir;

    public Student() {
    }

    public Student(String id, String name, Integer age, Double score, Date bir) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.score = score;
        this.bir = bir;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", score=" + score +
                ", bir=" + bir +
                '}';
    }

    public Date getBir() {
        return bir;
    }

    public void setBir(Date bir) {
        this.bir = bir;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
