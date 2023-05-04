package com.central.backend.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.backend.mapper.KpnSiteTopicMovieMapper;
import com.central.backend.service.IAsyncService;
import com.central.backend.service.IKpnSiteTopicMovieService;
import com.central.backend.vo.SiteMovieListVo;
import com.central.common.model.KpnSiteTopicMovie;
import com.central.common.model.PageResult;
import com.central.common.service.impl.SuperServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Slf4j
@Service
public class KpnSiteTopicMovieServiceImpl extends SuperServiceImpl<KpnSiteTopicMovieMapper, KpnSiteTopicMovie> implements IKpnSiteTopicMovieService {

    @Autowired
    private IAsyncService asyncService;

    @Override
    public Boolean saveOrUpdateTopicMovie(List<KpnSiteTopicMovie> list) {
        boolean result = super.saveOrUpdateBatch(list);

        //add by year 删除专题影片缓存
        if (result && CollUtil.isNotEmpty(list)) {
            KpnSiteTopicMovie topicMovie = list.get(0);
            asyncService.deleteSiteTopicMovieCache(topicMovie.getSiteId(), topicMovie.getTopicId());
        }
        return result;
    }

    @Override
    public List<KpnSiteTopicMovie> findTopicMovieTopicIdList(Long topicId) {
        LambdaQueryWrapper<KpnSiteTopicMovie> wrapper=new LambdaQueryWrapper<>();
        if (topicId!=null){
            wrapper.eq(KpnSiteTopicMovie::getTopicId,topicId);
        }
        return  baseMapper.selectList(wrapper);
    }

    @Override
    public Boolean deleteTopicId(List<Long> movieIds) {
        int i = baseMapper.deleteBatchIds(movieIds);

        //add by year 删除专题影片缓存
        if (i > 0) {
            Long topicMovieId = movieIds.get(0);
            KpnSiteTopicMovie topicMovie = getById(topicMovieId);
            asyncService.deleteSiteTopicMovieCache(topicMovie.getSiteId(), topicMovie.getTopicId());
        }

        return i > 0 ? true : false;
    }

    @Override
    public Boolean deleteId(Long topicMovieId) {
        int i = baseMapper.deleteById(topicMovieId);

        //add by year 删除专题影片缓存
        if (i > 0) {
            KpnSiteTopicMovie topicMovie = getById(topicMovieId);
            asyncService.deleteSiteTopicMovieCache(topicMovie.getSiteId(), topicMovie.getTopicId());
        }
        return i > 0 ? true : false;
    }


    @Override
    public PageResult<SiteMovieListVo> findSiteMovieList(Map<String, Object> params) {
        Page<SiteMovieListVo> page = new Page<>(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));
        List<SiteMovieListVo> list  =  baseMapper.findSiteMovieList(page,params);
        return PageResult.<SiteMovieListVo>builder().data(list).count(page.getTotal()).build();
    }
}