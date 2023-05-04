package com.central.porn.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.constant.PornConstants;
import com.central.common.language.LanguageUtil;
import com.central.common.model.KpnMovie;
import com.central.common.model.KpnSiteMovie;
import com.central.common.model.KpnSiteUserMovieFavorites;
import com.central.common.model.enums.*;
import com.central.common.redis.lock.RedissLockUtil;
import com.central.common.redis.template.RedisRepository;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.porn.entity.PornPageResult;
import com.central.porn.entity.dto.MovieSearchConditionDto;
import com.central.porn.entity.vo.KpnMovieVo;
import com.central.porn.entity.vo.KpnSiteMovieBaseVo;
import com.central.porn.entity.vo.KpnTagVo;
import com.central.porn.enums.KpnMovieSortTypeEnum;
import com.central.porn.enums.KpnSiteMovieSearchFromEnum;
import com.central.porn.mapper.KpnSiteMovieMapper;
import com.central.porn.service.*;
import com.central.porn.utils.PornUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@Service
public class KpnSiteMovieServiceImpl extends SuperServiceImpl<KpnSiteMovieMapper, KpnSiteMovie> implements IKpnSiteMovieService {

    @Autowired
    private IKpnMovieService movieService;

    @Autowired
    private IAsyncService asyncService;

    @Autowired
    private IKpnMovieTagService movieTagService;

    @Autowired
    private IKpnSiteUserMovieFavoritesService siteUserMovieFavoritesService;

    @Autowired
    private IRptSiteSearchDateService siteSearchDateService;

    @Autowired
    private IRptSiteSearchTotalService siteSearchTotalService;

    @Autowired
    private IKpnSiteTopicMovieService siteTopicMovieService;

    @Autowired
    private IRptSiteMovieDateService rptSiteMovieDateService;

    @Resource
    private TaskExecutor taskExecutor;

    @Override
    public List<KpnSiteMovieBaseVo> getSiteMovieByIds(Long sid, List<Long> movieIds, Boolean isDetail) {
        if (CollectionUtil.isEmpty(movieIds)) {
            return new ArrayList<>();
        }
        List<String> redisKeyVoList = movieIds.stream().map(movieId -> StrUtil.format(PornConstants.RedisKey.KPN_SITEID_MOVIEID_VO_KEY, sid, movieId)).collect(Collectors.toList());
        List<KpnMovieVo> cachedKpnMovieVos = (ArrayList) RedisRepository.mget(redisKeyVoList);
        boolean hasNullVoElem = cachedKpnMovieVos.stream().anyMatch(Objects::isNull);
        if (hasNullVoElem) {
            cachedKpnMovieVos = new ArrayList<>();

            List<KpnMovie> kpnMovies = movieService.lambdaQuery()
                    .eq(KpnMovie::getHandleStatus, true)
                    .eq(KpnMovie::getStatus, true)
                    .eq(KpnMovie::getDeleteStatus, false)
                    .in(KpnMovie::getId, movieIds)
                    .list();

            for (KpnMovie kpnMovie : kpnMovies) {
                if (ObjectUtil.isNotEmpty(kpnMovie)) {
                    KpnMovieVo kpnMovieVo = new KpnMovieVo();
                    BeanUtil.copyProperties(kpnMovie, kpnMovieVo);
                    KpnSiteMovie kpnSiteMovie = this.lambdaQuery().select(KpnSiteMovie::getPayType, KpnSiteMovie::getStatus).eq(KpnSiteMovie::getSiteId, sid).eq(KpnSiteMovie::getMovieId, kpnMovie.getId()).one();
                    kpnMovieVo.setPayType(kpnSiteMovie.getPayType());
                    kpnMovieVo.setStatus(kpnSiteMovie.getStatus());

                    List<KpnTagVo> kpnTagVos = movieTagService.getTagByMovieId(kpnMovie.getId());
                    kpnMovieVo.setTagVos(kpnTagVos);

                    KpnSiteMovie siteMovie = getSiteMovieVvFavorites(sid, kpnMovie.getId());
                    kpnMovieVo.setVv(siteMovie.getVv());
                    kpnMovieVo.setFavorites(siteMovie.getFavorites());

                    cachedKpnMovieVos.add(kpnMovieVo);
                }
            }

            if (CollectionUtil.isNotEmpty(cachedKpnMovieVos)) {
                for (KpnMovieVo kpnMovieVo : cachedKpnMovieVos) {
                    RedisRepository.setExpire(StrUtil.format(PornConstants.RedisKey.KPN_SITEID_MOVIEID_VO_KEY, sid, kpnMovieVo.getId()), kpnMovieVo, PornConstants.RedisKey.EXPIRE_TIME_30_DAYS);
                }
            }
        }
        List<KpnSiteMovieBaseVo> result = new ArrayList<>();
        for (KpnMovieVo kpnMovieVo : cachedKpnMovieVos) {
            if (!isDetail) {
                KpnSiteMovieBaseVo siteMovieBaseVo = new KpnSiteMovieBaseVo();
                BeanUtil.copyProperties(kpnMovieVo, siteMovieBaseVo);
                siteMovieBaseVo.setName(LanguageUtil.getLanguageName(siteMovieBaseVo));

                for (KpnTagVo tagVo : kpnMovieVo.getTagVos()) {
                    tagVo.setName(LanguageUtil.getLanguageName(tagVo));
                }
                result.add(siteMovieBaseVo);
            } else {
                result.add(kpnMovieVo);
            }
        }
        return result;
    }

    @Override
    public KpnMovieVo getSiteMovieDetail(Long sid, Long movieId) {
        String movieRedisKey = StrUtil.format(PornConstants.RedisKey.KPN_SITEID_MOVIEID_VO_KEY, sid, movieId);
        KpnMovieVo kpnMovieVo = (KpnMovieVo) RedisRepository.get(movieRedisKey);

        if (ObjectUtil.isEmpty(kpnMovieVo)) {
            kpnMovieVo = (KpnMovieVo)this.getSiteMovieByIds(sid, Collections.singletonList(movieId), true).get(0);
        }

        kpnMovieVo.setName(LanguageUtil.getLanguageName(kpnMovieVo));
        for (KpnTagVo tagVo : kpnMovieVo.getTagVos()) {
            tagVo.setName(LanguageUtil.getLanguageName(tagVo));
        }
        return kpnMovieVo;
    }

    @Override
    public KpnSiteMovie getSiteMovieVvFavorites(Long sid, Long movieId) {
        //先缓存
        String siteMovieVvKey = StrUtil.format(PornConstants.RedisKey.KPN_SITE_MOVIE_VV_KEY, sid, movieId);
        cacheSiteMovieVv(siteMovieVvKey, sid, movieId);
        long vv = RedisRepository.incr(siteMovieVvKey);

        String siteMovieFavoritesKey = StrUtil.format(PornConstants.RedisKey.KPN_SITE_MOVIE_FAVORITES_KEY, sid, movieId);
        cacheSiteMovieFavorites(siteMovieFavoritesKey, sid, movieId);
        long favorites = RedisRepository.incr(siteMovieFavoritesKey);

        taskExecutor.execute(() -> {
            RedisRepository.decr(siteMovieVvKey);
            RedisRepository.decr(siteMovieFavoritesKey);
        });

        return KpnSiteMovie.builder().siteId(sid).movieId(movieId).vv(vv - 1).favorites(favorites - 1).build();
    }

    @Override
    public Long addSiteMovieVv(Long sid, Long topicId, Long movieId) {
        String siteMovieVvKey = StrUtil.format(PornConstants.RedisKey.KPN_SITE_MOVIE_VV_KEY, sid, movieId);
        cacheSiteMovieVv(siteMovieVvKey, sid, movieId);
        taskExecutor.execute(() -> {
            addSiteMovieVv(sid, movieId);
            if (ObjectUtil.isNotEmpty(topicId)) {
                siteTopicMovieService.addTopicMovieVv(sid, topicId, movieId);
            }
            movieService.addMovieVv(movieId);
        });
        return RedisRepository.incr(siteMovieVvKey);
    }

    @Override
    public Long addSiteMovieFavorites(Long sid, Long userId, Long movieId) {
        KpnSiteUserMovieFavorites movieFavorites = siteUserMovieFavoritesService.getUserMovieFavorites(userId, movieId);
        if (ObjectUtil.isNotEmpty(movieFavorites)) {
            throw new RuntimeException("已经收藏该影片,不可重复操作");
        }

        String siteMovieFavoritesKey = StrUtil.format(PornConstants.RedisKey.KPN_SITE_MOVIE_FAVORITES_KEY, sid, movieId);
        cacheSiteMovieFavorites(siteMovieFavoritesKey, sid, movieId);

        addSiteMovieFavorites(sid, movieId);
        movieService.addMovieFavorites(movieId);
        siteUserMovieFavoritesService.add(userId, movieId);
        return RedisRepository.incr(siteMovieFavoritesKey);
    }

    @Override
    public Long removeSiteMovieFavorites(Long sid, Long userId, Long movieId) {
        KpnSiteUserMovieFavorites movie = siteUserMovieFavoritesService.getUserMovieFavorites(userId, movieId);
        if (ObjectUtil.isEmpty(movie)) {
            throw new RuntimeException("会员未收藏,不可取消");
        }

        String siteMovieFavoritesKey = StrUtil.format(PornConstants.RedisKey.KPN_SITE_MOVIE_FAVORITES_KEY, sid, movieId);
        cacheSiteMovieFavorites(siteMovieFavoritesKey, sid, movieId);

        removeSiteMovieFavorites(sid, movieId);
        movieService.removeMovieFavorites(movieId);
        siteUserMovieFavoritesService.remove(userId, movieId);

        return RedisRepository.decr(siteMovieFavoritesKey);
    }

    @Override
    public void addSiteMovieVv(Long sid, Long movieId) {
        this.lambdaUpdate()
                .eq(KpnSiteMovie::getSiteId, sid)
                .eq(KpnSiteMovie::getMovieId, movieId)
                .setSql(" `vv` = `vv` + 1")
                .update();

        rptSiteMovieDateService.saveRptSiteMovieDateVv(sid, movieId, DateUtil.formatDate(new Date()));
    }

    @Override
    public void addSiteMovieFavorites(Long sid, Long movieId) {
        this.lambdaUpdate()
                .eq(KpnSiteMovie::getSiteId, sid)
                .eq(KpnSiteMovie::getMovieId, movieId)
                .setSql(" `favorites` = `favorites` + 1")
                .update();
    }

    @Override
    public void removeSiteMovieFavorites(Long sid, Long movieId) {
        this.lambdaUpdate()
                .eq(KpnSiteMovie::getSiteId, sid)
                .eq(KpnSiteMovie::getMovieId, movieId)
                .setSql(" `favorites` = `favorites` - 1")
                .update();
    }

    @Override
    public Long getSiteActorMovieNum(Long sid, Long actorId) {
        String redisKey = StrUtil.format(PornConstants.RedisKey.KPN_SITE_ACTOR_MOVIENUM_KEY, sid, actorId);
        Integer siteActorMovieNum = (Integer) RedisRepository.get(redisKey);
        if (ObjectUtil.isEmpty(siteActorMovieNum)) {
            siteActorMovieNum = this.lambdaQuery()
                    .eq(KpnSiteMovie::getSiteId, sid)
                    .eq(KpnSiteMovie::getStatus, SiteMovieStatusEnum.ON_SHELF.getStatus())
                    .eq(KpnSiteMovie::getActorId, actorId)
                    .count();
            if (ObjectUtil.isNotEmpty(siteActorMovieNum)) {
                RedisRepository.setExpire(redisKey, siteActorMovieNum, PornConstants.RedisKey.EXPIRE_TIME_30_DAYS);
            }
        }

        return siteActorMovieNum.longValue();
    }

    @Override
    public PornPageResult<KpnSiteMovieBaseVo> getSiteMovieByActor(Long sid, Long actorId, final String sortType, Integer sortOrder, Integer currPage, Integer pageSize) {
        Page<KpnSiteMovie> page = new Page<>(currPage, pageSize);
        LambdaQueryWrapper<KpnSiteMovie> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(KpnSiteMovie::getMovieId);
        wrapper.eq(KpnSiteMovie::getSiteId, sid);
        wrapper.eq(KpnSiteMovie::getActorId, actorId);
        wrapper.eq(KpnSiteMovie::getStatus, SiteMovieStatusEnum.ON_SHELF.getStatus());

        if (sortType.equalsIgnoreCase(KpnMovieSortTypeEnum.HOT.getType())) {
            wrapper.orderBy(true, KpnSortOrderEnum.isAsc(sortOrder), KpnSiteMovie::getVv);
        }
        if (sortType.equalsIgnoreCase(KpnMovieSortTypeEnum.LATEST.getType())) {
            wrapper.orderBy(true, KpnSortOrderEnum.isAsc(sortOrder), KpnSiteMovie::getCreateTime);
        }
        if (sortType.equalsIgnoreCase(KpnMovieSortTypeEnum.DURATION.getType())) {
            wrapper.orderBy(true, KpnSortOrderEnum.isAsc(sortOrder), KpnSiteMovie::getDuration);
        }
        Page<KpnSiteMovie> actorMoviesPage = this.baseMapper.selectPage(page, wrapper);
        Long total = page.getTotal();
        Integer totalPage = (int) (total % pageSize == 0 ? total / pageSize : total / pageSize + 1);
        List<Long> movieIds = actorMoviesPage.getRecords().stream().map(KpnSiteMovie::getMovieId).collect(Collectors.toList());

        return PornPageResult.<KpnSiteMovieBaseVo>builder()
                .data(getSiteMovieByIds(sid, movieIds, false))
                .currPage(currPage)
                .pageSize(pageSize)
                .count(total)
                .totalPage(totalPage)
                .build();
    }

    @Override
    public List<KpnSiteMovieBaseVo> getFillingSiteMovie(Long sid, List<Long> movieIds) {
        LambdaQueryChainWrapper<KpnSiteMovie> lambdaQueryChainWrapper = this.lambdaQuery();
        lambdaQueryChainWrapper.select(KpnSiteMovie::getMovieId);
        lambdaQueryChainWrapper.eq(KpnSiteMovie::getSiteId, sid);
        lambdaQueryChainWrapper.eq(KpnSiteMovie::getStatus, SiteMovieStatusEnum.ON_SHELF.getStatus());
//        if (ObjectUtil.isNotEmpty(searchParam.getPayType())) {
//            lambdaQueryChainWrapper.eq(KpnSiteMovie::getPayType, searchParam.getPayType());
//        }
        if (CollectionUtil.isNotEmpty(movieIds)) {
            lambdaQueryChainWrapper.notIn(KpnSiteMovie::getMovieId, movieIds);
        }
        List<KpnSiteMovie> fillingMovies = lambdaQueryChainWrapper.list();
        if (CollectionUtil.isEmpty(fillingMovies)) {
            return new ArrayList<>();
        }
        List<Long> fillingMovieIds = fillingMovies.stream().map(KpnSiteMovie::getMovieId).collect(Collectors.toList());
        return getSiteMovieByIds(sid, fillingMovieIds, false);
    }

    @Override
    public PornPageResult<KpnSiteMovieBaseVo> searchSiteMovieKeywords(Long sid, String keywords, Integer currPage, Integer pageSize) {
        List<Long> movieIds = PornUtil.searchByKeywords(sid, keywords);
        CollectionUtil.sort(movieIds, (o1, o2) -> (int) (o2 - o1));

        List<Long> resultMovieIds = new ArrayList<>();
        int start = (currPage - 1) * pageSize;
        int end = start + (pageSize - 1);

        try {
            for (int i = start; i <= end; i++) {
                resultMovieIds.add(movieIds.get(i));
            }
        }catch(Exception ignored){
        }

        List<KpnSiteMovieBaseVo> siteMovieBaseVos = getSiteMovieByIds(sid, resultMovieIds, false);
//        siteMovieBaseVos.sort(KpnSiteMovieBaseVo::compareByVv);

        if (CollectionUtil.isNotEmpty(movieIds)) {
            siteSearchDateService.saveRptSiteSearchDateNumber(sid, keywords);
            siteSearchTotalService.saveRptSiteSearchTotalNumber(sid, keywords);
        }

        long count = movieIds.size();
        return PornPageResult.<KpnSiteMovieBaseVo>builder()
                .data(siteMovieBaseVos)
                .currPage(currPage)
                .pageSize(pageSize)
                .count(count)
                .totalPage((int) (count % pageSize == 0 ? count / pageSize : (count / pageSize + 1)))
                .build();
    }

    @Override
    public PornPageResult<KpnSiteMovieBaseVo> searchDepot(Long sid, Integer from, Long fromId, String sortType, Integer sortOrder, MovieSearchConditionDto searchConditionDto, Integer currPage, Integer pageSize) {
        Page<KpnSiteMovie> page = new Page<>(currPage, pageSize);

        List<Long> movieIds = new ArrayList<>();
        Long total = 0L;
        if (KpnSiteMovieSearchFromEnum.isSearch(from)) {
            LambdaQueryWrapper<KpnSiteMovie> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(KpnSiteMovie::getSiteId, sid);
            //国家
            if (!KpnMovieCountryEnum.isAll(searchConditionDto.getCountry())) {
                wrapper.eq(KpnSiteMovie::getCountry, searchConditionDto.getCountry());
            }
            //影片类型
            if (!KpnMovieTypeEnum.isAll(searchConditionDto.getType())) {
                wrapper.eq(KpnSiteMovie::getType, searchConditionDto.getType());
            }
            //付费类型
            if (!KpnMoviePayTypeEnum.isAll(searchConditionDto.getPayType())) {
                wrapper.eq(KpnSiteMovie::getPayType, searchConditionDto.getPayType());
            }
            //拍摄性质
            if (!KpnMovieShootingEnum.isAll(searchConditionDto.getShooting())) {
                wrapper.eq(KpnSiteMovie::getShootingType, searchConditionDto.getShooting());
            }
            //字幕类型
            if (!KpnMovieSubtitleEnum.isAll(searchConditionDto.getSubtitle())) {
                wrapper.eq(KpnSiteMovie::getSubtitleType, searchConditionDto.getSubtitle());
            }
            if (sortType.equalsIgnoreCase(KpnMovieSortTypeEnum.HOT.getType())) {
                if (KpnSortOrderEnum.isAsc(sortOrder)) {
                    wrapper.orderByAsc(KpnSiteMovie::getVv);
                } else {
                    wrapper.orderByDesc(KpnSiteMovie::getVv);
                }
            }
            else if (sortType.equalsIgnoreCase(KpnMovieSortTypeEnum.LATEST.getType())) {
                if (KpnSortOrderEnum.isAsc(sortOrder)) {
                    wrapper.orderByAsc(KpnSiteMovie::getCreateTime);
                } else {
                    wrapper.orderByDesc(KpnSiteMovie::getCreateTime);
                }
            }
            else if (sortType.equalsIgnoreCase(KpnMovieSortTypeEnum.DURATION.getType())) {
                if (KpnSortOrderEnum.isAsc(sortOrder)) {
                    wrapper.orderByAsc(KpnSiteMovie::getDuration);
                } else {
                    wrapper.orderByDesc(KpnSiteMovie::getDuration);
                }
            } else {
                wrapper.orderByDesc(KpnSiteMovie::getVv);
            }
            Page<KpnSiteMovie> list = baseMapper.selectPage(page, wrapper);
            total = page.getTotal();
            movieIds = list.getRecords().stream().map(KpnSiteMovie::getMovieId).collect(Collectors.toList());
        }
        //标签
        else if (KpnSiteMovieSearchFromEnum.isTag(from)) {
            //最热 默认
            String redisKey = StrUtil.format(PornConstants.RedisKey.KPN_SITE_TAG_MOVIEID_VV, sid, fromId);
            //最新
            if (sortType.equalsIgnoreCase(KpnMovieSortTypeEnum.LATEST.getType())) {
                redisKey = StrUtil.format(PornConstants.RedisKey.KPN_SITE_TAG_MOVIEID_LATEST, sid, fromId);
            }
            //时长
            else if (sortType.equalsIgnoreCase(KpnMovieSortTypeEnum.DURATION.getType())) {
                redisKey = StrUtil.format(PornConstants.RedisKey.KPN_SITE_TAG_MOVIEID_DURATION, sid, fromId);
            }

            //倒序(默认)
            int startIndex = (currPage - 1) * pageSize;
            int endIndex = startIndex + (pageSize - 1);
            //正序
            if (KpnSortOrderEnum.isAsc(sortOrder)) {
                endIndex = -(pageSize * (currPage - 1)) + (-1);
                startIndex = endIndex + (-(pageSize-1));
            }

            List<String> movieIdStrs = (ArrayList) RedisRepository.getList(redisKey, startIndex, endIndex);
            boolean hasNullVoElem = movieIdStrs.stream().anyMatch(Objects::isNull);
            if (!hasNullVoElem) {
                movieIds = movieIdStrs.stream().map(Long::valueOf).collect(Collectors.toList());
                //正序
                if (KpnSortOrderEnum.isAsc(sortOrder)) {
                    CollectionUtil.reverse(movieIds);
                }
            }
            total = RedisRepository.length(redisKey);
        }
        //主题 TOPIC
        else if (KpnSiteMovieSearchFromEnum.isTopic(from)) {
            //最热 默认
            String redisKey = StrUtil.format(PornConstants.RedisKey.KPN_SITE_TOPIC_MOVIEID_VV, sid, fromId);
            //最新
            if (sortType.equalsIgnoreCase(KpnMovieSortTypeEnum.LATEST.getType())) {
                redisKey = StrUtil.format(PornConstants.RedisKey.KPN_SITE_TOPIC_MOVIEID_LATEST, sid, fromId);
            }
            //时长
            else if (sortType.equalsIgnoreCase(KpnMovieSortTypeEnum.DURATION.getType())) {
                redisKey = StrUtil.format(PornConstants.RedisKey.KPN_SITE_TOPIC_MOVIEID_DURATION, sid, fromId);
            }

            //倒序(默认)
            int startIndex = (currPage - 1) * pageSize;
            int endIndex = startIndex + (pageSize - 1);
            //正序
            if (KpnSortOrderEnum.isAsc(sortOrder)) {
                endIndex = -(pageSize * (currPage - 1)) + (-1);
                startIndex = endIndex + (-(pageSize-1));
            }

            List<String> movieIdStrs = (ArrayList) RedisRepository.getList(redisKey, startIndex, endIndex);
            boolean hasNullVoElem = movieIdStrs.stream().anyMatch(Objects::isNull);
            if (!hasNullVoElem) {
                movieIds = movieIdStrs.stream().map(Long::valueOf).collect(Collectors.toList());
                //正序
                if (KpnSortOrderEnum.isAsc(sortOrder)) {
                    CollectionUtil.reverse(movieIds);
                }
            }
            total = RedisRepository.length(redisKey);
        }
        //频道
        else if (KpnSiteMovieSearchFromEnum.isChannel(from)) {
            //最热 默认
            String redisKey = StrUtil.format(PornConstants.RedisKey.KPN_SITE_CHANNEL_MOVIEID_VV, sid, fromId);
            //最新
            if (sortType.equalsIgnoreCase(KpnMovieSortTypeEnum.LATEST.getType())) {
                redisKey = StrUtil.format(PornConstants.RedisKey.KPN_SITE_CHANNEL_MOVIEID_LATEST, sid, fromId);
            }
            //时长
            else if (sortType.equalsIgnoreCase(KpnMovieSortTypeEnum.DURATION.getType())) {
                redisKey = StrUtil.format(PornConstants.RedisKey.KPN_SITE_CHANNEL_MOVIEID_DURATION, sid, fromId);
            }

            //倒序(默认)
            int startIndex = (currPage - 1) * pageSize;
            int endIndex = startIndex + (pageSize - 1);
            //正序
            if (KpnSortOrderEnum.isAsc(sortOrder)) {
                endIndex = -(pageSize * (currPage - 1)) + (-1);
                startIndex = endIndex + (-(pageSize - 1));
            }

            List<String> movieIdStrs = (ArrayList) RedisRepository.getList(redisKey, startIndex, endIndex);
            boolean hasNullVoElem = movieIdStrs.stream().anyMatch(Objects::isNull);
            if (!hasNullVoElem) {
                movieIds = movieIdStrs.stream().map(Long::valueOf).collect(Collectors.toList());
                //正序
                if (KpnSortOrderEnum.isAsc(sortOrder)) {
                    CollectionUtil.reverse(movieIds);
                }
            }
            total = RedisRepository.length(redisKey);
        }
        //热门VIP推荐
        else if (KpnSiteMovieSearchFromEnum.isVipRecommend(from)) {
            String redisKey = StrUtil.format(PornConstants.RedisKey.KPN_SITE_VIP_MOVIEID_VV, sid);

            //倒序(默认)
            int startIndex = (currPage - 1) * pageSize;
            int endIndex = startIndex + (pageSize - 1);
            //正序
            if (KpnSortOrderEnum.isAsc(sortOrder)) {
                endIndex = -(pageSize * (currPage - 1)) + (-1);
                startIndex = endIndex + (-(pageSize - 1));
            }

            List<String> movieIdStrs = (ArrayList) RedisRepository.getList(redisKey, startIndex, endIndex);
            boolean hasNullVoElem = movieIdStrs.stream().anyMatch(Objects::isNull);
            if (!hasNullVoElem) {
                movieIds = movieIdStrs.stream().map(Long::valueOf).collect(Collectors.toList());
                //正序
                if (KpnSortOrderEnum.isAsc(sortOrder)) {
                    CollectionUtil.reverse(movieIds);
                }
            }
            total = RedisRepository.length(redisKey);
        }
        //最新
        else if (KpnSiteMovieSearchFromEnum.isLatest(from)) {
            String redisKey = StrUtil.format(PornConstants.RedisKey.KPN_SITE_ALL_MOVIEID_LATEST, sid);

            //倒序(默认)
            int startIndex = (currPage - 1) * pageSize;
            int endIndex = startIndex + (pageSize - 1);
            //正序
            if (KpnSortOrderEnum.isAsc(sortOrder)) {
                endIndex = -(pageSize * (currPage - 1)) + (-1);
                startIndex = endIndex + (-(pageSize - 1));
            }

            List<String> movieIdStrs = (ArrayList) RedisRepository.getList(redisKey, startIndex, endIndex);
            boolean hasNullVoElem = movieIdStrs.stream().anyMatch(Objects::isNull);
            if (!hasNullVoElem) {
                movieIds = movieIdStrs.stream().map(Long::valueOf).collect(Collectors.toList());
                //正序
                if (KpnSortOrderEnum.isAsc(sortOrder)) {
                    CollectionUtil.reverse(movieIds);
                }
            }
            total = RedisRepository.length(redisKey);
        }
        //最热
        else if (KpnSiteMovieSearchFromEnum.isHottest(from)) {
            //最热 默认
            String redisKey = StrUtil.format(PornConstants.RedisKey.KPN_SITE_ALL_MOVIEID_VV, sid, fromId);

            //倒序(默认)
            int startIndex = (currPage - 1) * pageSize;
            int endIndex = startIndex + (pageSize - 1);
            //正序
            if (KpnSortOrderEnum.isAsc(sortOrder)) {
                endIndex = -(pageSize * (currPage - 1)) + (-1);
                startIndex = endIndex + (-(pageSize - 1));
            }

            List<String> movieIdStrs = (ArrayList) RedisRepository.getList(redisKey, startIndex, endIndex);
            boolean hasNullVoElem = movieIdStrs.stream().anyMatch(Objects::isNull);
            if (!hasNullVoElem) {
                movieIds = movieIdStrs.stream().map(Long::valueOf).collect(Collectors.toList());
                //正序
                if (KpnSortOrderEnum.isAsc(sortOrder)) {
                    CollectionUtil.reverse(movieIds);
                }
            }
            total = RedisRepository.length(redisKey);
        }
        Integer totalPage = (int) (total % pageSize == 0 ? total / pageSize : total / pageSize + 1);
        List<KpnSiteMovieBaseVo> KpnSiteMovieBaseVos = this.getSiteMovieByIds(sid, movieIds, false);

        return PornPageResult.<KpnSiteMovieBaseVo>builder()
                .data(KpnSiteMovieBaseVos)
                .currPage(currPage)
                .pageSize(pageSize)
                .count(total)
                .totalPage(totalPage)
                .build();
    }

    @Override
    public List<Long> getSiteMovieIds(Long sid) {
        return this.lambdaQuery()
                .select(KpnSiteMovie::getMovieId)
                .eq(KpnSiteMovie::getSiteId, sid)
                .eq(KpnSiteMovie::getStatus, SiteMovieStatusEnum.ON_SHELF.getStatus())
                .list()
                .stream().map(KpnSiteMovie::getMovieId).collect(Collectors.toList());
    }

    @Override
    public List<Long> getSiteMovieIdsOrderByVv(Long sid, Boolean isVip) {
        LambdaQueryChainWrapper<KpnSiteMovie> wrapper = this.lambdaQuery()
                .select(KpnSiteMovie::getMovieId)
                .eq(KpnSiteMovie::getSiteId, sid)
                .eq(KpnSiteMovie::getStatus, SiteMovieStatusEnum.ON_SHELF.getStatus());

        if (isVip) {
            wrapper.eq(KpnSiteMovie::getPayType, true);
        }
        wrapper.orderByAsc(KpnSiteMovie::getVv, KpnSiteMovie::getId);
        List<KpnSiteMovie> kpnSiteMovies = wrapper.list();
        List<Long> movieIds = kpnSiteMovies.stream().map(KpnSiteMovie::getMovieId).collect(Collectors.toList());
        return movieIds;
    }

    @Override
    public List<KpnSiteMovieBaseVo> searchSiteVipMovieTop5(Long sid) {
        String siteVipMovieIdsKey = StrUtil.format(PornConstants.RedisKey.KPN_SITE_VIP_MOVIEID_VV, sid);
        List<String> movieIdStrs = (ArrayList) RedisRepository.getList(siteVipMovieIdsKey, 0, 4);

        List<Long> movieIds = movieIdStrs.stream().map(Long::parseLong).collect(Collectors.toList());

        return getSiteMovieByIds(sid, movieIds, false);
    }

    private void cacheSiteMovieVv(String siteMovieVvKey, Long sid, Long movieId) {
        Object siteMovieVvObj = RedisRepository.get(siteMovieVvKey);

        if (ObjectUtil.isEmpty(siteMovieVvObj)) {
            String lockKey = StrUtil.format("Lock:" + siteMovieVvKey, sid, movieId);
            boolean lockedSuccess = RedissLockUtil.tryLock(lockKey, PornConstants.Lock.WAIT_TIME, PornConstants.Lock.LEASE_TIME);
            if (!lockedSuccess) {
                throw new RuntimeException("加锁失败");
            }
            try {
                siteMovieVvObj = RedisRepository.get(siteMovieVvKey);
                if (ObjectUtil.isEmpty(siteMovieVvObj)) {
                    Long vv = this.lambdaQuery()
                            .eq(KpnSiteMovie::getSiteId, sid)
                            .eq(KpnSiteMovie::getMovieId, movieId)
                            .one().getVv();

                    RedisRepository.setExpire(siteMovieVvKey, vv, PornConstants.RedisKey.EXPIRE_TIME_30_DAYS, TimeUnit.SECONDS);
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            } finally {
                RedissLockUtil.unlock(lockKey);
            }
        }
    }

    private void cacheSiteMovieFavorites(String siteMovieFavoritesKey, Long sid, Long movieId) {
        Object siteMovieFavoritesObj = RedisRepository.get(siteMovieFavoritesKey);

        if (ObjectUtil.isEmpty(siteMovieFavoritesObj)) {
            String lockKey = StrUtil.format("Lock:" + siteMovieFavoritesKey, sid, movieId);
            boolean lockedSuccess = RedissLockUtil.tryLock(lockKey, PornConstants.Lock.WAIT_TIME, PornConstants.Lock.LEASE_TIME);
            if (!lockedSuccess) {
                throw new RuntimeException("加锁失败");
            }
            try {
                siteMovieFavoritesObj = RedisRepository.get(siteMovieFavoritesKey);
                if (ObjectUtil.isEmpty(siteMovieFavoritesObj)) {
                    Long favorites = this.lambdaQuery()
                            .eq(KpnSiteMovie::getSiteId, sid)
                            .eq(KpnSiteMovie::getMovieId, movieId)
                            .one().getFavorites();

                    RedisRepository.setExpire(siteMovieFavoritesKey, favorites, PornConstants.RedisKey.EXPIRE_TIME_30_DAYS, TimeUnit.SECONDS);
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            } finally {
                RedissLockUtil.unlock(lockKey);
            }
        }
    }
}
