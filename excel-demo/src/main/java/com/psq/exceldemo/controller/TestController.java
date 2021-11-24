package com.psq.exceldemo.controller;

import com.alibaba.excel.EasyExcel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

/**
 * TestController.java
 * Description: 下载测试Controller
 *
 * @author Peng Shiquan
 * @date 2021/11/24
 */
@RestController
public class TestController {

    @GetMapping("download")
    public void downloadExcel(HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        //EasyExcel.write(response.getOutputStream(), DownloadData.class).sheet("模板").doWrite(data());
        List<List<String>> head = new ArrayList<>();
        head.add(new ArrayList<String>() {{
            add("字符串");
        }});
        head.add(new ArrayList<String>() {{
            add("数字");
        }});
        head.add(new ArrayList<String>() {{
            add("日期");
        }});
        List<List<Object>> data = new ArrayList<>();
        data.add(new ArrayList<Object>() {{
            add("haha");
            add(123);
            add(new Date());
        }});
        data.add(new ArrayList<Object>() {{
            add("hehe");
            add(456);
            add(new Date());
        }});
        data.add(new ArrayList<Object>() {{
            add("lisi");
            add(789);
            add(new Date());
        }});
        EasyExcel.write(response.getOutputStream()).head(head).sheet("模板").doWrite(data);
    }
}
