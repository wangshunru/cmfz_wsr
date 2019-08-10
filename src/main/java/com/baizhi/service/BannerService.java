package com.baizhi.service;

import com.baizhi.entity.Banner;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by Administrator on 2019/7/30.
 */
public interface BannerService {
    List<Banner> showAll(Integer page,Integer rows);
    String add(Banner banner);
    Integer count();
    void modify(Banner banner);
    void remove(Banner banner);
    Banner queryOne(Banner banner);
}
