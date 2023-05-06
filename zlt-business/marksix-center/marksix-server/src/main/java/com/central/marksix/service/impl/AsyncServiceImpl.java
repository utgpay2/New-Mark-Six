package com.central.marksix.service.impl;

import com.central.common.model.KpnSiteActor;
import com.central.marksix.service.IAsyncService;
import com.central.marksix.service.IKpnSiteActorService;
import com.central.marksix.service.IKpnSiteMovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AsyncServiceImpl implements IAsyncService {

    @Autowired
    @Lazy
    private IKpnSiteMovieService siteMovieService;

    @Autowired
    @Lazy
    private IKpnSiteActorService siteActorService;

    @Async
    @Override
    public void addSiteActorFavorites(Long sid, Long actorId) {
        siteActorService.lambdaUpdate()
                .eq(KpnSiteActor::getSiteId, sid)
                .eq(KpnSiteActor::getActorId, actorId)
                .setSql(" `favorites` = `favorites` + 1")
                .update();
    }

    @Async
    @Override
    public void removeSiteActorFavorites(Long sid, Long actorId) {
        siteActorService.lambdaUpdate()
                .eq(KpnSiteActor::getSiteId, sid)
                .eq(KpnSiteActor::getActorId, actorId)
                .setSql(" `favorites` = `favorites` - 1")
                .update();
    }
}
