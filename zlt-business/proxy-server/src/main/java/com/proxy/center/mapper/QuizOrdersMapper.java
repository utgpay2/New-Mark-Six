package com.proxy.center.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.proxy.center.model.vo.UserBettingDetailedReportFormsVo;
import com.proxy.center.model.vo.UserMoneyDetailedReportFormsVo;
import com.proxy.center.model.vo.UserReportFormsVo;
import com.central.common.model.QuizOrders;
import com.central.db.mapper.SuperMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author zlt
 * @date 2023-05-09 18:43:48
 */
@Mapper
public interface QuizOrdersMapper extends SuperMapper<QuizOrders> {
    /**
     * 分页查询用户列表
     * @param page
     * @param params
     * @return
     */
    List<QuizOrders> findList(Page<QuizOrders> page, @Param("p") Map<String, Object> params);

    List<UserReportFormsVo> userReportForms(@Param("p") Map<String, Object> params);

    List<UserMoneyDetailedReportFormsVo> userMoneyDetailed(@Param("p") Map<String, Object> params);

    List<UserBettingDetailedReportFormsVo> userBettingDetailed(@Param("p") Map<String, Object> params);


    List<UserReportFormsVo> userReportFormsPage(Page<QuizOrders> page, @Param("p") Map<String, Object> params);

    Map<String, BigDecimal> userReportFormsSummary(@Param("p") Map<String, Object> params);

    BigDecimal getSumWithdrawalByUsernamesSummary(@Param("p") Map<String, Object> params);

    List<UserMoneyDetailedReportFormsVo> userMoneyDetailedPage(Page<QuizOrders> page, @Param("p") Map<String, Object> params);

    Map<String, BigDecimal> userMoneyDetailedSummary(@Param("p") Map<String, Object> params);

    List<UserBettingDetailedReportFormsVo> userBettingDetailedPage(Page<QuizOrders> page, @Param("p") Map<String, Object> params);

    Map<String, BigDecimal> userBettingDetailedSummary(@Param("p") Map<String, Object> params);

    List<Map<String, Object>> getSumWithdrawalByUsernames(@Param("p") Map<String, Object> params);



}
