package com.baizhi.service;

import com.baizhi.dao.AlbumDAO;
import com.baizhi.dao.ChapterDAO;
import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by Administrator on 2019/7/31.
 */
@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumDAO albumDAO;

    @Autowired
    private ChapterDAO chapterDAO;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<Album> showAll(Integer page, Integer rows) {
        List<Album> albums = albumDAO.selectAll((page - 1) * rows, rows);
        List<Chapter> chaps = chapterDAO.select();
        Map<String, Integer> map = new HashMap<>();
        for (Album album : albums) {
            Integer count = 0;
            for (Chapter chap : chaps) {
                if(album.getId().equals(chap.getAlbum_id())){
                    count++;
                }
                map.put(album.getId(),count);
            }
        }
        for (Album album : albums) {
            Integer number = map.get(album.getId());
            album.setNumber(number);
        }
        return albums;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Integer showCount() {
        Integer records = albumDAO.selectCount();
        return records;
    }

    @Override
    public String add(Album album) {
        album.setId(UUID.randomUUID().toString());
        album.setPub_date(new Date());
        album.setNumber(0);
        /*album.setScore(5.0);*/
        albumDAO.insert(album);
        return album.getId();
    }

    @Override
    public void modify(Album album) {
        albumDAO.update(album);
    }

    @Override
    public void remove(Album album) {
        albumDAO.delete(album.getId());
    }

    @Override
    public List<Album> show() {
        List<Album> albums = albumDAO.select();
        return albums;
    }
}
