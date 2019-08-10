package com.baizhi;

import com.baizhi.service.UserService;
import com.baizhi.util.SendPhoneMsgUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2019/8/8.
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
public class TestSendPhone {

    @Autowired
    private UserService userService;
    @Test
    public void test(){
        String phoneMsg = SendPhoneMsgUtil.getPhoneMsg("13393230912", "liuyubindashazi");
        System.out.println(phoneMsg);
    }

}
