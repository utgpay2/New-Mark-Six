package com.central.backend.service;


import com.central.backend.co.KpnSiteSuggestionCo;
import com.central.backend.co.KpnSiteSuggestionUpdateCo;
import com.central.common.model.KpnSiteSuggestion;
import com.central.common.model.PageResult;
import com.central.common.model.Result;
import com.central.common.service.ISuperService;

public interface IKpnSiteSuggestionService extends ISuperService<KpnSiteSuggestion> {

    PageResult<KpnSiteSuggestion> findSuggestionList(KpnSiteSuggestionCo params) ;


    Result updateSuggestionStatus(KpnSiteSuggestionUpdateCo params);


}
