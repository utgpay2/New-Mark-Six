package com.proxy.center.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.model.LotteryKjTime;
import com.central.db.mapper.SuperMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 开奖时间
 * 
 * @author zlt
 * @date 2023-05-09 19:59:03
 */
@Mapper
public interface LotteryKjTimeMapper extends SuperMapper<LotteryKjTime> {
    /**
     * 分页查询用户列表
     * @param page
     * @param params
     * @return
     */
    List<LotteryKjTime> findList(Page<LotteryKjTime> page, @Param("p") Map<String, Object> params);
}
