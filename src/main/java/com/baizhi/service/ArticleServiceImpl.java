package com.baizhi.service;

import com.baizhi.dao.ArticleDAO;
import com.baizhi.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.*;

/**
 * Created by Administrator on 2019/8/1.
 */
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDAO articleDAO;

    @Override
    public Map<String, Object> showAll(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();
        List<Article> articles = articleDAO.selectAll((page - 1) * rows, rows);
        Integer reocrds = articleDAO.selectCount();
        Integer total = reocrds%rows==0?reocrds/rows:reocrds/rows+1;
        map.put("page",page);
        map.put("reocrds",reocrds);
        map.put("total",total);
        map.put("rows",articles);
        return map;
    }

    @Override
    public void remove(Article article) {
        articleDAO.delete(article.getId());
    }

    @Override
    public void modify(Article article) {
        articleDAO.update(article);
    }

    @Override
    public Article showOne(Article article) {
        return articleDAO.selectOne(article);
    }

    @Override
    public String add(Article article) {
        article.setId(UUID.randomUUID().toString());
        //设置状态
        article.setStatus("0");
        //设置上传日期
        article.setUp_date(new Date());
        article.setUser_id("公开");
        System.out.println(article);
        articleDAO.insert(article);
        return article.getId();
    }

}
