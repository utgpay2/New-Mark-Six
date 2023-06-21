package com.central.marksix.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.model.MoneyLog;
import com.central.common.model.enums.MbChangeTypeEnum;
import com.central.common.model.enums.SortEnum;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.marksix.entity.PornPageResult;
import com.central.marksix.entity.vo.MbChangeRecordDetailsVo;
import com.central.marksix.entity.vo.MbChangeRecordVo;
import com.central.marksix.enums.MbChangeTypeExtendEnum;
import com.central.marksix.mapper.MoneyLogMapper;
import com.central.marksix.service.IMoneyLogService;
import com.central.marksix.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Slf4j
@Service
public class MoneyLogServiceImpl extends SuperServiceImpl<MoneyLogMapper, MoneyLog> implements IMoneyLogService {


    @Autowired
    ISysUserService sysUserService;

    @Override
    public PornPageResult<MbChangeRecordVo> getByUserId(Long userId, Integer currPage, Integer pageSize) {
        Page<MoneyLog> page = new Page<>(currPage, pageSize);
        LambdaQueryWrapper<MoneyLog> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(MoneyLog::getUserId, MoneyLog::getOrderType, MoneyLog::getCreateTime, MoneyLog::getMoney);
        wrapper.eq(MoneyLog::getUserId, userId);
        wrapper.orderByDesc(MoneyLog::getCreateTime);

        Page<MoneyLog> moneyLogPage = this.baseMapper.selectPage(page, wrapper);
        List<MoneyLog> moneyLogPageRecords = moneyLogPage.getRecords();
        List<MbChangeRecordVo> changeRecordVos = new ArrayList<>();
        for (MoneyLog moneyLogPageRecord : moneyLogPageRecords) {
            MbChangeRecordVo changeRecordVo = new MbChangeRecordVo();
            changeRecordVo.setDateTime(moneyLogPageRecord.getCreateTime());
            changeRecordVo.setMbs(moneyLogPageRecord.getMoney());
            changeRecordVo.setAddOrSubTypeInt(MbChangeTypeExtendEnum.getMbChangeTypeEnumByType(moneyLogPageRecord.getOrderType()).getAddOrSub());
            changeRecordVo.setAddOrSubType(MbChangeTypeExtendEnum.getLanguageAddOrSubNameByType(moneyLogPageRecord.getOrderType()));

            changeRecordVos.add(changeRecordVo);
        }

        Long total = page.getTotal();
        Integer totalPage = (int) (total % pageSize == 0 ? total / pageSize : total / pageSize + 1);

        return PornPageResult.<MbChangeRecordVo>builder()
                .data(changeRecordVos)
                .currPage(currPage)
                .pageSize(pageSize)
                .count(total)
                .totalPage(totalPage)
                .build();
    }

    @Override
    public  PornPageResult<MbChangeRecordDetailsVo> transferRecords(String username, Integer[] userIds, Integer type, Integer sort, Integer currPage, Integer pageSize) {

        Page<MoneyLog> page = new Page<>(currPage, pageSize);
        LambdaQueryWrapper<MoneyLog> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(MoneyLog::getUserId, MoneyLog::getOrderType, MoneyLog::getCreateTime, MoneyLog::getMoney,
                MoneyLog::getUserName,MoneyLog::getBeforeMoney,MoneyLog::getAfterMoney);
        if (sort== SortEnum.ASC.getCode())
            wrapper.orderByAsc(MoneyLog::getCreateTime);
        else
            wrapper.orderByDesc(MoneyLog::getCreateTime);
        if(type!=null){
            wrapper.eq(MoneyLog::getOrderType, type);
        }else {
            wrapper.in(MoneyLog::getOrderType, MbChangeTypeEnum.WITHDRAWAL.getType(),MbChangeTypeEnum.RECHARGE.getType());
        }


        wrapper.in( MoneyLog::getUserId, userIds);

        Page<MoneyLog> moneyLogPage = this.baseMapper.selectPage(page, wrapper);
        List<MoneyLog> moneyLogPageRecords = moneyLogPage.getRecords();
        List<MbChangeRecordDetailsVo> changeRecordVos = new ArrayList<>();
        for (MoneyLog moneyLogPageRecord : moneyLogPageRecords) {
            MbChangeRecordDetailsVo changeRecordVo = new MbChangeRecordDetailsVo();
            changeRecordVo.setDateTime(moneyLogPageRecord.getCreateTime());
            changeRecordVo.setMbs(moneyLogPageRecord.getMoney());
            changeRecordVo.setAddOrSubTypeInt(MbChangeTypeExtendEnum.getMbChangeTypeEnumByType(moneyLogPageRecord.getOrderType()).getAddOrSub());
            changeRecordVo.setAddOrSubType(MbChangeTypeExtendEnum.getLanguageAddOrSubNameByType(moneyLogPageRecord.getOrderType()));
            changeRecordVo.setName(MbChangeTypeExtendEnum.getMbChangeTypeEnumByType(moneyLogPageRecord.getOrderType()).getName());
            changeRecordVo.setUserId(moneyLogPageRecord.getUserId());
            changeRecordVo.setBeforeMoney(moneyLogPageRecord.getBeforeMoney());
            changeRecordVo.setAfterMoney(moneyLogPageRecord.getAfterMoney());
            changeRecordVo.setUserName(moneyLogPageRecord.getUserName());
            changeRecordVos.add(changeRecordVo);
        }

        Long total = page.getTotal();
        Integer totalPage = (int) (total % pageSize == 0 ? total / pageSize : total / pageSize + 1);

        return PornPageResult.<MbChangeRecordDetailsVo>builder()
                .data(changeRecordVos)
                .currPage(currPage)
                .pageSize(pageSize)
                .count(total)
                .totalPage(totalPage)
                .build();

    }
}



























