package com.baizhi.service;

import com.baizhi.entity.User;
import com.baizhi.entity.UserCount;
import com.baizhi.entity.UserMap;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/8/1.
 */
public interface UserService {
    Map<String,Object> showAll(Integer page, Integer rows);
    void modify(User user);
    User queryOne(User user);
    List<User> queryAll();
    //查询每月注册人数
    Map<String,Object> selectByMonth();
    //查询地区分布
    List<UserMap> selectByCity();
    String add(User user);
    //手机验证码
    String phoneMsg(String phoneNumbers, HttpSession session);
}
