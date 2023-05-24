package com.central.marksix.service;

import com.central.common.model.PageResult;
import com.central.common.model.QuizOrders;
import com.central.common.model.Result;
import com.central.common.model.SysUser;
import com.central.common.service.ISuperService;
import com.central.marksix.entity.dto.QuizOrdersDto;

import java.util.List;
import java.util.Map;

public interface IQuizOrdersService extends ISuperService<QuizOrders> {


    /**
     * 获取会员订单列表
     * @return
     */
    public PageResult<QuizOrders> findList(Map<String, Object> params);

    /**
     * 下注
     * @param ordersDtoList
     * @param user
     * @return
     */
    public Result bettingOrders(List<QuizOrdersDto> ordersDtoList, SysUser user);

    /**
     * 撤销投注
     * @param ids
     * @param user
     * @return
     */
    public Result cancelBetting(List<Long> ids, SysUser user);
}
