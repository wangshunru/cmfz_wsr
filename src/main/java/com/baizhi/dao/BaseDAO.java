package com.baizhi.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2019/7/31.
 */
public interface BaseDAO<T> {
    List<T> selectAll(@Param("start") Integer page, @Param("rows") Integer rows);
    Integer selectCount();
    void  insert(T t);
    void  update(T t);
    void  delete(String id);
}
