package com.proxy.center.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.proxy.center.co.SiteSuggestionCo;
import com.proxy.center.co.SiteSuggestionUpdateCo;
import com.proxy.center.mapper.SiteSuggestionMapper;
import com.proxy.center.service.ISiteSuggestionService;
import com.central.common.model.PageResult;
import com.central.common.model.Result;
import com.central.common.model.SiteSuggestion;
import com.central.common.service.impl.SuperServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class SiteSuggestionServiceImpl extends SuperServiceImpl<SiteSuggestionMapper, SiteSuggestion> implements ISiteSuggestionService {

    @Override
    public PageResult<SiteSuggestion> findSuggestionList(SiteSuggestionCo params) {
        Page<SiteSuggestion> page = new Page<>(params.getPage(), params.getLimit());
        LambdaQueryWrapper<SiteSuggestion> wrapper=new LambdaQueryWrapper<>();

        if (params.getSiteId()!=null){
            wrapper.like(SiteSuggestion::getSiteId, params.getSiteId());
        }
        if (StringUtils.isNotBlank(params.getContent())){
            wrapper.like(SiteSuggestion::getContent, params.getContent());
        }
        if (StringUtils.isNotBlank(params.getStartTime())) {
            wrapper.ge(SiteSuggestion::getCreateTime, params.getStartTime());
        }
        if (StringUtils.isNotBlank(params.getEndTime())) {
            wrapper.le(SiteSuggestion::getCreateTime, params.getEndTime());
        }

        wrapper.orderByAsc(SiteSuggestion::getStatus, SiteSuggestion::getCreateTime);
        Page<SiteSuggestion> list = baseMapper.selectPage(page, wrapper);
        long total = page.getTotal();
        return PageResult.<SiteSuggestion>builder().data(list.getRecords()).count(total).build();
    }

    @Override
    public Result updateSuggestionStatus(SiteSuggestionUpdateCo params) {
        Long id = params.getId();
        Integer state = params.getStatus();
        SiteSuggestion suggestion = baseMapper.selectById(id);
        if (suggestion == null) {
            return Result.failed("数据不存在");
        }

        suggestion.setStatus(state);
        suggestion.setUpdateBy(params.getUpdateBy());
        int i = baseMapper.updateById(suggestion);

        return i > 0 ?Result.succeed(suggestion, "更新成功") : Result.failed("更新失败");
    }
}