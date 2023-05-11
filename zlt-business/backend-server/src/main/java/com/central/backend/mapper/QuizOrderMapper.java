package com.central.backend.mapper;

import com.central.common.model.QuizOrder;
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
 * @date 2023-05-09 18:43:00
 */
@Mapper
public interface QuizOrderMapper extends SuperMapper<QuizOrder> {
    /**
     * 分页查询用户列表
     * @param page
     * @param params
     * @return
     */
    List<QuizOrder> findList(Page<QuizOrder> page, @Param("p") Map<String, Object> params);
}
