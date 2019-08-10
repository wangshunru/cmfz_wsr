package com.baizhi.controller;

import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import com.baizhi.util.VideoUtil;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.net.URLEncoder;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/7/31.
 */
@Controller
@RequestMapping("/chapter")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @RequestMapping("/showAll")
    @ResponseBody
    public Map<String,Object> showAll(Integer page,Integer rows,String album_id){
        List<Chapter> chapters = chapterService.showAll(page, rows, album_id);
        Integer records = chapterService.showCount();
        Integer total = records%rows==0?records/rows:records/rows+1;
        Map<String, Object> map = new HashMap<>();
        map.put("page",page);
        map.put("records",records);
        map.put("total",total);
        map.put("rows",chapters);
        return map;
    }

    @RequestMapping("/edit")
    @ResponseBody
    public String edit(Chapter chapter,String oper){
        String uuid = null;
        if("add".equals(oper)){
            uuid = chapterService.add(chapter);
        }else {
            chapterService.delete(chapter);
        }
        return uuid;
    }

    @RequestMapping("/upload")
    @ResponseBody
    public Map<String, Object> upload(MultipartFile url, HttpSession session,String id){
        Map<String, Object> map = new HashMap<>();
        Chapter chapter = new Chapter();
        chapter.setId(id);
        try{
            //获取文件名
            String filename = url.getOriginalFilename();

            //根据相对路径获取绝对路径
            String realPath = session.getServletContext().getRealPath("/audios");

            //判断文件夹是否存在
            File file = new File(realPath);
            if(!file.exists()){
                file.mkdir();
            }
            String sname = new Date().getTime()+"-"+filename;

        /*    //获取音频大小    zijie
            long size = url.getSize();
            //=========2========
            DecimalFormat format = new DecimalFormat("0.00");
            String str = String.valueOf(size);
            Double dd = Double.valueOf(str)/1024/1024;
            String sizess = format.format(dd)+"MB";

            //=========3========
            DecimalFormat df = new DecimalFormat("0.00");
            Double a = Double.valueOf(String.valueOf(size))/1024/1024;
            if(a>1024){
                a= a/1024;
                System.out.println("文件大小为：  "+df.format(a)+"GB");
            }else
                System.out.println("文件大小为：  "+df.format(a)+"MB");
*/
            //=========4========
            BigDecimal sizez = new BigDecimal(url.getSize());
            BigDecimal mod = new BigDecimal(1024);

            //除两个1024，保留两位小数，进行四舍五入
            BigDecimal setScale = sizez.divide(mod).divide(mod).setScale(2, BigDecimal.ROUND_HALF_UP);
            System.out.println("文件大小为：  "+setScale+"MB");

            String newsize = setScale+"MB";
            chapter.setNewsize(newsize);
            //文件上传
            url.transferTo(new File(realPath,sname));
            chapter.setUrl(sname);
            //获取文件时长
            String path = realPath+"/"+sname;
            long duration1 = VideoUtil.getDuration(path);
            //设置时长
            String duration =duration1/60+"分"+duration1%60+"秒";
            chapter.setDuration(duration);
            chapterService.modify(chapter);
            map.put("success", 200);
            map.put("message", "上传成功");
        } catch (Exception e) {
            map.put("success", 400);
            map.put("message", "上传失败");
        }
        return map;
    }

    @RequestMapping("/download")
    @ResponseBody
    public  void  download(String url, HttpServletRequest request, HttpServletResponse response,String value){
        //获取存放音频的文件夹路径
        String realPath = request.getRealPath("/audios");
        //获取输入流，将目标文件读入流中
        FileInputStream inputStream;
        ServletOutputStream outputStream;
        try {
            inputStream = new FileInputStream(new File(realPath, url));
            //获取文件输出流
            outputStream = response.getOutputStream();
            if("att".equals(value)){
                response.setHeader("content-disposition","attachment;url="+URLEncoder.encode(url,"utf-8"));
            }else{
                response.setHeader("content-disposition","inline;url="+URLEncoder.encode(url,"utf-8"));
            }
            IOUtils.copy(inputStream,outputStream);
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
