package com.central.backend.service;

import com.central.common.model.PageResult;
import com.central.common.model.QuizOrders;
import com.central.common.service.ISuperService;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 
 *
 * @author zlt
 * @date 2023-05-09 18:43:48
 */
public interface IQuizOrdersService extends ISuperService<QuizOrders> {
    /**
     * 列表
     * @param params
     * @return
     */
    PageResult<QuizOrders> findList(Map<String, Object> params);

    PageResult userReportForms(Map<String, Object> params);

    void userReportFormsExport(Map<String, Object> params, HttpServletResponse httpServletResponse);

    PageResult userMoneyDetailed(Map<String, Object> params);

    void userMoneyDetailedExport(Map<String, Object> params,HttpServletResponse httpServletResponse);

    PageResult userBettingDetailed(Map<String, Object> params);

    void userBettingDetailedExport(Map<String, Object> params, HttpServletResponse httpServletResponse);
}

