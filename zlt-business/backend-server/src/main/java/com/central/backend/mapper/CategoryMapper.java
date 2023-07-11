package com.central.backend.mapper;

import com.central.common.model.Category;
import com.central.db.mapper.SuperMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 下注分类
 * 
 * @author yixiu
 * @date 2023-02-03 16:32:41
 */
@Mapper
public interface CategoryMapper extends SuperMapper<Category> {
    /**
     * 分页查询用户列表
     * @param params
     * @return
     */
    List<Category> findList(@Param("p") Map<String, Object> params);

}
