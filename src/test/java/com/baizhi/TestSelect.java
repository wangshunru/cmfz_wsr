package com.baizhi;

import com.baizhi.dao.UserDAO;
import com.baizhi.entity.UserCount;
import com.baizhi.entity.UserMap;
import com.baizhi.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/8/6.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TestSelect {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserService userService;
    @Test
    public void test1(){

        Map<String, Object> map = userService.selectByMonth();
        System.out.println(map);
        System.out.println(map.get("girls"));
        System.out.println(map.get("boys"));

    }


    @Test
    public  void  test2(){
        List<UserMap> map = userDAO.map();
        for (UserMap userMap : map) {
            System.out.println(userMap);
        }
    }
}
