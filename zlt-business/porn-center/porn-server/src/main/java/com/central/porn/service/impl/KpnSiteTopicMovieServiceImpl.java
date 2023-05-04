package com.central.porn.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.central.common.constant.PornConstants;
import com.central.common.model.KpnSiteTopicMovie;
import com.central.common.redis.template.RedisRepository;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.porn.mapper.KpnSiteTopicMovieMapper;
import com.central.porn.service.IKpnSiteTopicMovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class KpnSiteTopicMovieServiceImpl extends SuperServiceImpl<KpnSiteTopicMovieMapper, KpnSiteTopicMovie> implements IKpnSiteTopicMovieService {

    @Override
    public List<Long> getTopicMovieIdsBySort(Long sid, Long topicId, Integer currPage, Integer pageSize) {
        String topicMovieIdRedisKey = StrUtil.format(PornConstants.RedisKey.KPN_SITE_TOPIC_MOVIEID_SORT_KEY, sid, topicId);

        int start = (currPage - 1) * pageSize;
        int end = start + pageSize - 1;
        List<String> topicMovieIdList = (ArrayList) RedisRepository.getList(topicMovieIdRedisKey, 0, -1);
        if (CollectionUtil.isEmpty(topicMovieIdList)) {
            //获取全部影片
            List<KpnSiteTopicMovie> siteTopicMovies = this.lambdaQuery()
                    .eq(KpnSiteTopicMovie::getSiteId, sid)
                    .eq(KpnSiteTopicMovie::getTopicId, topicId)
                    .orderByAsc(KpnSiteTopicMovie::getSort)
                    .list();

            if (CollectionUtil.isNotEmpty(siteTopicMovies)) {
                List<String> topicMovieIds = siteTopicMovies.stream()
                        .map(kpnSiteTopicMovie -> String.valueOf(kpnSiteTopicMovie.getMovieId()))
                        .collect(Collectors.toList());
                RedisRepository.leftPushAll(topicMovieIdRedisKey, topicMovieIds);
                RedisRepository.setExpire(topicMovieIdRedisKey, PornConstants.RedisKey.EXPIRE_TIME_30_DAYS);
                topicMovieIdList = (ArrayList) RedisRepository.getList(topicMovieIdRedisKey, start, end);
            } else {
                topicMovieIdList = new ArrayList<>();
            }
        }
        return topicMovieIdList.stream().map(Long::parseLong).collect(Collectors.toList());
    }


    @Override
    public List<Long> getTopicMovieIdsSortedByColumn(Long sid, Long topicId, String column) {
        return this.baseMapper.getTopicMovieIdsSortedByColumn(sid, topicId, column);
    }

    @Override
    public void addTopicMovieVv(Long sid, Long topicId, Long movieId) {
        this.lambdaUpdate().setSql(" `vv` = `vv` + 1")
                .eq(KpnSiteTopicMovie::getSiteId, sid)
                .eq(KpnSiteTopicMovie::getTopicId, topicId)
                .eq(KpnSiteTopicMovie::getMovieId, movieId)
                .update();
    }
}



















