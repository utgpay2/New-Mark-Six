package com.central.backend.service.impl;

import com.central.backend.mapper.QuizOrdersMapper;
import com.central.backend.model.vo.UserBettingDetailedReportFormsVo;
import com.central.backend.model.vo.UserMoneyDetailedReportFormsVo;
import com.central.backend.model.vo.UserReportFormsVo;
import com.central.backend.service.IQuizOrdersService;
import com.central.backend.util.ExcelUtils;
import com.central.common.model.QuizOrders;
import com.central.common.model.enums.MbChangeTypeEnum;
import com.central.common.model.enums.OrderStatusEnum;
import org.springframework.stereotype.Service;
import com.central.common.model.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.service.impl.SuperServiceImpl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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
    public PageResult<UserReportFormsVo> userReportForms(Map<String, Object> params) {
        Page<QuizOrders> page = new Page<>(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));
        List<UserReportFormsVo> list  =  baseMapper.userReportForms(page, params);
        return PageResult.<UserReportFormsVo>builder().data(list).count(page.getTotal()).build();

    }

    @Override
    public void userReportFormsExport(Map<String, Object> params, HttpServletResponse httpServletResponse) {
        Page<QuizOrders> page = new Page<>(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));
        List<UserReportFormsVo> list  =  baseMapper.userReportForms(page, params);

        try {
            ExcelUtils.write(httpServletResponse,"user", UserReportFormsVo.class,list);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public PageResult<UserMoneyDetailedReportFormsVo> userMoneyDetailed(Map<String, Object> params) {
        Page<QuizOrders> page = new Page<>(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));

        List<UserMoneyDetailedReportFormsVo> list  =  baseMapper.userMoneyDetailed(page, params);
        for(UserMoneyDetailedReportFormsVo dto:list){
            MbChangeTypeEnum mbChangeTypeEnum= MbChangeTypeEnum.getMbChangeTypeEnumByType(Integer.parseInt(dto.getOrderType()));
            if(mbChangeTypeEnum.getAddOrSub()==-1){
                dto.setExpenditure(dto.getIncome());
                dto.setIncome(BigDecimal.ZERO);
            }else {
                dto.setExpenditure(BigDecimal.ZERO);
            }
            dto.setOrderType(mbChangeTypeEnum.getName());
        }

        return PageResult.<UserMoneyDetailedReportFormsVo>builder().data(list).count(page.getTotal()).build();

    }

    @Override
    public void userMoneyDetailedExport(Map<String, Object> params,HttpServletResponse httpServletResponse) {
        Page<QuizOrders> page = new Page<>(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));

        List<UserMoneyDetailedReportFormsVo> list  =  baseMapper.userMoneyDetailed(page, params);
        for(UserMoneyDetailedReportFormsVo dto:list){
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
            ExcelUtils.write(httpServletResponse,"userMoneyDetailed", UserMoneyDetailedReportFormsVo.class,list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PageResult userBettingDetailed(Map<String, Object> params) {

        Page<QuizOrders> page = new Page<>(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));
        List<UserBettingDetailedReportFormsVo> list  =  baseMapper.userBettingDetailed(page, params);
        for(UserBettingDetailedReportFormsVo userbetting:list){
            userbetting.setStatus(OrderStatusEnum.getOptions().get(Integer.parseInt(userbetting.getStatus())));
        }
        return PageResult.<UserBettingDetailedReportFormsVo>builder().data(list).count(page.getTotal()).build();

    }

    @Override
    public void userBettingDetailedExport(Map<String, Object> params, HttpServletResponse httpServletResponse) {
        Page<QuizOrders> page = new Page<>(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));
        List<UserBettingDetailedReportFormsVo> list  =  baseMapper.userBettingDetailed(page, params);
        for(UserBettingDetailedReportFormsVo userbetting:list){
            userbetting.setStatus(OrderStatusEnum.getOptions().get(Integer.parseInt(userbetting.getStatus())));
        }
        try {
            ExcelUtils.write(httpServletResponse,"userBettingDetailed", UserBettingDetailedReportFormsVo.class,list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
