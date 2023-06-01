package com.central.marksix.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.model.QuizDetails;
import com.central.db.mapper.SuperMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 竞猜分类
 * 
 * @author zlt
 * @date 2023-05-30 13:00:21
 */
@Mapper
public interface QuizDetailsMapper extends SuperMapper<QuizDetails> {
    /**
     * 分页查询用户列表
     * @param params
     * @return
     */
    List<QuizDetails> findList(@Param("p") Map<String, Object> params);
}
