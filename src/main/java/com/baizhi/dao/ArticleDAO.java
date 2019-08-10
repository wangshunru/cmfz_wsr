package com.baizhi.dao;

import com.baizhi.entity.Article;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2019/8/1.
 */
@Component
public interface ArticleDAO extends BaseDAO<Article> {
    Article selectOne(Article article);
}
