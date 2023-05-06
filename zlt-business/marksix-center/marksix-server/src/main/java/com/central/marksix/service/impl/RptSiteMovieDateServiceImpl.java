package com.central.marksix.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.central.common.constant.MarksixConstants;
import com.central.common.model.RptSiteMovieDate;
import com.central.common.redis.template.RedisRepository;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.marksix.entity.vo.KpnSiteMovieBaseVo;
import com.central.marksix.mapper.RptSiteMovieDateMapper;
import com.central.marksix.service.IKpnSiteMovieService;
import com.central.marksix.service.IRptSiteMovieDateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RptSiteMovieDateServiceImpl extends SuperServiceImpl<RptSiteMovieDateMapper, RptSiteMovieDate> implements IRptSiteMovieDateService {

    @Autowired
    @Lazy
    private IKpnSiteMovieService siteMovieService;

    @Override
    public void saveRptSiteMovieDateVv(Long sid, Long movieId, String date) {
        this.baseMapper.saveRptSiteMovieDateVv(sid, movieId, date);
    }

    @Override
    public List<KpnSiteMovieBaseVo> searchSiteMovieMonth(Long sid) {
        String redisKey = StrUtil.format(MarksixConstants.RedisKey.KPN_SITE_MONTH_MOVIE_KEY, sid);
        List<KpnSiteMovieBaseVo> siteMonthMovies = (ArrayList<KpnSiteMovieBaseVo>) RedisRepository.get(redisKey);
        if (CollectionUtil.isEmpty(siteMonthMovies)) {
            String endDate = DateUtil.formatDate(new Date());
            String startDate = DateUtil.formatDate(DateUtil.offsetDay(new Date(), -30));
            log.info("startDate:{}, endDate:{}", startDate, endDate);

            List<RptSiteMovieDate> rptSiteMovieDates = this.baseMapper.searchSiteMovieMonth(sid, startDate, endDate);
            List<Long> movieIds = rptSiteMovieDates.stream().map(RptSiteMovieDate::getMovieId).collect(Collectors.toList());
            siteMonthMovies = siteMovieService.getSiteMovieByIds(sid, movieIds,false);
            if (siteMonthMovies.size() < 10) {
                List<KpnSiteMovieBaseVo> kpnSiteMovieBaseVos = siteMovieService.getFillingSiteMovie(sid, movieIds);
                siteMonthMovies.addAll(kpnSiteMovieBaseVos);
            }

            if (CollectionUtil.isNotEmpty(siteMonthMovies)) {
                RedisRepository.setExpire(redisKey, siteMonthMovies, MarksixConstants.RedisKey.EXPIRE_TIME_1_DAYS);
            }
        }
        return siteMonthMovies;
    }
}
