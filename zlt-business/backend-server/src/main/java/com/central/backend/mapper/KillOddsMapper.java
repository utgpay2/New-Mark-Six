package com.central.backend.mapper;

import com.central.common.model.KillOdds;
import com.central.db.mapper.SuperMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 设置杀率
 * 
 * @author yixiu
 * @date 2023-02-03 16:32:41
 */
@Mapper
public interface KillOddsMapper extends SuperMapper<KillOdds> {
    /**
     * 分页查询用户列表
     * @param params
     * @return
     */
    List<KillOdds> findList(@Param("p") Map<String, Object> params);

}
