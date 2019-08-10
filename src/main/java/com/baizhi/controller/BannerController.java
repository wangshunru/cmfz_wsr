package com.baizhi.controller;

import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/7/30.
 */
@Controller
@RequestMapping("/banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @RequestMapping("/showAll")
    @ResponseBody
    public Map<String,Object> showAll(Integer page,Integer rows)throws  Exception{
        //按照页号查询数据
        List<Banner> banners = bannerService.showAll(page, rows);
        //查询总条数
        Integer records = bannerService.count();
        //设置总页数
        Integer total = records%rows == 0?records/rows:records/rows+1;
        Map<String, Object> map = new HashMap<>();
        //当前页号
        map.put("page",page);
        //总条数
        map.put("records",records);
        //总页数
        map.put("total",total);
        //具体数据
        map.put("rows",banners);
        return map;
    }

    @RequestMapping("/edit")
    @ResponseBody
    public String edit(String oper, Banner banner)throws Exception{
        String uuid = null;
        if("add".equals(oper)){
            uuid = bannerService.add(banner);
        }else if("edit".equals(oper)){
            uuid = banner.getId();
            bannerService.modify(banner);
        }else{
            bannerService.remove(banner);
        }
        return uuid;
    }


    @RequestMapping("/upload")
    @ResponseBody
    public void  upload(MultipartFile img_path,HttpSession session,String id){
        if(img_path.getOriginalFilename().contains(".")){
            String realPath = session.getServletContext().getRealPath("/image/photo");
            File file = new File(realPath);
            //如果不存在对应的文件夹  创建
            if(! file.exists()){
                file.mkdirs();
            }
            String filename = img_path.getOriginalFilename();
            String name = new Date().getTime()+filename;
            try {
                img_path.transferTo(new File(realPath,name));
            } catch (IOException e) {
                e.printStackTrace();
            }
            //设置banner 图片路径
            Banner banner = new Banner();
            banner.setId(id);
            banner.setImg_path(name);
            bannerService.modify(banner);
        }
    }

    /*
    * 修改轮播图状态
    * */
    @RequestMapping("/status")
    @ResponseBody
    public void  modifyStatus(Banner banner){
        banner = bannerService.queryOne(banner);
        bannerService.modify(banner);
    }
}
