package com.central.backend.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.backend.vo.KpnSiteTopicVo;
import com.central.common.model.KpnSiteTopic;
import com.central.db.mapper.SuperMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/*
 * @Author: Lulu
 * @Date: 2023/2/14
 */
@Mapper
public interface KpnSiteTopicMapper extends SuperMapper<KpnSiteTopic> {

    List<KpnSiteTopicVo> selectTopicPage(Page<KpnSiteTopicVo> page, @Param("siteId") Long siteId);


}
