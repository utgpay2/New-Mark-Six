package com.xxl.job.executor.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.model.QuizSuborders;
import com.central.db.mapper.SuperMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author zlt
 * @date 2023-06-15 20:50:53
 */
@Mapper
public interface QuizSubordersMapper extends SuperMapper<QuizSuborders> {
    /**
     * 分页查询用户列表
     * @param page
     * @param params
     * @return
     */
    List<QuizSuborders> findList(Page<QuizSuborders> page, @Param("p") Map<String, Object> params);

    List<QuizSuborders> findList(@Param("p") Map<String, Object> params);
}
