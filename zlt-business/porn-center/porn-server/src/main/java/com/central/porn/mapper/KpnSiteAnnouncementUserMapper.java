package com.central.porn.mapper;

import com.central.common.model.KpnSiteAnnouncement;
import com.central.common.model.KpnSiteAnnouncementUser;
import com.central.db.mapper.SuperMapper;
import com.central.porn.entity.vo.AnnouncementUserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface KpnSiteAnnouncementUserMapper extends SuperMapper<KpnSiteAnnouncementUser> {
}
