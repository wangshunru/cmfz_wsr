package com.baizhi;

import com.alibaba.fastjson.JSON;
import com.baizhi.service.UserService;
import io.goeasy.GoEasy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

/**
 * Created by Administrator on 2019/8/6.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestGoEasy {

    @Autowired
    private UserService  userService;

    @Test
    public  void test(){
       //按照月份查询结果
        Map<String, Object> map = userService.selectByMonth();
        System.out.println(map);
        String content = JSON.toJSONString(map);
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-a1c18ea4094a4f95afcb0c9bbb462176");
        goEasy.publish("myChannel", content);
    }

}
