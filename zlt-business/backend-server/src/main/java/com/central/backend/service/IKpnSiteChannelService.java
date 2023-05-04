package com.central.backend.service;

import com.central.backend.co.KpnSiteChannelUpdateCo;
import com.central.common.model.KpnSiteChannel;
import com.central.common.model.PageResult;
import com.central.common.model.Result;
import com.central.common.service.ISuperService;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


/*
 * @Author: Lulu
 * @Date: 2023/2/11
 */
public interface IKpnSiteChannelService extends ISuperService<KpnSiteChannel> {

    PageResult<KpnSiteChannel> findSiteChannelList( Map<String, Object> params);


    Result updateEnabledChannel(KpnSiteChannelUpdateCo params);

   Boolean deleteId( Long id);


    Result saveOrUpdateSiteChannel(KpnSiteChannel siteChannel);

    void saveSiteChannelList(Long siteId,String siteCode,String siteName,String createBy);
}
