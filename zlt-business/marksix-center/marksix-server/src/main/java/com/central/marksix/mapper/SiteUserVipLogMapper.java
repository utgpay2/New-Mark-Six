package com.central.marksix.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.central.common.model.SiteUserVipLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SiteUserVipLogMapper extends BaseMapper<SiteUserVipLog> {

    /**
     * 获取推广奖励vip天数
     *
     * @param userId            会员id
     * @param vipChangeTypeCode vip变动类型
     * @return
     */
    Integer getRewardVipDaysByVipChangeType(@Param("userId") Long userId,@Param("vipChangeTypeCode") Integer vipChangeTypeCode);
}
