package com.baizhi.service;

import com.baizhi.entity.Admin;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by Administrator on 2019/7/29.
 */
public interface AdminService {
    /*Admin login(Admin admin);*/
    Map<String,Object> login(Admin admin, HttpSession session,String inputCode);
}
