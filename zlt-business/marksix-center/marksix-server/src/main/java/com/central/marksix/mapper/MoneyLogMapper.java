package com.central.marksix.mapper;

import com.central.common.model.MoneyLog;
import com.central.db.mapper.SuperMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

@Mapper
public interface MoneyLogMapper extends SuperMapper<MoneyLog> {

    /**
     * 按M币账变类型获取累计总金额
     *
     * @param userId           会员id
     * @param mbChangeTypeCode mb账变类型
     * @return
     */
    BigDecimal getRewardMbsByMbChangeType(@Param("userId") Long userId, @Param("mbChangeTypeCode") Integer mbChangeTypeCode);

    /**
     * 获取推广人今日总推广mb金额
     *
     * @param userId           推广人id
     * @param date             当前日期(yyyy-MM-dd)
     * @param mbChangeTypeCode mb变动类型
     * @return
     */
    BigDecimal getUserTodayPromoteTotalMb(@Param("userId") Long userId, @Param("date") String date, @Param("mbChangeTypeCode") Integer mbChangeTypeCode);

}
