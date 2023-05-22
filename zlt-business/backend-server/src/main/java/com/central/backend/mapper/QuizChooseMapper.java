package com.central.backend.mapper;

import com.central.common.model.QuizChoose;
import com.central.db.mapper.SuperMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 竞猜奖项详情
 * 
 * @author zlt
 * @date 2023-05-09 18:42:17
 */
@Mapper
public interface QuizChooseMapper extends SuperMapper<QuizChoose> {
    /**
     * @param params
     * @return
     */
    List<QuizChoose> findList(@Param("p") Map<String, Object> params);
}
