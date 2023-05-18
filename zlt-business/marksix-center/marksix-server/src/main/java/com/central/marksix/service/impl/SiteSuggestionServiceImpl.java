package com.central.marksix.service.impl;

import com.central.common.model.SiteSuggestion;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.marksix.mapper.SiteSuggestionMapper;
import com.central.marksix.service.ISiteSuggestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class SiteSuggestionServiceImpl extends SuperServiceImpl<SiteSuggestionMapper, SiteSuggestion> implements ISiteSuggestionService {

}