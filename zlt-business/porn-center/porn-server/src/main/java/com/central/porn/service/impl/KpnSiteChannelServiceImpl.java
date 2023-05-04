package com.central.porn.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.central.common.constant.PornConstants;
import com.central.common.model.KpnSiteChannel;
import com.central.common.model.KpnSiteUserChannel;
import com.central.common.redis.template.RedisRepository;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.porn.entity.co.MemberChannelSortCo;
import com.central.porn.mapper.KpnSiteChannelMapper;
import com.central.porn.service.IKpnSiteChannelService;
import com.central.porn.service.IKpnSiteUserChannelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class KpnSiteChannelServiceImpl extends SuperServiceImpl<KpnSiteChannelMapper, KpnSiteChannel> implements IKpnSiteChannelService {

    @Autowired
    private IKpnSiteUserChannelService userChannelService;

    @Override
    public List<KpnSiteChannel> getAllChannelsBySiteId(Long sid) {
        //固定频道
//        String stashChannelRedisKey = StrUtil.format(PornConstants.RedisKey.SITE_STASH_CHANNEL_KEY, sid);
//        List<KpnSiteChannel> siteChannels = (List<KpnSiteChannel>) RedisRepository.get(stashChannelRedisKey);
//        if (CollectionUtil.isEmpty(siteChannels)) {
//            siteChannels = this.lambdaQuery()
//                    .eq(KpnSiteChannel::getSiteId, sid)
//                    .eq(KpnSiteChannel::getIsStable, true)
//                    .orderByDesc(KpnSiteChannel::getIsStable)
//                    .orderByDesc(KpnSiteChannel::getSort)
//                    .orderByDesc(KpnSiteChannel::getCreateTime)
//                    .list();
//            if (CollectionUtil.isNotEmpty(siteChannels)) {
//                RedisRepository.setExpire(stashChannelRedisKey, siteChannels, PornConstants.RedisKey.EXPIRE_TIME_30_DAYS);
//            }
//        }

        List<KpnSiteChannel> siteChannels = this.lambdaQuery()
                .eq(KpnSiteChannel::getSiteId, sid)
                .eq(KpnSiteChannel::getIsStable, true)
                .orderByDesc(KpnSiteChannel::getIsStable)
                .orderByDesc(KpnSiteChannel::getSort)
                .orderByDesc(KpnSiteChannel::getCreateTime)
                .list();

        //站点自定义频道,无影片不展示
        String redisKey = StrUtil.format(PornConstants.RedisKey.KPN_SITE_CHANNEL_MOVIEID_VV, sid, PornConstants.Symbol.ASTERISK);
        Set<String> notStashChannelKeys = RedisRepository.keys(redisKey);

        List<Long> channelIds = notStashChannelKeys.stream().map(s -> Long.parseLong(s.substring(s.lastIndexOf(":") + 1))).collect(Collectors.toList());
        if (CollectionUtil.isNotEmpty(channelIds)) {
            List<KpnSiteChannel> kpnSiteChannels = listByIds(channelIds);
            kpnSiteChannels.sort(Comparator.comparingLong(KpnSiteChannel::getSort).thenComparing(KpnSiteChannel::getId).reversed());
            siteChannels.addAll(kpnSiteChannels);
        }
        return siteChannels;
    }

    @Override
    public List<Long> getSiteNotStableChannelIds(Long sid) {
        List<KpnSiteChannel> list = this.lambdaQuery()
                .eq(KpnSiteChannel::getSiteId, sid)
                .eq(KpnSiteChannel::getStatus, true)
                .eq(KpnSiteChannel::getIsStable, false)
                .list();

        return list.stream().map(KpnSiteChannel::getId).collect(Collectors.toList());
    }

    @Override
    public List<KpnSiteChannel> getMemberChannels(Long uid) {
//        String userChannelRedisKey = StrUtil.format(PornConstants.RedisKey.KPN_SITE_USER_CHANNEL_KEY, uid);
//        List<KpnSiteChannel> memberChannels = (List<KpnSiteChannel>) RedisRepository.get(userChannelRedisKey);
//        if (CollectionUtil.isEmpty(memberChannels)) {
//            memberChannels = this.baseMapper.getMemberChannels(uid);
//            if (CollectionUtil.isNotEmpty(memberChannels)) {
//                RedisRepository.setExpire(userChannelRedisKey, memberChannels, PornConstants.RedisKey.EXPIRE_TIME_30_DAYS);
//            }
//        }
//        return memberChannels;

        return this.baseMapper.getMemberChannels(uid);
    }

    @Override
    @Async
    public void saveMemberChannelsSort(Long uid, @RequestBody List<MemberChannelSortCo> channelSortCos) {
//        String userChannelRedisKey = StrUtil.format(PornConstants.RedisKey.KPN_SITE_USER_CHANNEL_KEY, uid);
//        RedisRepository.delete(userChannelRedisKey);

        if (CollectionUtil.isNotEmpty(channelSortCos)) {
            for (MemberChannelSortCo channelSortCo : channelSortCos) {
                userChannelService.lambdaUpdate()
                        .eq(KpnSiteUserChannel::getUserId, uid)
                        .eq(KpnSiteUserChannel::getChannelId, channelSortCo.getChannelId())
                        .set(KpnSiteUserChannel::getSort, channelSortCo.getSort())
                        .update();

            }
        }
        //重新缓存
        getMemberChannels(uid);
        log.info("频道排序完成 userId: {}", uid);
    }

    @Override
    @Async
    @Transactional
    public void addChannel(Long uid, String username, Long siteId, String siteCode, String siteName, Long channelId) {
        //增加排序值
        userChannelService.lambdaUpdate().eq(KpnSiteUserChannel::getUserId, uid)
                .setSql(" `sort` = `sort` + 1")
                .update();

        //新增频道
        KpnSiteUserChannel siteUserChannel = new KpnSiteUserChannel();
        siteUserChannel.setSiteId(siteId);
        siteUserChannel.setSiteCode(siteCode);
        siteUserChannel.setSiteName(siteName);
        siteUserChannel.setChannelId(channelId);
        siteUserChannel.setUserId(uid);
        siteUserChannel.setUserName(username);
        siteUserChannel.setChannelId(channelId);
        siteUserChannel.setSort(PornConstants.Numeric.DEFAULT_SORT_VALUE);
        userChannelService.save(siteUserChannel);

        //重置缓存
//        String userChannelRedisKey = StrUtil.format(PornConstants.RedisKey.KPN_SITE_USER_CHANNEL_KEY, uid);
//        RedisRepository.delete(userChannelRedisKey);
        getMemberChannels(uid);
        log.info("频道添加完成 userId: {},channelId: {}", uid, channelId);
    }

    @Override
    @Async
    @Transactional
    public void removeChannel(Long uid, Long channelId) {
        userChannelService.lambdaUpdate()
                .eq(KpnSiteUserChannel::getUserId, uid)
                .eq(KpnSiteUserChannel::getChannelId, channelId)
                .remove();

        //重置缓存
//        String userChannelRedisKey = StrUtil.format(PornConstants.RedisKey.KPN_SITE_USER_CHANNEL_KEY, uid);
//        RedisRepository.delete(userChannelRedisKey);
        getMemberChannels(uid);
        log.info("频道移除完成 userId: {},channelId: {}", uid, channelId);
    }

    @Override
    public List<Long> getChannelMovieIdsSortedByColumn(Long sid, Long channelId, String column) {
        return this.baseMapper.getChannelMovieIdsSortedByColumn(sid, channelId, column);
    }


}
