package com.central.porn.mapper;

import com.central.common.model.KpnMoneyLog;
import com.central.db.mapper.SuperMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

@Mapper
public interface KpnMoneyLogMapper extends SuperMapper<KpnMoneyLog> {

    /**
     * 按K币账变类型获取累计总金额
     *
     * @param userId           会员id
     * @param kbChangeTypeCode kb账变类型
     * @return
     */
    BigDecimal getRewardKbsByKbChangeType(@Param("userId") Long userId, @Param("kbChangeTypeCode") Integer kbChangeTypeCode);

    /**
     * 获取推广人今日总推广kb金额
     *
     * @param userId           推广人id
     * @param date             当前日期(yyyy-MM-dd)
     * @param kbChangeTypeCode kb变动类型
     * @return
     */
    BigDecimal getUserTodayPromoteTotalKb(@Param("userId") Long userId, @Param("date") String date, @Param("kbChangeTypeCode") Integer kbChangeTypeCode);

}
