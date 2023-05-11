package com.central.backend.mapper;

import com.central.common.model.QuizOrderSon;
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
 * @date 2023-05-09 18:43:48
 */
@Mapper
public interface QuizOrderSonMapper extends SuperMapper<QuizOrderSon> {
    /**
     * 分页查询用户列表
     * @param page
     * @param params
     * @return
     */
    List<QuizOrderSon> findList(Page<QuizOrderSon> page, @Param("p") Map<String, Object> params);
}
