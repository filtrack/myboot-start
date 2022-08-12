package com.starter.app.doc;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.stream.StreamUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

public class MD {
    public static void main(String[] args) {
        // 1.获取上传文件输入流
        File file = new File("D:/codeview.xlsx");
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
// 2.应用HUtool ExcelUtil获取ExcelReader指定输入流和sheet
        ExcelReader excelReader = ExcelUtil.getReader(file);
// 可以加上表头验证
// 3.读取第二行到最后一行数据
        List<List<Object>> read = excelReader.read(2, excelReader.getRowCount());
        int i = 0;
        for (List<Object> objects : read) {
            String title = objects.get(2).toString();
            String clz = objects.get(6).toString();
            String lines = objects.get(7).toString();
            String code =  objects.get(8).toString();
            System.out.println("#####"+title+"\n");
            System.out.println("######"+title+"\n");
            System.out.println("######"+lines+"\n");
            System.out.println("```\n"+code+"\n```\n\n");

        }
    }
}
