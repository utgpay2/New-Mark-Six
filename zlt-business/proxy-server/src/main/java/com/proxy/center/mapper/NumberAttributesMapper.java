package com.proxy.center.mapper;


import com.central.common.model.NumberAttributes;
import com.central.db.mapper.SuperMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 号码属性表
 * 
 * @author zlt
 * @date 2023-05-08 15:05:53
 */
@Mapper
public interface NumberAttributesMapper extends SuperMapper<NumberAttributes> {
    /**
     * 查询用户列表
     * @param params
     * @return
     */
    List<NumberAttributes> findList(@Param("p") Map<String, Object> params);
}
