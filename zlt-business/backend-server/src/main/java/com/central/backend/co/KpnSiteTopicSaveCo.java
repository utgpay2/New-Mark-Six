package com.central.backend.co;

import com.central.common.model.KpnSiteTopic;
import com.central.common.model.KpnSiteTopicMovie;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@Data
@ApiModel
public class KpnSiteTopicSaveCo {

    KpnSiteTopic KpnSiteTopicInfo;

    List<KpnSiteTopicMovie> movieList;

}
