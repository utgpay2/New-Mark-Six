package com.central.backend.service.impl;

import com.central.backend.mapper.KpnMovieTagMapper;
import com.central.backend.service.IKpnMovieTagService;
import com.central.common.KpnMovieTag;
import com.central.common.model.KpnTag;
import com.central.common.service.impl.SuperServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class KpnMovieTagServiceImpl extends SuperServiceImpl<KpnMovieTagMapper, KpnMovieTag> implements IKpnMovieTagService {

    @Override
    public List<KpnMovieTag> getKpnMovieTag(Map<String, Object> params){
        return baseMapper.findList(params);
    }

    @Override
    public List<KpnTag> getTagByMovieId(Long movieId) {

        return baseMapper.getTagByMovieId(movieId);
    }
}





















