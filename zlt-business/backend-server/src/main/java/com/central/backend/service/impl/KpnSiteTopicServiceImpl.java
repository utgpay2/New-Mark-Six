package com.central.backend.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.backend.co.KpnSiteTopicSaveCo;
import com.central.backend.co.KpnSiteTopicUpdateCo;
import com.central.backend.mapper.KpnSiteTopicMapper;
import com.central.backend.service.IAsyncService;
import com.central.backend.service.IKpnSiteTopicMovieService;
import com.central.backend.service.IKpnSiteTopicService;
import com.central.backend.vo.KpnSiteTopicVo;
import com.central.common.model.KpnSiteTopic;
import com.central.common.model.KpnSiteTopicMovie;
import com.central.common.model.PageResult;
import com.central.common.model.Result;
import com.central.common.service.impl.SuperServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Slf4j
@Service
public class KpnSiteTopicServiceImpl extends SuperServiceImpl<KpnSiteTopicMapper, KpnSiteTopic> implements IKpnSiteTopicService {



    @Autowired
    private IKpnSiteTopicMovieService siteTopicMovieService;

    @Autowired
    private IAsyncService asyncService;


    @Override
    public PageResult<KpnSiteTopicVo> findSiteTopicList(Map<String, Object> params) {
        Page<KpnSiteTopicVo> page = new Page<>(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));
        Long siteId = MapUtils.getLong(params, "siteId");
        List<KpnSiteTopicVo> list = baseMapper.selectTopicPage(page, siteId);
        return PageResult.<KpnSiteTopicVo>builder().data(list).count(page.getTotal()).build();
    }

    @Override
    public Result updateEnabledTopic(KpnSiteTopicUpdateCo params) {
        Long id = params.getId();
        Integer state = params.getStatus();
        KpnSiteTopic siteTopicInfo = baseMapper.selectById(id);
        if (siteTopicInfo == null) {
            return Result.failed("数据不存在");
        }
        siteTopicInfo.setStatus(state);
        siteTopicInfo.setUpdateBy(params.getUpdateBy());
        int i = baseMapper.updateById(siteTopicInfo);
        //add by year 删除站点专题缓存
        if (i > 0) {
            asyncService.deleteSiteTopicCache(siteTopicInfo.getSiteId());
        }
        return i > 0 ? Result.succeed(siteTopicInfo, "更新成功") : Result.failed("更新失败");
    }

    @Override
    @Transactional
    public Boolean deleteId(Long id) {
        boolean b = false;
        b = baseMapper.deleteById(id) > 0;
        //删除站点专题影片关联表
        List<KpnSiteTopicMovie> topicMovieTopicIdList = siteTopicMovieService.findTopicMovieTopicIdList(id);
        List<Long> movieIds = topicMovieTopicIdList.stream().map(KpnSiteTopicMovie::getId).collect(Collectors.toList());
        b = siteTopicMovieService.deleteTopicId(movieIds);

        //add by year 删除站点专题缓存
        if (b) {
            KpnSiteTopic siteTopic = getById(id);
            asyncService.deleteSiteTopicCache(siteTopic.getSiteId());
        }

        return b;
    }

    @Override
    @Transactional
    public Result saveOrUpdateTopic(KpnSiteTopicSaveCo params) {
        boolean insert = false;
        KpnSiteTopic KpnSiteTopicInfo = params.getKpnSiteTopicInfo();
        Long id = KpnSiteTopicInfo.getId();
        insert = super.saveOrUpdate(KpnSiteTopicInfo);
        if (id == null) {
            params.getMovieList().stream().forEach(info -> {
                info.setTopicId(KpnSiteTopicInfo.getId());
            });
        }
        //添加站点专题影片关联表
        insert = siteTopicMovieService.saveOrUpdateTopicMovie(params.getMovieList());
        //add by year 删除站点专题缓存
        if (insert) {
            asyncService.deleteSiteTopicCache(KpnSiteTopicInfo.getSiteId());
        }
        return insert ? Result.succeed("操作成功") : Result.failed("操作失败");
    }
}