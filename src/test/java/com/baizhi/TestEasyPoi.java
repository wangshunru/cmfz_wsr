package com.baizhi;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.dao.BannerDAO;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2019/8/5.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestEasyPoi {

    @Test
    public void testEasyPoi1(){
        List<Student> students = new ArrayList<>();
        students.add(new Student("1","zhang3",18,89.9,new Date()));
        students.add(new Student("2","li4",18,87.9,new Date()));
        students.add(new Student("3","wang5",21,92.1,new Date()));
        students.add(new Student("4","zhao6",29,96.2,new Date()));

        List<Teacher> teachers = new ArrayList<>();
        teachers.add(new Teacher("1","AAA",30,students));
        teachers.add(new Teacher("2","BBB",29,students));
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("讲师信息汇总表", "老师信息表","授课教师"), Teacher.class, teachers);
        try {
            workbook.write(new FileOutputStream(new File("D:\\后期项目\\day7\\笔记\\testEasyPoi.xls")));
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Autowired
    private  BannerDAO bannerDAO;
    @Test
    public void testEasyPoi2(){
        bannerDAO.selectAll(1,10);
    }
}
