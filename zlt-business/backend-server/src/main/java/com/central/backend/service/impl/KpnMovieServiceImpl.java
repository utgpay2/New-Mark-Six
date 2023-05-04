package com.central.backend.service.impl;

import com.central.backend.mapper.KpnMovieMapper;
import com.central.backend.service.IKpnMovieService;
import com.central.common.model.KpnMovie;
import com.central.common.service.impl.SuperServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class KpnMovieServiceImpl extends SuperServiceImpl<KpnMovieMapper, KpnMovie> implements IKpnMovieService {
    @Override
    public List<KpnMovie> getKpnMovie(Map<String, Object> params){
        return baseMapper.findList(params);
    }
}
