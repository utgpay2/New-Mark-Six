package com.central.porn.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.central.common.constant.PornConstants;
import com.central.common.model.KpnSiteTopic;
import com.central.common.model.enums.SiteTopicEnum;
import com.central.common.redis.template.RedisRepository;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.common.language.LanguageUtil;
import com.central.porn.entity.vo.KpnSiteMovieBaseVo;
import com.central.porn.entity.vo.KpnSiteTopicVo;
import com.central.porn.mapper.KpnSiteTopicMapper;
import com.central.porn.service.IKpnSiteMovieService;
import com.central.porn.service.IKpnSiteTopicMovieService;
import com.central.porn.service.IKpnSiteTopicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class KpnSiteTopicServiceImpl extends SuperServiceImpl<KpnSiteTopicMapper, KpnSiteTopic> implements IKpnSiteTopicService {

    @Autowired
    private IKpnSiteTopicMovieService topicMovieService;

    @Autowired
    private IKpnSiteMovieService siteMovieService;


    public List<Long> getTopicIdsBySiteId(Long sid) {
        String topicKey = StrUtil.format(PornConstants.RedisKey.KPN_SITE_TOPIC_KEY, sid);
        List<KpnSiteTopic> siteTopics = (List<KpnSiteTopic>) RedisRepository.get(topicKey);
        if (CollectionUtil.isEmpty(siteTopics)) {
            siteTopics = this.lambdaQuery()
                    .eq(KpnSiteTopic::getSiteId, sid)
                    .eq(KpnSiteTopic::getStatus, SiteTopicEnum.ON_SHELF.getStatus())
                    .list();
            if (CollectionUtil.isNotEmpty(siteTopics)) {
                RedisRepository.setExpire(topicKey, siteTopics, PornConstants.RedisKey.EXPIRE_TIME_30_DAYS);
            }
        }

        return siteTopics.stream().map(KpnSiteTopic::getId).collect(Collectors.toList());
    }

    @Override
    public List<KpnSiteTopicVo> getBySiteId(Long sid) {
        //查询并缓存专题
        String topicKey = StrUtil.format(PornConstants.RedisKey.KPN_SITE_TOPIC_KEY, sid);
        List<KpnSiteTopic> siteTopics = (List<KpnSiteTopic>) RedisRepository.get(topicKey);
        if (CollectionUtil.isEmpty(siteTopics)) {
            siteTopics = this.lambdaQuery()
                    .eq(KpnSiteTopic::getSiteId, sid)
                    .eq(KpnSiteTopic::getStatus, SiteTopicEnum.ON_SHELF.getStatus())
                    .orderByDesc(KpnSiteTopic::getSort)
                    .list();
            if (CollectionUtil.isNotEmpty(siteTopics)) {
                RedisRepository.setExpire(topicKey, siteTopics, PornConstants.RedisKey.EXPIRE_TIME_30_DAYS);
            }
        }

        return siteTopics.stream().map(kpnSiteTopic -> {
            KpnSiteTopicVo topicVo = new KpnSiteTopicVo();
            BeanUtil.copyProperties(kpnSiteTopic, topicVo);
            topicVo.setName(LanguageUtil.getLanguageName(topicVo));
            //查询关联最新的10部影片
            List<Long> movieIds = topicMovieService.getTopicMovieIdsBySort(sid, topicVo.getId(), 1, 10);
            List<KpnSiteMovieBaseVo> kpnSiteMovieBaseVos = siteMovieService.getSiteMovieByIds(sid, movieIds, false);
            topicVo.setMovieBaseVos(kpnSiteMovieBaseVos);
            return topicVo;
        }).collect(Collectors.toList());
    }


}



















