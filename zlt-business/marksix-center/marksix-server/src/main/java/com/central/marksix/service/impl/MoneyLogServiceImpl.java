package com.central.marksix.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.model.MoneyLog;
import com.central.common.model.SysUser;
import com.central.common.model.enums.MbChangeTypeEnum;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.common.language.LanguageUtil;
import com.central.marksix.entity.PornPageResult;
import com.central.marksix.entity.vo.MbChangeRecordVo;
import com.central.marksix.enums.MbChangeTypeExtendEnum;
import com.central.marksix.mapper.MoneyLogMapper;
import com.central.marksix.service.IMoneyLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Slf4j
@Service
public class MoneyLogServiceImpl extends SuperServiceImpl<MoneyLogMapper, MoneyLog> implements IMoneyLogService {


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
}



























