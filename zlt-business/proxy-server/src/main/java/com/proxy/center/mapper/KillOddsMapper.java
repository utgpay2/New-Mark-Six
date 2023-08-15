package com.proxy.center.mapper;

import com.central.common.model.KillOdds;
import com.central.db.mapper.SuperMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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
    List<KillOdds> findList();

}
