package com.wu.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @Auther: wenchangping
 * @Date: 2019/5/8
 * @Description:
 */
public class EasyPoiUtils {
    private EasyPoiUtils() {
    }

    public static void initExcel(String fileName, HttpServletResponse response) {
        try {
            fileName = fileName + System.currentTimeMillis() + ".xlsx";
            //清除首部的空白行
            response.reset();
            //响应类型
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            //响应编码
            response.setCharacterEncoding("UTF-8");
            //解决文件中文名乱码
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
            Result.error("初始化excel失败");
        }
    }

}
