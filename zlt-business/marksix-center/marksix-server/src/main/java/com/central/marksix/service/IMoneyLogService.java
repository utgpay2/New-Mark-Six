package com.central.marksix.service;

import com.central.common.model.MoneyLog;
import com.central.common.model.SysUser;
import com.central.common.service.ISuperService;
import com.central.marksix.entity.PornPageResult;
import com.central.marksix.entity.vo.MbChangeRecordVo;

import java.math.BigDecimal;
import java.util.Map;

public interface IMoneyLogService extends ISuperService<MoneyLog> {

    /**
     * 获取会员账变记录
     *
     * @param userId   会员id
     * @param currPage 当前页数
     * @param pageSize 每页条数
     * @return
     */
    PornPageResult<MbChangeRecordVo> getByUserId(Long userId, Integer currPage, Integer pageSize);
}
