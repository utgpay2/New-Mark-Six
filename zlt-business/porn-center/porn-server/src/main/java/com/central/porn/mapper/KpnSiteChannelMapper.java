package com.central.porn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.central.common.model.KpnSiteChannel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface KpnSiteChannelMapper extends BaseMapper<KpnSiteChannel> {

    List<KpnSiteChannel> getMemberChannels(@Param("uid") Long uid);

    List<Long> getChannelMovieIdsSortedByColumn(@Param("sid") Long sid, @Param("channelId") Long channelId, @Param("column") String column);
}
