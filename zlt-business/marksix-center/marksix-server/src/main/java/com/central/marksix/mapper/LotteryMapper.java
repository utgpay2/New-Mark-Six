package com.central.marksix.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.model.Lottery;
import com.central.db.mapper.SuperMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 彩种表
 * 
 * @author zlt
 * @date 2023-05-09 19:57:30
 */
@Mapper
public interface LotteryMapper extends SuperMapper<Lottery> {
    /**
     * 查询彩种列表
     * @param params
     * @return
     */
    List<Lottery> findList(@Param("p") Map<String, Object> params);
}
