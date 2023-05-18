package com.central.marksix.mapper;

import com.central.common.model.SiteAnnouncement;
import com.central.db.mapper.SuperMapper;
import com.central.marksix.entity.vo.AnnouncementUserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SiteAnnouncementMapper extends SuperMapper<SiteAnnouncement> {
    List<AnnouncementUserVo> findList(@Param("p") Map<String, Object> params);
}
