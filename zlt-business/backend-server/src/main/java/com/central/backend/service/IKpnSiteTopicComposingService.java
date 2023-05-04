package com.central.backend.service;

import com.central.common.model.KpnSiteTopicComposing;
import com.central.common.service.ISuperService;

import java.util.List;


public interface IKpnSiteTopicComposingService extends ISuperService<KpnSiteTopicComposing> {

    List<KpnSiteTopicComposing> findTopicComposingList();

}
