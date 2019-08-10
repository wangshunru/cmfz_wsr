package com.baizhi.dao;

import com.baizhi.entity.Album;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Administrator on 2019/7/31.
 */
@Component
public interface AlbumDAO extends  BaseDAO<Album> {

    List<Album> select();
}
