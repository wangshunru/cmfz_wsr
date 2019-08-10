package com.baizhi;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

import java.util.List;

/**
 * Created by Administrator on 2019/8/5.
 */
@ExcelTarget(value = "teacher")
public class Teacher {
    @ExcelIgnore
    private  String id;
    @Excel(name = "老师姓名",needMerge = true)
    private  String name;
    @Excel(name = "老师年龄",needMerge = true)
    private  Integer age;
    @ExcelCollection(name = "学生信息")
    private List<Student> students;

    public Teacher() {
    }

    public Teacher(String id, String name, Integer age, List<Student> students) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.students = students;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", students=" + students +
                '}';
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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
