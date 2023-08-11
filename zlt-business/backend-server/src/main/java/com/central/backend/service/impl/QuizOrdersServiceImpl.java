package com.central.backend.service.impl;

import com.central.backend.mapper.QuizOrdersMapper;
import com.central.backend.model.vo.UserBettingDetailedReportFormsVo;
import com.central.backend.model.vo.UserMoneyDetailedReportFormsVo;
import com.central.backend.model.vo.UserReportFormsVo;
import com.central.backend.service.IQuizOrdersService;
import com.central.backend.service.ISiteService;
import com.central.backend.util.ExcelUtils;
import com.central.common.constant.CommonConstant;
import com.central.common.model.QuizOrders;
import com.central.common.model.Site;
import com.central.common.model.enums.MbChangeTypeEnum;
import com.central.common.model.enums.OrderStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.central.common.model.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.service.impl.SuperServiceImpl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

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


    @Autowired
    ISiteService siteService;

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
        List<UserReportFormsVo> list  =  baseMapper.userReportFormsPage(page, params);
        List<Map<String, Object>> Withdrawals;
        if(list.size()>0){
            List<String> usernames= list.stream().map(userReportFormsVo ->userReportFormsVo.getUsername()).collect(Collectors.toList());
            params.put("usernames",usernames);
            Withdrawals=baseMapper.getSumWithdrawalByUsernames(params);
        }else {
            Withdrawals= new ArrayList<>();
        }

        //计算汇总
        Map<String,BigDecimal> other=baseMapper.userReportFormsSummary( params);
        BigDecimal withdrawalSummary=baseMapper.getSumWithdrawalByUsernamesSummary( params);
        other.put("withdrawalSummary",withdrawalSummary);


        for(UserReportFormsVo userReportFormsVo:list){
            if(userReportFormsVo.getWinMount().floatValue()!=0 && userReportFormsVo.getOrderPrice().floatValue()!=0){
                userReportFormsVo.setWinRate(userReportFormsVo.getWinMount().divide(userReportFormsVo.getOrderPrice(),2,BigDecimal.ROUND_HALF_UP));
            }else {
                userReportFormsVo.setWinRate(BigDecimal.ZERO);
            }

            for(Map<String, Object> map:Withdrawals){
                if(map.get("username").equals(userReportFormsVo.getUsername())){
                    userReportFormsVo.setWithdrawal(new BigDecimal(map.get("withdrawal").toString()));
                    break;
                }
            }
            if(userReportFormsVo.getWithdrawal()==null){
                userReportFormsVo.setWithdrawal(BigDecimal.ZERO);
            }

        }

        return PageResult.<UserReportFormsVo>builder().data(list).count(page.getTotal()).other(other).build();

    }

    @Override
    public void userReportFormsExport(Map<String, Object> params, HttpServletResponse httpServletResponse) {

        List<UserReportFormsVo> list  =  baseMapper.userReportForms( params);
        List<Map<String, Object>> Withdrawals;
        if(list.size()>0){
            List<String> usernames= list.stream().map(userReportFormsVo ->userReportFormsVo.getUsername()).collect(Collectors.toList());
            params.put("usernames",usernames);
            Withdrawals=baseMapper.getSumWithdrawalByUsernames(params);
        }else {
            Withdrawals= new ArrayList<>();
        }
        for(UserReportFormsVo userReportFormsVo:list){
            if(userReportFormsVo.getWinMount().floatValue()!=0 && userReportFormsVo.getOrderPrice().floatValue()!=0){
                userReportFormsVo.setWinRate(userReportFormsVo.getWinMount().divide(userReportFormsVo.getOrderPrice(),2,BigDecimal.ROUND_HALF_UP));
            }else {
                userReportFormsVo.setWinRate(BigDecimal.ZERO);
            }
            for(Map<String, Object> map:Withdrawals){
                if(map.get("username").equals(userReportFormsVo.getUsername())){
                    userReportFormsVo.setWithdrawal(new BigDecimal(map.get("withdrawal").toString()));
                    break;
                }
            }
            if(userReportFormsVo.getWithdrawal()==null){
                userReportFormsVo.setWithdrawal(BigDecimal.ZERO);
            }
        }
        try {
            ExcelUtils.write(httpServletResponse,"user", UserReportFormsVo.class,list);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public PageResult<UserMoneyDetailedReportFormsVo> userMoneyDetailed(Map<String, Object> params) {
        Page<QuizOrders> page = new Page<>(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));

        List<UserMoneyDetailedReportFormsVo> list  =  baseMapper.userMoneyDetailedPage(page, params);

        //计算汇总
        Map<String,BigDecimal> other=baseMapper.userMoneyDetailedSummary(params);

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

        return PageResult.<UserMoneyDetailedReportFormsVo>builder().data(list).count(page.getTotal()).other(other).build();

    }

    @Override
    public void userMoneyDetailedExport(Map<String, Object> params,HttpServletResponse httpServletResponse) {


        List<UserMoneyDetailedReportFormsVo> list  =  baseMapper.userMoneyDetailed( params);
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
        Site site=siteService.getById(params.get("siteId").toString());
        Page<QuizOrders> page = new Page<>(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));
        List<UserBettingDetailedReportFormsVo> list  =  baseMapper.userBettingDetailedPage(page, params);
        //计算汇总
        Map<String,BigDecimal> other=baseMapper.userBettingDetailedSummary( params);


        for(UserBettingDetailedReportFormsVo userbetting:list){

            userbetting.setStatus(OrderStatusEnum.getOptions().get(Integer.parseInt(userbetting.getStatus())));
            userbetting.setFeePercentage(site.getFeePercentage());
            BigDecimal commission=BigDecimal.ZERO;
            if(userbetting.getStatus().equals(OrderStatusEnum.ORDER_FOUR.getStatus().toString())){
                commission=userbetting.getTotalPrice().multiply(new BigDecimal(site.getFeePercentage())).divide(CommonConstant.ONE_HUNDRED,2,BigDecimal.ROUND_HALF_UP);

            }
            userbetting.setCommission(commission);
        }
        return PageResult.<UserBettingDetailedReportFormsVo>builder().data(list).count(page.getTotal()).other(other).build();

    }

    @Override
    public void userBettingDetailedExport(Map<String, Object> params, HttpServletResponse httpServletResponse) {

        Site site=siteService.getById(params.get("siteId").toString());
        List<UserBettingDetailedReportFormsVo> list  =  baseMapper.userBettingDetailed( params);


        for(UserBettingDetailedReportFormsVo userbetting:list){

            userbetting.setStatus(OrderStatusEnum.getOptions().get(Integer.parseInt(userbetting.getStatus())));
            userbetting.setFeePercentage(site.getFeePercentage());
            BigDecimal commission=BigDecimal.ZERO;
            if(userbetting.getStatus().equals(OrderStatusEnum.ORDER_FOUR.getStatus().toString())){
                commission=userbetting.getTotalPrice().multiply(new BigDecimal(site.getFeePercentage())).divide(CommonConstant.ONE_HUNDRED,2,BigDecimal.ROUND_HALF_UP);

            }
            userbetting.setCommission(commission);
        }


        try {
            ExcelUtils.write(httpServletResponse,"userBettingDetailed", UserBettingDetailedReportFormsVo.class,list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
