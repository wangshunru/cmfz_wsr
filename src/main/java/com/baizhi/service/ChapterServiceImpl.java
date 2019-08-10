package com.baizhi.service;

import com.baizhi.dao.ChapterDAO;
import com.baizhi.entity.Chapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2019/7/31.
 */
@Service
@Transactional
public class ChapterServiceImpl implements ChapterService{
    @Autowired
    private ChapterDAO chapterDAO;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<Chapter> showAll(Integer page, Integer rows, String album_id) {
        List<Chapter> chapters = chapterDAO.selsctAllById((page - 1) * rows, rows, album_id);
        return chapters;
    }

    @Override
    public String add(Chapter chapter) {
        chapter.setId(UUID.randomUUID().toString());
        chapter.setUp_date(new Date());
        chapterDAO.insert(chapter);
        return chapter.getId();
    }

    @Override
    public Integer showCount() {
        Integer records = chapterDAO.selectCount();
        return records;
    }

    @Override
    public void modify(Chapter chapter) {
        chapterDAO.update(chapter);
    }

    @Override
    public List<Chapter> show() {
        List<Chapter> chapters = chapterDAO.select();
        return chapters;
    }

    @Override
    public void delete(Chapter chapter) {
        chapterDAO.delete(chapter.getId());
    }
}
