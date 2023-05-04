package com.central.porn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.model.KpnSiteUserMovieFavorites;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.porn.entity.PornPageResult;
import com.central.porn.entity.vo.KpnSiteMovieBaseVo;
import com.central.porn.mapper.KpnSiteUserMovieFavoritesMapper;
import com.central.porn.service.IKpnSiteMovieService;
import com.central.porn.service.IKpnSiteUserMovieFavoritesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class KpnSiteUserMovieFavoritesServiceImpl extends SuperServiceImpl<KpnSiteUserMovieFavoritesMapper, KpnSiteUserMovieFavorites> implements IKpnSiteUserMovieFavoritesService {

    @Autowired
    @Lazy
    private IKpnSiteMovieService siteMovieService;

    @Override
    public KpnSiteUserMovieFavorites getUserMovieFavorites(Long userId, Long movieId) {
        return this.lambdaQuery()
                .eq(KpnSiteUserMovieFavorites::getUserId, userId)
                .eq(KpnSiteUserMovieFavorites::getMovieId, movieId)
                .one();
    }

    @Async
    @Override
    public void add(Long userId, Long movieId) {
        KpnSiteUserMovieFavorites userMovieFavorites = KpnSiteUserMovieFavorites.builder().userId(userId).movieId(movieId).build();
        save(userMovieFavorites);
    }

    @Async
    @Override
    public void remove(Long userId, Long movieId) {
        this.lambdaUpdate()
                .eq(KpnSiteUserMovieFavorites::getUserId, userId)
                .eq(KpnSiteUserMovieFavorites::getMovieId, movieId)
                .remove();
    }

    @Override
    public PornPageResult<KpnSiteMovieBaseVo> getFavoritesMoviesByPage(Long sid, Long userId, Integer currPage, Integer pageSize) {
        Page<KpnSiteUserMovieFavorites> page = new Page<>(currPage, pageSize);
        LambdaQueryWrapper<KpnSiteUserMovieFavorites> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(KpnSiteUserMovieFavorites::getMovieId);
        wrapper.eq(KpnSiteUserMovieFavorites::getUserId, userId);
        wrapper.orderByDesc(KpnSiteUserMovieFavorites::getCreateTime);

        Page<KpnSiteUserMovieFavorites> historyPage = this.baseMapper.selectPage(page, wrapper);
        Long total = page.getTotal();
        List<Long> movieIds = historyPage.getRecords().stream().map(KpnSiteUserMovieFavorites::getMovieId).collect(Collectors.toList());

        Integer totalPage = (int) (total % pageSize == 0 ? total / pageSize : total / pageSize + 1);
        List<KpnSiteMovieBaseVo> KpnSiteMovieBaseVos = siteMovieService.getSiteMovieByIds(sid, movieIds, false);

        return PornPageResult.<KpnSiteMovieBaseVo>builder()
                .data(KpnSiteMovieBaseVos)
                .currPage(currPage)
                .pageSize(pageSize)
                .count(total)
                .totalPage(totalPage)
                .build();
    }
}
