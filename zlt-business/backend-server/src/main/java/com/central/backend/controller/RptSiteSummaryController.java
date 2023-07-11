package com.central.backend.controller;

import cn.hutool.http.HttpResponse;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.central.backend.co.RptSiteSummaryCo;
import com.central.backend.model.dto.UserReportFormsDto;
import com.central.backend.service.IQuizOrdersService;
import com.central.backend.service.IRptSiteSummaryService;
import com.central.backend.util.ExcelUtils;
import com.central.backend.vo.RptSiteSummaryVo;
import com.central.common.model.PageResult;
import com.central.common.model.Result;
import com.central.common.model.RptSiteSummary;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.*;

@Slf4j
@RestController
@Api(tags = "运营报表api")
@Validated
@RequestMapping("/summary")
public class RptSiteSummaryController {


    @Autowired
    private IQuizOrdersService quizOrderSonService;

    /**
     * 列表
     */
    @ApiOperation(value = "会员报表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "分页结束位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "siteId", value = "分页结束位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = true, dataType = " Date"),
            @ApiImplicitParam(name = "endTime", value = "开始时间", required = true, dataType = " Date")

    })
    @GetMapping("/user")
    public PageResult userReportForms(@RequestParam Map<String, Object> params) {

        return quizOrderSonService.userReportForms(params);
    }

    /**
     * 列表
     */
    @ApiOperation(value = "会员报表导出")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "分页结束位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "siteId", value = "分页结束位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = true, dataType = " Date"),
            @ApiImplicitParam(name = "endTime", value = "开始时间", required = true, dataType = " Date")

    })
    @GetMapping("/user/export")
    public void userReportFormsExport(@RequestParam Map<String, Object> params, HttpServletResponse response) {

        quizOrderSonService.userReportFormsExport(params,response);

    }

}
