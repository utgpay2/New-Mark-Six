package com.central.backend.service.impl;

import com.central.backend.mapper.QuizChooseMapper;
import com.central.backend.service.IQuizChooseService;
import com.central.common.model.QuizChoose;
import org.springframework.stereotype.Service;
import com.central.common.model.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.service.impl.SuperServiceImpl;

import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import lombok.extern.slf4j.Slf4j;


/**
 * 竞猜奖项详情
 *
 * @author zlt
 * @date 2023-05-09 18:42:17
 */
@Slf4j
@Service
public class QuizChooseServiceImpl extends SuperServiceImpl<QuizChooseMapper, QuizChoose> implements IQuizChooseService {
    /**
     * 列表
     * @param params
     * @return
     */
    @Override
    public List<QuizChoose> findList(Map<String, Object> params){
        return baseMapper.findList( params);
    }
}
