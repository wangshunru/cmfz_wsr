package com.baizhi.controller;

import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2019/8/1.
 */
@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping("/showAll")
    @ResponseBody
    public Map<String,Object> showAll(Integer page,Integer rows){
        Map<String, Object> map = articleService.showAll(page, rows);
        return map;
    }
    @RequestMapping("/edit")
    @ResponseBody
    public void edit(Article article,String oper){
        if("del".equals(oper)){
            articleService.remove(article);
        }else{
            if(article.getInsert_img() == null){
                article.setInsert_img("");
            }
            articleService.modify(article);
        }
    }

    @RequestMapping("/add")
    @ResponseBody
    public void add(Article article){
        articleService.add(article);
    }

   /* @RequestMapping("/insertImgUpload")
    @ResponseBody
    public void upload(MultipartFile coverImg,HttpSession session,String id){
        articleService.upload(coverImg, session, id);
    }*/
    @RequestMapping("/status")
    @ResponseBody
    public void status(Article article){
        article = articleService.showOne(article);
        if("0".equals(article.getStatus())){
            article.setStatus("1");
        }else {
            article.setStatus("0");
        }
        articleService.modify(article);
    }

    @RequestMapping("/upload")
    @ResponseBody
    public Map<String,Object> upload(MultipartFile upload, HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        String realPath = request.getSession().getServletContext().getRealPath("/article/picture");
        File file = new File(realPath);
        if (!file.exists()){
            file.mkdirs();
        }
        String filename = new Date().getTime()+"-"+upload.getOriginalFilename();
        File file1 = new File(realPath, filename);
        //拼接url
        //http
        String scheme = request.getScheme();
        //IP
        String serverName = request.getServerName();
        //端口号
        int port = request.getServerPort();
        //项目名
        String contextPath = request.getContextPath();
        //http://localhost:8989/
        String url = scheme+"://"+serverName+":"+port+"/"+contextPath+"/article/picture"+filename;
        try {
            upload.transferTo(file1);
            map.put("error",0);
            map.put("url",url);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("error",1);
            map.put("massage","上传错误");
        }
        return map;
    }

    /*
    * {
	"moveup_dir_path": "",
	"current_dir_path": "",
	"current_url": "\/ke4\/php\/..\/attached\/",
	"total_count": 5,
	"file_list": [{
		"is_dir": false,
		"has_file": false,
		"filesize": 245132,
		"dir_path": "",
		"is_photo": true,
		"filetype": "jpg",
		"filename": "2009321101428.jpg",
		"datetime": "2018-06-06 00:36:39"
	}}
    * */

    @RequestMapping("/showAllPicture")
    @ResponseBody
    public  Map<String,Object> showAllPicture(HttpServletRequest request){
        Map<String, Object> hashMap = new HashMap<>();
        String realPath = request.getSession().getServletContext().getRealPath("/article/picture");
        File file = new File(realPath);
        String[] files = file.list();
        //拼接list
        List<Object> list = new ArrayList<>();
        for (String s : files) {
            Map<String, Object> map = new HashMap<>();
            map.put("is_dir",false);
            map.put("has_file",false);
            //文件长度
            map.put("filesize",s.length());
            map.put("is_photo",true);
                                // 获取后缀
            map.put("filetype", FilenameUtils.getExtension(s));
            map.put("filename",s);
            //获取上传时间
            String[] split = s.split("-");
            String time = split[0];
            //转为lang 类型
            long parseLong = Long.parseLong(time);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
            String format = dateFormat.format(parseLong);
            map.put("datetime",format);
            list.add(map);
        }
        //获取网络路径
        //拼接url
        //http
        String scheme = request.getScheme();
        //IP
        String serverName = request.getServerName();
        //端口号
        int port = request.getServerPort();
        //项目名
        String contextPath = request.getContextPath();
        //http://localhost:8989/
        String url = scheme+"://"+serverName+":"+port+"/"+contextPath+"/article/picture/";
        hashMap.put("current_url",url);
        hashMap.put("total_count",list.size());
        hashMap.put("file_list",list);
        return hashMap;
    }
}















