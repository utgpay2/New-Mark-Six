package com.central.marksix.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.model.PageResult;
import com.central.common.model.QuizChoose;
import com.central.common.model.enums.StatusEnum;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.marksix.mapper.QuizChooseMapper;
import com.central.marksix.service.IQuizChooseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
        if(null == params){
            params = new HashMap<>();
        }
        params.put("status", StatusEnum.ONE_FALSE.getStatus());
        return baseMapper.findList( params);
    }
}
