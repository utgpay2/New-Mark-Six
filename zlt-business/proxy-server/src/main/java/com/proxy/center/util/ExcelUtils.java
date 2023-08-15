package com.proxy.center.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

public class ExcelUtils {

    /**
     * 将列表以 Excel 响应给前端
     *
     * @param response 响应
     * @param filename 文件名
     * @param sheetName Excel sheet 名
     * @param head Excel head 头
     * @param data 数据列表哦
     * @param <T> 泛型，保证 head 和 data 类型的一致性
     * @throws IOException 写入失败的情况
     */
    public static <T> void write(HttpServletResponse response,  String sheetName,
                                 Class<T> head, List<T> data) throws IOException {

        // 设置响应头等
        response.setHeader("Content-Disposition", "attachment;filename=" +
                URLEncoder.encode(sheetName+System.currentTimeMillis()+".xlsx", "UTF-8"));
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("UTF-8");
        // 导出数据
        EasyExcel.write(response.getOutputStream())
                .autoCloseStream(false) // 不要自动关闭，交给 Servlet 自己处理
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy()) // 基于 column 长度，自动适配。最大 255 宽度
                .head(head).sheet(sheetName).doWrite(data);

    }

}
