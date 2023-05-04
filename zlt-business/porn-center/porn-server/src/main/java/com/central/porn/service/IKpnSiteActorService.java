package com.central.porn.service;

import com.central.common.model.KpnSiteActor;
import com.central.common.service.ISuperService;
import com.central.porn.entity.PornPageResult;
import com.central.porn.entity.vo.KpnActorVo;

import java.util.List;


public interface IKpnSiteActorService extends ISuperService<KpnSiteActor> {

    /**
     * 获取演员信息信息
     *
     * @param sid
     * @param actorId
     * @return
     */
    KpnActorVo getKpnActorVo(Long sid, Long actorId);

    /**
     * 获取站点演员收藏量
     *
     * @param sid     站点id
     * @param actorId 演员id
     * @return
     */
    Long getSiteActorFavorites(Long sid, Long actorId);

    /**
     * 收藏演员
     *
     * @param sid     站点id
     * @param userId  会员id
     * @param actorId 演员id
     * @return 最新收藏量
     */
    Long addSiteActorFavorites(Long sid, Long userId, Long actorId);

    /**
     * 取消演员收藏
     *
     * @param sid     站点id
     * @param userId  会员id
     * @param actorId 演员id
     * @return 最新收藏量
     */
    Long removeSiteActorFavorites(Long sid, Long userId, Long actorId);

    /**
     * 获取演员列表-收藏量
     *
     * @param sid       站点id
     * @param sortOrder 排序顺序 ASC DESC
     * @param currPage  当前第几页
     * @param pageSize  每页条数
     * @return 演员列表
     */
    PornPageResult<KpnActorVo> getActorListByFavorites(Long sid, String sortOrder, Integer currPage, Integer pageSize);

    /**
     * 获取演员列表-创建时间
     *
     * @param sid       站点id
     * @param sortOrder 排序顺序 ASC DESC
     * @param currPage  当前第几页
     * @param pageSize  每页条数
     * @return 演员列表
     */
    PornPageResult<KpnActorVo> getActorListByCreateTime(Long sid, String sortOrder, Integer currPage, Integer pageSize);
}
