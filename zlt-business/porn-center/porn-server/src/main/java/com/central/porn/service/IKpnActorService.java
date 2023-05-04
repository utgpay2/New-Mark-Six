package com.central.porn.service;

import com.central.common.model.KpnActor;
import com.central.common.service.ISuperService;
import com.central.porn.entity.vo.KpnActorVo;

import java.util.List;


public interface IKpnActorService extends ISuperService<KpnActor> {

    /**
     * 获取演员信息
     *
     * @param actorIds 演员id集合
     * @return
     */
    List<KpnActor> getActorByIds(List<Long> actorIds);

    /**
     * 增加总关注量
     *
     * @param actorId 演员id
     */
    void addActorFavorites(Long actorId);

    /**
     * 减去总关注量
     *
     * @param actorId 演员id
     */
    void removeActorFavorites(Long actorId);

}
