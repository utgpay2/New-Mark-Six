package com.central.backend.service;

import com.central.backend.co.KpnSiteTopicSaveCo;
import com.central.backend.co.KpnSiteTopicUpdateCo;
import com.central.backend.vo.KpnSiteTopicVo;
import com.central.common.model.KpnSiteTopic;
import com.central.common.model.PageResult;
import com.central.common.model.Result;
import com.central.common.service.ISuperService;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;


/*
 * @Author: Lulu
 * @Date: 2023/2/14
 */
public interface IKpnSiteTopicService extends ISuperService<KpnSiteTopic> {

    PageResult<KpnSiteTopicVo> findSiteTopicList(Map<String, Object> params);

    Result updateEnabledTopic( KpnSiteTopicUpdateCo params) ;

    Boolean deleteId(Long id);

    Result saveOrUpdateTopic( KpnSiteTopicSaveCo params) ;

}
