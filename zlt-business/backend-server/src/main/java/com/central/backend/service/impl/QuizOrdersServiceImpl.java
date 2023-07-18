package com.central.backend.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.central.backend.mapper.MoneyLogMapper;
import com.central.backend.mapper.QuizOrdersMapper;
import com.central.backend.model.dto.UserMoneyDetailedReportFormsDto;
import com.central.backend.model.dto.UserReportFormsDto;
import com.central.backend.service.IQuizOrdersService;
import com.central.backend.util.ExcelUtils;
import com.central.common.model.MoneyLog;
import com.central.common.model.QuizOrders;
import com.central.common.model.enums.MbChangeTypeEnum;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.central.common.model.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.service.impl.SuperServiceImpl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
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
            ExcelUtils.write(httpServletResponse,"user",UserReportFormsDto.class,list);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public PageResult userMoneyDetailed(Map<String, Object> params) {
        Page<QuizOrders> page = new Page<>(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));

        List<UserMoneyDetailedReportFormsDto> list  =  baseMapper.userMoneyDetailed(page, params);
        for(UserMoneyDetailedReportFormsDto dto:list){
            MbChangeTypeEnum mbChangeTypeEnum= MbChangeTypeEnum.getMbChangeTypeEnumByType(Integer.parseInt(dto.getOrderType()));
            if(mbChangeTypeEnum.getAddOrSub()==-1){
                dto.setExpenditure(dto.getIncome());
                dto.setIncome(BigDecimal.ZERO);
            }else {
                dto.setExpenditure(BigDecimal.ZERO);
            }
            dto.setOrderType(mbChangeTypeEnum.getName());
        }

        return PageResult.<UserMoneyDetailedReportFormsDto>builder().data(list).count(page.getTotal()).build();

    }

    @Override
    public void userMoneyDetailedExport(Map<String, Object> params,HttpServletResponse httpServletResponse) {
        Page<QuizOrders> page = new Page<>(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));

        List<UserMoneyDetailedReportFormsDto> list  =  baseMapper.userMoneyDetailed(page, params);
        for(UserMoneyDetailedReportFormsDto dto:list){
            MbChangeTypeEnum mbChangeTypeEnum= MbChangeTypeEnum.getMbChangeTypeEnumByType(Integer.parseInt(dto.getOrderType()));
            if(mbChangeTypeEnum.getAddOrSub()==-1){
                dto.setExpenditure(dto.getIncome());
                dto.setIncome(BigDecimal.ZERO);
            }else {
                dto.setExpenditure(BigDecimal.ZERO);
            }
            dto.setOrderType(mbChangeTypeEnum.getName());
        }
        try {
            ExcelUtils.write(httpServletResponse,"userMoneyDetailed",UserMoneyDetailedReportFormsDto.class,list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PageResult userBettingDetailed(Map<String, Object> params) {

       /* Page<QuizOrders> page = new Page<>(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));
        List<QuizOrders> list  =  baseMapper.userBettingDetailed(page, params);
        return PageResult.<QuizOrders>builder().data(list).count(page.getTotal()).build();*/
       return null;
    }


}
