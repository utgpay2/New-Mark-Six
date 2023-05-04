package com.central.porn.service.impl;

import com.central.common.model.KpnMovie;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.porn.mapper.KpnMovieMapper;
import com.central.porn.service.IKpnMovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KpnMovieServiceImpl extends SuperServiceImpl<KpnMovieMapper, KpnMovie> implements IKpnMovieService {

    @Async
    @Override
    public void addMovieVv(Long movieId) {
        this.lambdaUpdate()
                .eq(KpnMovie::getId, movieId)
                .setSql(" `vv` = `vv` + 1")
                .update();
    }

    @Async
    @Override
    public void addMovieFavorites(Long movieId) {
        this.lambdaUpdate()
                .eq(KpnMovie::getId, movieId)
                .setSql(" `favorites` = `favorites` + 1")
                .update();
    }

    @Async
    @Override
    public void removeMovieFavorites(Long movieId) {
        this.lambdaUpdate()
                .eq(KpnMovie::getId, movieId)
                .setSql(" `favorites` = `favorites` - 1")
                .update();
    }
}
