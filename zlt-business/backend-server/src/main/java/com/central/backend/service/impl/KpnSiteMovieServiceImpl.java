package com.central.backend.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.backend.controller.movie.KpnSiteMovieVo;
import com.central.backend.controller.movie.MovieSearchEnum;
import com.central.backend.controller.movie.QueryMovieCo;
import com.central.backend.controller.movie.TagVo;
import com.central.backend.mapper.KpnSiteMovieMapper;
import com.central.backend.model.dto.KpnSiteMoviePayTypeDto;
import com.central.backend.model.dto.KpnSiteMovieStatusDto;
import com.central.backend.service.IKpnMovieTagService;
import com.central.backend.service.IKpnSiteMovieService;
import com.central.backend.vo.MovieVo;
import com.central.common.KpnMovieTag;
import com.central.common.language.LanguageUtil;
import com.central.common.model.KpnSiteMovie;
import com.central.common.model.KpnTag;
import com.central.common.model.PageResult;
import com.central.common.model.SysUser;
import com.central.common.model.enums.*;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.common.vo.LanguageNameMulti;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;


/**
 * 站点影片
 */
@Slf4j
@RefreshScope
@Service
public class KpnSiteMovieServiceImpl extends SuperServiceImpl<KpnSiteMovieMapper, KpnSiteMovie> implements IKpnSiteMovieService {

    @Autowired
    private IKpnMovieTagService movieTagService;

    @Value("${zlt.minio.externalEndpoint}")
    private String externalEndpoint;

    @Resource
    private TaskExecutor taskExecutor;

    private static final Integer ALL = -1;





    @SneakyThrows
    @Override
    public PageResult<KpnSiteMovieVo> list(QueryMovieCo queryMovieCo) {
        Page<KpnSiteMovie> page = new Page<>(queryMovieCo.getPage(), queryMovieCo.getLimit());
        LambdaQueryWrapper<KpnSiteMovie> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(KpnSiteMovie::getSiteId, queryMovieCo.getSiteId());
        //更新时间
        if (StrUtil.isNotBlank(queryMovieCo.getStartTime())) {
            wrapper.ge(KpnSiteMovie::getUpdateTime, queryMovieCo.getStartTime());
        }
        if (StrUtil.isNotBlank(queryMovieCo.getEndTime())) {
            wrapper.le(KpnSiteMovie::getUpdateTime, queryMovieCo.getEndTime());
        }
        //站点影片状态
        if (!SiteMovieStatusEnum.isAll(queryMovieCo.getStatus())) {
            wrapper.eq(KpnSiteMovie::getStatus, queryMovieCo.getStatus());
        }
        //国家
        if (!KpnMovieCountryEnum.isAll(queryMovieCo.getCountry())) {
            wrapper.eq(KpnSiteMovie::getCountry, queryMovieCo.getCountry());
        }
        //标签分类
        if (ObjectUtil.isNotEmpty(queryMovieCo.getTagCategoryId()) && !queryMovieCo.getTagCategoryId().equals(ALL)) {
            LambdaQueryChainWrapper<KpnMovieTag> movieTagWrapper = movieTagService.lambdaQuery();
            movieTagWrapper.select(KpnMovieTag::getMovieId);
            movieTagWrapper.eq(KpnMovieTag::getTagCategoryId, queryMovieCo.getTagCategoryId());
            if (ObjectUtil.isNotEmpty(queryMovieCo.getTagId()) && !queryMovieCo.getTagId().equals(ALL)) {
                movieTagWrapper.eq(KpnMovieTag::getTagId, queryMovieCo.getTagId());
            }
            List<Long> movieIds = movieTagWrapper.list().stream().map(KpnMovieTag::getMovieId).collect(Collectors.toList());
            if (CollUtil.isNotEmpty(movieIds)) {
                wrapper.in(KpnSiteMovie::getMovieId, movieIds);
            }
        }
        //影片类型
        if (!KpnMovieTypeEnum.isAll(queryMovieCo.getType())) {
            wrapper.eq(KpnSiteMovie::getType, queryMovieCo.getType());
        }
        //拍摄性质
        if (!KpnMovieShootingEnum.isAll(queryMovieCo.getShooting())) {
            wrapper.eq(KpnSiteMovie::getShootingType, queryMovieCo.getShooting());
        }
        //字幕类型
        if (!KpnMovieSubtitleEnum.isAll(queryMovieCo.getSubtitle())) {
            wrapper.eq(KpnSiteMovie::getSubtitleType, queryMovieCo.getSubtitle());
        }
        //付费类型
        if (!KpnMoviePayTypeEnum.isAll(queryMovieCo.getPayType())) {
            wrapper.eq(KpnSiteMovie::getPayType, queryMovieCo.getPayType());
        }
        //模糊搜索
        if (StrUtil.isNotBlank(queryMovieCo.getSearch())) {
            String search = queryMovieCo.getSearch();
            //影片id
            if (queryMovieCo.getSearchId().equals(MovieSearchEnum.MOVIE_ID.getCode())) {
                wrapper.eq(KpnSiteMovie::getMovieId, search);
            }
            //影片名
            else if (queryMovieCo.getSearchId().equals(MovieSearchEnum.MOVIE_NAME.getCode())) {
                wrapper.like(KpnSiteMovie::getNameZh, search)
                        .or().like(KpnSiteMovie::getNameEn, search)
                        .or().like(KpnSiteMovie::getNameKh, search)
                ;
            }
            //演员名
            else if (queryMovieCo.getSearchId().equals(MovieSearchEnum.ACTOR_NAME.getCode())) {
                wrapper.like(KpnSiteMovie::getActorNameZh, search)
                        .or().like(KpnSiteMovie::getActorNameEn, search)
                        .or().like(KpnSiteMovie::getActorNameKh, search)
                ;
            }
        }
        //播放量排序
        wrapper.orderBy(true, KpnSortOrderEnum.isAsc(queryMovieCo.getSort()), KpnSiteMovie::getVv);

        Page<KpnSiteMovie> pageResult = baseMapper.selectPage(page, wrapper);

        List<KpnSiteMovieVo> siteMovieVos = new ArrayList<>();
        List<KpnSiteMovie> records = pageResult.getRecords();
        LanguageNameMulti actorMultiName = new LanguageNameMulti();
        CountDownLatch countDownLatch = new CountDownLatch(records.size());
        for (KpnSiteMovie siteMovie : records) {
            KpnSiteMovieVo siteMovieVo = new KpnSiteMovieVo();
            BeanUtils.copyProperties(siteMovie, siteMovieVo);
            siteMovieVo.setName(LanguageUtil.getLanguageName2(siteMovieVo));
            //演员名
            actorMultiName.setNameEn(siteMovie.getActorNameEn());
            actorMultiName.setNameZh(siteMovie.getActorNameZh());
            actorMultiName.setNameKh(siteMovie.getActorNameKh());
            siteMovieVo.setActorName(LanguageUtil.getLanguageName(actorMultiName));
            //url
            siteMovieVo.setUrl(externalEndpoint + siteMovieVo.getUrl());
            siteMovieVo.setCoverUrl(externalEndpoint + siteMovieVo.getCoverUrl());
            //标签
            taskExecutor.execute(() -> {
                List<KpnTag> kpnMovieTags = movieTagService.getTagByMovieId(siteMovie.getMovieId());
                List<TagVo> tagVos = new ArrayList<>();
                for (KpnTag kpnTag : kpnMovieTags) {
                    TagVo tagVo = new TagVo();
                    BeanUtils.copyProperties(kpnTag, tagVo);
                    tagVo.setName(LanguageUtil.getLanguageName(tagVo));
                    tagVos.add(tagVo);
                }
                siteMovieVo.setTagVos(tagVos);
                countDownLatch.countDown();
            });

            siteMovieVos.add(siteMovieVo);
        }
        countDownLatch.await();

        return PageResult.<KpnSiteMovieVo>builder().data(siteMovieVos).count(page.getTotal()).build();
    }




    @Override
    public void updateBatchStatusById(List<KpnSiteMovieStatusDto> list,SysUser user){
        List<KpnSiteMovie> movieList = new ArrayList<>();
        for (KpnSiteMovieStatusDto dto : list){
            KpnSiteMovie movie = new KpnSiteMovie();
            BeanUtils.copyProperties(dto, movie);
            movie.setUpdateBy(null!=user?user.getUsername():"");
            movie.setUpdateTime(new Date());
            movieList.add(movie);
        }
        baseMapper.updateBatchStatusById(movieList);
    }

    @Override
    public void updateBatchPayTypeById(List<KpnSiteMoviePayTypeDto> list, SysUser user){
        List<KpnSiteMovie> movieList = new ArrayList<>();
        for (KpnSiteMoviePayTypeDto dto : list){
            KpnSiteMovie movie = new KpnSiteMovie();
            BeanUtils.copyProperties(dto, movie);
            movie.setUpdateBy(null!=user?user.getUsername():"");
            movie.setUpdateTime(new Date());
            movieList.add(movie);
        }
        baseMapper.updateBatchPayTypeById(movieList);
    }

    @Override
    public PageResult<MovieVo> findMovieList(Map<String, Object> params) {
        Page<MovieVo> page = new Page<>(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));
        List<MovieVo> list  =  baseMapper.findMovieList(page, params);
        return PageResult.<MovieVo>builder().data(list).count(page.getTotal()).build();
    }
}
