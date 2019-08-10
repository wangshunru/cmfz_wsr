package com.baizhi.service;

import com.baizhi.dao.UserDAO;
import com.baizhi.entity.User;
import com.baizhi.entity.UserCount;
import com.baizhi.entity.UserMap;
import com.baizhi.util.Md5Utils;
import com.baizhi.util.SendPhoneMsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by Administrator on 2019/8/1.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Map<String, Object> showAll(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();
        List<User> users = userDAO.selectAll((page - 1) * rows, rows);
        Integer count = userDAO.selectCount();
        Integer total = count % rows == 0 ? count / rows : count / rows + 1;
        map.put("page", page);
        map.put("records", count);
        map.put("total", total);
        map.put("rows", users);
        return map;
    }

    @Override
    public void modify(User user) {
        userDAO.update(user);
    }

    @Override
    public User queryOne(User user) {
        user = userDAO.selectOne(user.getId());
        return user;
    }

    @Override
    public List<User> queryAll() {
        return userDAO.select();
    }

    @Override
    public Map<String, Object> selectByMonth() {
        Map<String, Object> map = new HashMap<>();
        //存放月份
        Set<String> set = new HashSet<>();
        //获取所有数据
        List<UserCount> total = userDAO.total();
        //性别
        List<String> sex = new ArrayList<>();
        sex.add("boys");
        sex.add("girls");
        //女生
        List<UserCount> girls = new ArrayList<>();
        //男生
        List<UserCount> boys = new ArrayList<>();
        for (UserCount userCount : total) {
            set.add(userCount.getMonths());
            if (userCount.getSex().equals("女")) {
                girls.add(userCount);
            } else {
                boys.add(userCount);
            }
        }
        Map<String, Integer> girl = new HashMap<>();
        for (String month : set) {
            girl.put(month,0);
            for (UserCount userCount : girls) {
                if(userCount.getMonths().equals(month)){
                    girl.put(month,userCount.getCount());
                }
            }
        }
        Map<String, Integer> boy = new HashMap<>();
        for (String month : set) {
            boy.put(month,0);
            for (UserCount userCount : boys) {
                if(month.equals(userCount.getMonths())){
                    boy.put(month,userCount.getCount());
                }
            }
        }
        Collection<Integer> nv = girl.values();
        Collection<Integer> nan = boy.values();
        map.put("month", set);
        map.put("sex", sex);
        map.put("girls", nv);
        map.put("boys", nan);
        return map;
    }

    @Override
    public List<UserMap> selectByCity() {
        //查询所有数据
        List<UserMap> userMap = userDAO.map();
        return userMap;
    }

    @Override
    public String add(User user) {
        //填充user对象
        user.setId(UUID.randomUUID().toString());
        String md5Code = Md5Utils.getMd5Code(user.getPassword());
        user.setPassword(md5Code);
        user.setStatus("0");
        user.setReg_date(new Date());
        user.setSalt(Md5Utils.getSalt(8));
        userDAO.insert(user);
        return user.getId();
    }

    //发送手机验证码
    @Override
    public String phoneMsg(String phoneNumbers, HttpSession session) {
        String randomCode = SendPhoneMsgUtil.getRandomCode(6);
        //存储验证码，以便后续进行校验
        session.setAttribute("phoneCode",randomCode);
        System.out.println("验证码为："+randomCode);
        String message = SendPhoneMsgUtil.getPhoneMsg(phoneNumbers, randomCode);
        return message;
    }
}
