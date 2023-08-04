package com.central.marksix.service;

import com.central.common.model.PageResult;
import com.central.common.model.QuizOrders;
import com.central.common.model.Result;
import com.central.common.model.SysUser;
import com.central.common.service.ISuperService;
import com.central.marksix.entity.dto.QuizOrdersDto;
import com.central.marksix.entity.vo.StatiQuizOrdersVo;

import java.util.List;
import java.util.Map;

public interface IQuizOrdersService extends ISuperService<QuizOrders> {


    /**
     * 查询我的投注
     * @return
     */
    public PageResult<QuizOrders> findList(Map<String, Object> params);

    /**
     * 统计我的投注
     * @param params
     * @return
     */
    StatiQuizOrdersVo statiOrders(Map<String, Object> params);

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

    public PageResult<QuizOrders> findPage(Map<String, Object> params);
}
