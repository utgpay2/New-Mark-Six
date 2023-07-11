package com.central.backend.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.central.backend.mapper.QuizOrdersMapper;
import com.central.backend.model.dto.UserReportFormsDto;
import com.central.backend.service.IQuizOrdersService;
import com.central.backend.util.ExcelUtils;
import com.central.common.model.QuizOrders;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;
import com.central.common.model.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.service.impl.SuperServiceImpl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.collections4.MapUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;


/**
 * 
 *
 * @author zlt
 * @date 2023-05-09 18:43:48
 */
@Slf4j
@Service
public class QuizOrdersServiceImpl extends SuperServiceImpl<QuizOrdersMapper, QuizOrders> implements IQuizOrdersService {
    /**
     * 列表
     * @param params
     * @return
     */
    @Override
    public PageResult<QuizOrders> findList(Map<String, Object> params){
        Page<QuizOrders> page = new Page<>(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));
        List<QuizOrders> list  =  baseMapper.findList(page, params);
        return PageResult.<QuizOrders>builder().data(list).count(page.getTotal()).build();
    }

    @Override
    public PageResult userReportForms(Map<String, Object> params) {
        Page<QuizOrders> page = new Page<>(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));
        List<UserReportFormsDto> list  =  baseMapper.userReportForms(page, params);
        return PageResult.<UserReportFormsDto>builder().data(list).count(page.getTotal()).build();

    }

    @Override
    public void userReportFormsExport(Map<String, Object> params, HttpServletResponse httpServletResponse) {
        Page<QuizOrders> page = new Page<>(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));
        List<UserReportFormsDto> list  =  baseMapper.userReportForms(page, params);

        try {
            ExcelUtils.write(httpServletResponse,"会员报表",UserReportFormsDto.class,list);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
