package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
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
 * Created by Administrator on 2019/7/31.
 */
@Controller
@RequestMapping("album")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @RequestMapping("/showAll")
    @ResponseBody
    public Map<String,Object> showAll(Integer page, Integer rows)throws Exception{
        Map<String, Object> map = new HashMap<>();
        //获取数据
        List<Album> albums = albumService.showAll(page, rows);
        //获取总条数
        Integer records = albumService.showCount();
        //总页数
        Integer total = records%rows == 0?records/rows:records/rows+1;
        map.put("page",page);
        map.put("total",total);
        map.put("records",records);
        map.put("rows",albums);
        return map;
    }

    @RequestMapping("/edit")
    @ResponseBody
    public String edit(Album album, String oper){
        String uuid = null;
        if("add".equals(oper)){
            uuid = albumService.add(album);
        }else if ("edit".equals(oper)){
            albumService.modify(album);
        }
        return uuid;
    }

    @RequestMapping("/upload")
    @ResponseBody
    public void upload(String id, MultipartFile cover_img,HttpSession session){
        if(cover_img.getOriginalFilename().contains(".")){
            String realPath = session.getServletContext().getRealPath("/album/cover");
            File file = new File(realPath);
            //如果不存在
            if(!file.exists()){
                file.mkdirs();
            }
            String cover = new Date().getTime()+cover_img.getOriginalFilename();
            File file1 = new File(realPath, cover);
            Album album = new Album();
            try {
                album.setId(id);
                album.setCover_img(cover);
                albumService.modify(album);
                cover_img.transferTo(file1);
                System.out.println(album);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
