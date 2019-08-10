package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;

import java.util.Date;

/**
 * Created by Administrator on 2019/8/1.
 */
public class User {
    @ExcelIgnore
    private  String id;
    @Excel(name = "手机号",width = 12)
    private  String phone;
    @Excel(name = "密码",width = 58)
    private  String password;
    @Excel(name = "头像",type = 2,width = 16,height = 20)
    private  String pic_img;
    @Excel(name = "昵称")
    private  String ahama;
    @Excel(name = "姓名")
    private  String name;
    @Excel(name = "性别")
    private  String sex;
    @Excel(name = "居住地址")
    private  String city;
    @Excel(name = "个性签名")
    private  String sign;
    @ExcelIgnore
    private  String status;
    @Excel(name = "注册时间",format = "yyyy-MM-dd")
    private  Date reg_date;
    @ExcelIgnore
    private  String salt;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", picImg='" + pic_img + '\'' +
                ", ahama='" + ahama + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", city='" + city + '\'' +
                ", sign='" + sign + '\'' +
                ", status='" + status + '\'' +
                ", regDate=" + reg_date +
                ", salt='" + salt + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getReg_date() {
        return reg_date;
    }

    public void setReg_date(Date reg_date) {
        this.reg_date = reg_date;
    }

    public String getPic_img() {
        return pic_img;
    }

    public void setPic_img(String pic_img) {
        this.pic_img = pic_img;
    }

    public String getAhama() {
        return ahama;
    }

    public void setAhama(String ahama) {
        this.ahama = ahama;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
