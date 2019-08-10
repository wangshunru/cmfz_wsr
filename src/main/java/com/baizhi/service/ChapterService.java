package com.baizhi.service;

import com.baizhi.entity.Chapter;

import java.util.List;

/**
 * Created by Administrator on 2019/7/31.
 */
public interface ChapterService {
    List<Chapter> showAll(Integer page,Integer rows,String album_id);
    String add(Chapter chapter);
    Integer showCount();
    void modify(Chapter chapter);
    List<Chapter> show();
    void delete(Chapter chapter);
}
