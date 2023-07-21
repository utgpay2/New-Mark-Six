package com.central.backend.mapper;

import com.central.backend.model.vo.UserBettingDetailedReportFormsVo;
import com.central.backend.model.vo.UserMoneyDetailedReportFormsVo;
import com.central.backend.model.vo.UserReportFormsVo;
import com.central.common.model.QuizOrders;
import com.central.db.mapper.SuperMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

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

    List<UserReportFormsVo> userReportForms(Page<QuizOrders> page, @Param("p")Map<String, Object> params);

    List<UserMoneyDetailedReportFormsVo> userMoneyDetailed(Page<QuizOrders> page, @Param("p")Map<String, Object> params);

    List<UserBettingDetailedReportFormsVo> userBettingDetailed(Page<QuizOrders> page, @Param("p")Map<String, Object> params);
}
