package com.baizhi;

import com.baizhi.dao.BannerDAO;
import com.baizhi.entity.Admin;
import com.baizhi.entity.Banner;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CmfzWsrApplicationTests {

	@Autowired
	private BannerDAO bannerDAO;
	@Test
	public void testPoi() {

		//创建一个文档
		Workbook workbook = new HSSFWorkbook();
		//创建工作簿
		Sheet sheet = workbook.createSheet();
		//创建一行
		Row row = sheet.createRow(0);
		//创建单元格
		Cell cell = row.createCell(0);
		//内容填充
		cell.setCellValue("啦啦啦");

		try {
			workbook.write(new FileOutputStream(new File("D:\\后期项目\\day7\\笔记\\testPoi.xls")));
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testPoiExport() {

		List<Banner> banners = bannerDAO.selectAll(1, 10);
		HSSFWorkbook workbook = new HSSFWorkbook();
		//创建工作簿 参数  工作簿名称
		HSSFSheet sheet = workbook.createSheet("管理员信息表");
		//设置单元格宽度  宽度单位1/256
		sheet.setColumnWidth(5,15*256);
		sheet.setColumnWidth(4,4*256);
		sheet.setColumnWidth(3,15*256);
		sheet.setColumnWidth(2,20*256);
		sheet.setColumnWidth(1,10*256);
		sheet.setColumnWidth(0,35*256);
		//创建标题行
		Row row0 = sheet.createRow(0);
		//设置行高  单位1/20
		row0.setHeight((short) 400);
		//创建单元格
		Cell cell0 = row0.createCell(0);
		//填充内容
		cell0.setCellValue("管理员信息汇总表");
		//设置字体样式
		CellStyle cellStyle1 = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setBold(true); //加粗
		font.setColor(Font.COLOR_RED); //字体颜色
		font.setFontHeightInPoints((short)20);
		font.setFontName("楷体");
		cellStyle1.setFont(font);
		cell0.setCellStyle(cellStyle1);
		//合并单元格
		CellRangeAddress cellAddresses = new CellRangeAddress(0,0,0,5);
		sheet.addMergedRegion(cellAddresses);
		//设置目录行
		HSSFRow rowTitle = sheet.createRow(1);
		String[] title = {"id","标题","轮播图","描述","状态","上传日期"};
		for (int i = 0; i < title.length; i++) {
			rowTitle.createCell(i).setCellValue(title[i]);
		}
		//创建样式对象
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		//设置日期
		DataFormat dataFormat = workbook.createDataFormat();
		cellStyle.setDataFormat(dataFormat.getFormat("yyyy-MM-dd"));
		//处理数据
		for (int i = 0; i < banners.size(); i++) {
			HSSFRow rowData = sheet.createRow(i + 2);
			rowData.createCell(0).setCellValue(banners.get(i).getId());
			rowData.createCell(1).setCellValue(banners.get(i).getTitle());
			rowData.createCell(2).setCellValue(banners.get(i).getImg_path());
			rowData.createCell(3).setCellValue(banners.get(i).getDescription());
			rowData.createCell(4).setCellValue(banners.get(i).getStatus());
			HSSFCell cell5 = rowData.createCell(5);
			cell5.setCellValue(banners.get(i).getUp_date());
			cell5.setCellStyle(cellStyle);
		}

		//输出
		try {
			workbook.write(new FileOutputStream("D:\\后期项目\\day7\\笔记\\testPoi.xls"));
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testPoiImport() {
		try {
			Workbook workbook = new HSSFWorkbook(new FileInputStream(new File("D:\\后期项目\\day7\\笔记\\testPoi.xls")));
			//获取工作簿
			Sheet sheet = workbook.getSheet("管理员信息表");
			ArrayList<Banner> banners = new ArrayList<>();
			for (int i = 2; i <= sheet.getLastRowNum(); i++) {
				Banner banner = new Banner();
				//获取行
				Row row = sheet.getRow(i);
				//填充banner 对象
				banner.setId(row.getCell(0).getStringCellValue());
				banner.setTitle(row.getCell(1).getStringCellValue());
				banner.setImg_path(row.getCell(2).getStringCellValue());
				banner.setDescription(row.getCell(3).getStringCellValue());
				banner.setStatus(row.getCell(4).getStringCellValue());
				banner.setUp_date(row.getCell(5).getDateCellValue());
				banners.add(banner);
			}
			workbook.close();
			for (Banner banner : banners) {
				System.out.println(banner);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
