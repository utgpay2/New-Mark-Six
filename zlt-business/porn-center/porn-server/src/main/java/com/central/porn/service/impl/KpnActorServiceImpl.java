package com.central.porn.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.central.common.constant.PornConstants;
import com.central.common.model.KpnActor;
import com.central.common.redis.template.RedisRepository;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.porn.mapper.KpnActorMapper;
import com.central.porn.service.IKpnActorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class KpnActorServiceImpl extends SuperServiceImpl<KpnActorMapper, KpnActor> implements IKpnActorService {

    @Override
    public List<KpnActor> getActorByIds(List<Long> actorIds) {
        if (CollectionUtil.isEmpty(actorIds)) {
            return new ArrayList<>();
        }
        List<String> redisKeyList = actorIds.stream().map(actorId -> StrUtil.format(PornConstants.RedisKey.KPN_ACTOR_KEY, actorId)).collect(Collectors.toList());
        List<KpnActor> cachedKpnActors = (ArrayList) RedisRepository.mget(redisKeyList);
        boolean hasNullElem = cachedKpnActors.stream().anyMatch(Objects::isNull);
        if (hasNullElem) {
            cachedKpnActors = new ArrayList<>();
            for (Long actorId : actorIds) {
                String redisActorKey = StrUtil.format(PornConstants.RedisKey.KPN_ACTOR_KEY, actorId);
                KpnActor actor = (KpnActor) RedisRepository.get(redisActorKey);
                if (ObjectUtil.isEmpty(actor)) {
                    actor = getById(actorId);
                    actor.setFavorites(null);
                    if (ObjectUtil.isNotEmpty(actor)) {
                        RedisRepository.setExpire(redisActorKey, actor, PornConstants.RedisKey.EXPIRE_TIME_30_DAYS);
                    }
                }
                cachedKpnActors.add(actor);
            }
        }
        return cachedKpnActors;
    }

    @Async
    @Override
    public void addActorFavorites(Long actorId) {
        this.lambdaUpdate()
                .eq(KpnActor::getId, actorId)
                .setSql(" `favorites` = `favorites` + 1")
                .update();
    }

    @Async
    @Override
    public void removeActorFavorites(Long actorId) {
        this.lambdaUpdate()
                .eq(KpnActor::getId, actorId)
                .setSql(" `favorites` = `favorites` - 1")
                .update();
    }
}
