package com.central.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.backend.co.KpnSiteSuggestionCo;
import com.central.backend.co.KpnSiteSuggestionUpdateCo;
import com.central.backend.mapper.KpnSiteSuggestionMapper;
import com.central.backend.service.IKpnSiteSuggestionService;
import com.central.common.model.KpnSiteAnnouncement;
import com.central.common.model.KpnSiteSuggestion;
import com.central.common.model.PageResult;
import com.central.common.model.Result;
import com.central.common.service.impl.SuperServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class KpnSiteSuggestionServiceImpl extends SuperServiceImpl<KpnSiteSuggestionMapper, KpnSiteSuggestion> implements IKpnSiteSuggestionService {

    @Override
    public PageResult<KpnSiteSuggestion> findSuggestionList(KpnSiteSuggestionCo params) {
        Page<KpnSiteSuggestion> page = new Page<>(params.getPage(), params.getLimit());
        LambdaQueryWrapper<KpnSiteSuggestion> wrapper=new LambdaQueryWrapper<>();

        if (params.getSiteId()!=null){
            wrapper.like(KpnSiteSuggestion::getSiteId, params.getSiteId());
        }
        if (StringUtils.isNotBlank(params.getContent())){
            wrapper.like(KpnSiteSuggestion::getContent, params.getContent());
        }
        if (StringUtils.isNotBlank(params.getStartTime())) {
            wrapper.ge(KpnSiteSuggestion::getCreateTime, params.getStartTime());
        }
        if (StringUtils.isNotBlank(params.getEndTime())) {
            wrapper.le(KpnSiteSuggestion::getCreateTime, params.getEndTime());
        }

        wrapper.orderByAsc(KpnSiteSuggestion::getStatus,KpnSiteSuggestion::getCreateTime);
        Page<KpnSiteSuggestion> list = baseMapper.selectPage(page, wrapper);
        long total = page.getTotal();
        return PageResult.<KpnSiteSuggestion>builder().data(list.getRecords()).count(total).build();
    }

    @Override
    public Result updateSuggestionStatus(KpnSiteSuggestionUpdateCo params) {
        Long id = params.getId();
        Integer state = params.getStatus();
        KpnSiteSuggestion suggestion = baseMapper.selectById(id);
        if (suggestion == null) {
            return Result.failed("数据不存在");
        }

        suggestion.setStatus(state);
        suggestion.setUpdateBy(params.getUpdateBy());
        int i = baseMapper.updateById(suggestion);

        return i > 0 ?Result.succeed(suggestion, "更新成功") : Result.failed("更新失败");
    }
}