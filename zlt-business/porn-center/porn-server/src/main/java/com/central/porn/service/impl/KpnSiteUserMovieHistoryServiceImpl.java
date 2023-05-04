package com.central.porn.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.model.KpnSiteUserMovieHistory;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.porn.entity.PornPageResult;
import com.central.porn.entity.vo.KpnSiteMovieBaseVo;
import com.central.porn.mapper.KpnSiteUserMovieHistoryMapper;
import com.central.porn.service.IKpnSiteMovieService;
import com.central.porn.service.IKpnSiteUserMovieHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class KpnSiteUserMovieHistoryServiceImpl extends SuperServiceImpl<KpnSiteUserMovieHistoryMapper, KpnSiteUserMovieHistory> implements IKpnSiteUserMovieHistoryService {

    @Autowired
    private IKpnSiteMovieService siteMovieService;

    @Override
    public PornPageResult<KpnSiteMovieBaseVo> getWatchHistoryByPage(Long sid, Long userId, Integer currPage, Integer pageSize) {
        Page<KpnSiteUserMovieHistory> page = new Page<>(currPage, pageSize);
        LambdaQueryWrapper<KpnSiteUserMovieHistory> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(KpnSiteUserMovieHistory::getMovieId);
        wrapper.eq(KpnSiteUserMovieHistory::getUserId, userId);
        wrapper.orderByDesc(KpnSiteUserMovieHistory::getCreateTime);

        Page<KpnSiteUserMovieHistory> historyPage = this.baseMapper.selectPage(page, wrapper);
        Long total = page.getTotal();
        List<Long> movieIds = historyPage.getRecords().stream().map(KpnSiteUserMovieHistory::getMovieId).collect(Collectors.toList());

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

    @Override
    public KpnSiteUserMovieHistory getUserMovieHistory(Long userId, Long movieId) {
        return this.lambdaQuery()
                .eq(KpnSiteUserMovieHistory::getUserId, userId)
                .eq(KpnSiteUserMovieHistory::getMovieId, movieId)
                .one();
    }

    @Async
    @Override
    public void addUserMovieHistory(Long userId, Long movieId) {
        KpnSiteUserMovieHistory userMovieHistory = getUserMovieHistory(userId, movieId);
        if (ObjectUtil.isNotNull(userMovieHistory)) {
            this.lambdaUpdate()
                    .set(KpnSiteUserMovieHistory::getCreateTime, new Date())
                    .eq(KpnSiteUserMovieHistory::getUserId, userId)
                    .eq(KpnSiteUserMovieHistory::getMovieId, movieId)
                    .update();
        } else {
            KpnSiteUserMovieHistory siteUserMovieHistory = KpnSiteUserMovieHistory.builder().userId(userId).movieId(movieId).build();
            save(siteUserMovieHistory);
        }

    }


}
