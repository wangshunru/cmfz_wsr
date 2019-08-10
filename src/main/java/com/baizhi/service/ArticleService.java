package com.baizhi.service;

import com.baizhi.entity.Article;
import org.apache.tomcat.util.descriptor.web.SecurityRoleRef;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by Administrator on 2019/8/1.
 */
public interface ArticleService {
    Map<String,Object> showAll(Integer page,Integer rows);
    void remove(Article article);
    void modify(Article article);
    Article showOne(Article article);
    String add(Article article);
    /*void upload(MultipartFile coverImg,HttpSession session,String id);*/
}
