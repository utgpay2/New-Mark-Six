package com.central.porn.service;

import com.central.common.model.KpnSiteChannel;
import com.central.common.service.ISuperService;
import com.central.porn.entity.co.MemberChannelSortCo;

import java.util.List;


public interface IKpnSiteChannelService extends ISuperService<KpnSiteChannel> {

    /**
     * 获取站点所有频道
     *
     * @param sid 站点id
     */
    List<KpnSiteChannel> getAllChannelsBySiteId(Long sid);

    /**
     * 获取站点所有非固定频道id
     *
     * @param sid 站点id
     * @return
     */
    List<Long> getSiteNotStableChannelIds(Long sid);

    /**
     * 获取会员频道
     *
     * @param uid 会员id
     * @return
     */
    List<KpnSiteChannel> getMemberChannels(Long uid);

    /**
     * 保存会员频道
     *
     * @param uid            会员id
     * @param channelSortCos 排序集合
     */
    void saveMemberChannelsSort(Long uid, List<MemberChannelSortCo> channelSortCos);

    /**
     * 会员增加频道
     *
     * @param uid       会员id
     * @param username  会员账号
     * @param siteId    站点id
     * @param siteCode  站点编码
     * @param siteName  站点名称
     * @param channelId 频道id
     */
    void addChannel(Long uid, String username, Long siteId, String siteCode, String siteName, Long channelId);

    /**
     * 会员移除频道
     *
     * @param uid       会员id
     * @param channelId 频道id
     */
    void removeChannel(Long uid, Long channelId);

    /**
     * 站点频道影片排序
     *
     * @param sid       站点id
     * @param channelId 频道id
     * @param column    排序字段
     * @return
     */
    List<Long> getChannelMovieIdsSortedByColumn(Long sid, Long channelId, String column);
}
