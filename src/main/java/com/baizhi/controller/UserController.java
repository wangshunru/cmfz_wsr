package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.alibaba.fastjson.JSON;
import com.baizhi.entity.User;
import com.baizhi.entity.UserMap;
import com.baizhi.service.UserService;
import io.goeasy.GoEasy;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/8/1.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/showAll")
    @ResponseBody
    public Map<String,Object> showAll(Integer page, Integer rows,HttpSession session){
        Map<String, Object> map = userService.showAll(page, rows);
        session.setAttribute("users",map);
        return map;
    }

    @RequestMapping("/edit")
    @ResponseBody
    public String edit(User user,String oper){
        String uuid = null;
        if("edit".equals(oper)){
            userService.modify(user);
            uuid = user.getId();
        }
        return uuid;
    }

    @RequestMapping("/upload")
    @ResponseBody
    public void upload(MultipartFile pic_img, HttpSession session, String id, HttpServletRequest request){
        System.out.println(id);
       if(!(pic_img == null)){
           System.out.println(pic_img.getOriginalFilename()+"************");
           String realPath = session.getServletContext().getRealPath("/user/pic_img");
           File file = new File(realPath);
           if(!file.exists()){
               file.mkdirs();
           }
           String filename = pic_img.getOriginalFilename();
           String newPic = new Date().getTime()+"-"+filename;
           try {
               pic_img.transferTo(new File(realPath,newPic));
           } catch (IOException e) {
               e.printStackTrace();
           }
           //拼接url
           //http
           String scheme = request.getScheme();
           //IP
           String serverName = request.getServerName();
           //端口号
           int port = request.getServerPort();
           //项目名
           String contextPath = request.getContextPath();
           String path = scheme+"://"+serverName+":"+port+"/"+contextPath+"/user/pic_img";
           User user = new User();
           user.setId(id);
           System.out.println(path);
           user.setPic_img(path+"/"+newPic);
           userService.modify(user);
       }
    }
    @RequestMapping("/status")
    @ResponseBody
    public void status(User user){
        user = userService.queryOne(user);
        if("1".equals(user.getStatus())){
            user.setStatus("0");
        }else{
            user.setStatus("1");
        }
        userService.modify(user);
    }

    @RequestMapping("/add")
    @ResponseBody
    public void add(User user){
        String id = userService.add(user);
        //按照月份查询结果
        Map<String, Object> map = userService.selectByMonth();
        String content = JSON.toJSONString(map);
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-a1c18ea4094a4f95afcb0c9bbb462176");
        goEasy.publish("myChannel", content);
    }
    @RequestMapping("/export")
    @ResponseBody
    public String userExport(HttpSession session,String mark){
        List<User> users;
        if(mark.equals("0")){
            Map<String,Object> map = (Map<String, Object>) session.getAttribute("users");
            users = (List<User>) map.get("rows");
        }else {
            users = userService.queryAll();
        }

        for (User user : users) {
            String pic_img = user.getPic_img();
            String[] split = pic_img.split("/");
            user.setPic_img("D:/workspace/cmfz_wsr/src/main/webapp/user/pic_img/"+split[split.length-1]);
        }
        //导出
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户信息汇总表", "用户信息"), User.class, users);
        try {
            workbook.write(new FileOutputStream(new File("D:/workspace/cmfz_wsr/src/main/webapp/export/user.xls")));
            workbook.close();
            return "信息导出成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "信息导出失败";
        }
    }

    @RequestMapping("/showByMonth")
    @ResponseBody
    public Map<String,Object> showByMonth(){
        Map<String, Object> map = userService.selectByMonth();
        System.out.println(map);
        return map;
    }

    @RequestMapping("/showByCity")
    @ResponseBody
    public List<UserMap> showByCity(){
        List<UserMap> userMaps = userService.selectByCity();
        return userMaps;
    }


    //发送手机验证码
    @RequestMapping("/phoneMsg")
    @ResponseBody
    public String phoneMsg(String phoneNumbers,HttpSession session){
        String message = userService.phoneMsg(phoneNumbers, session);
        System.out.println(message);
        return message;
    }
}
