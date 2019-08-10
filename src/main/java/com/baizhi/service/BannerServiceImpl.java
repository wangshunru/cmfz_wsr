package com.baizhi.service;

import com.baizhi.dao.BannerDAO;
import com.baizhi.entity.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2019/7/30.
 */
@Service
@Transactional
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerDAO bannerDAO;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<Banner> showAll(Integer page, Integer rows) {
        List<Banner> banners = bannerDAO.selectAll(page, rows);
        return banners;
    }

    @Override
    public String add(Banner banner) {
        banner.setId(UUID.randomUUID().toString());
        banner.setStatus("0");
        banner.setUp_date(new Date());
        bannerDAO.insert(banner);
        return banner.getId();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Integer count() {
        Integer count = bannerDAO.count();
        return count;
    }

    @Override
    public void modify(Banner banner) {
        bannerDAO.update(banner);
    }

    @Override
    public void remove(Banner banner) {
        bannerDAO.delete(banner.getId());
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Banner queryOne(Banner banner) {
        banner = bannerDAO.selectOne(banner.getId());
        if (banner.getStatus().equals("0")) {
            banner.setStatus("1");
        } else {
            banner.setStatus("0");
        }
        return banner;
    }
}
