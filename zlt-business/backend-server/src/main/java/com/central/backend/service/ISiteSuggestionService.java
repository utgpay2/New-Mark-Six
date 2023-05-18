package com.central.backend.service;


import com.central.backend.co.SiteSuggestionCo;
import com.central.backend.co.SiteSuggestionUpdateCo;
import com.central.common.model.SiteSuggestion;
import com.central.common.model.PageResult;
import com.central.common.model.Result;
import com.central.common.service.ISuperService;

public interface ISiteSuggestionService extends ISuperService<SiteSuggestion> {

    PageResult<SiteSuggestion> findSuggestionList(SiteSuggestionCo params) ;


    Result updateSuggestionStatus(SiteSuggestionUpdateCo params);


}
