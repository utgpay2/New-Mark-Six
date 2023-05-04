package com.central.backend.service;

import java.util.List;

import com.central.backend.model.co.QueryI18nInfoPageCo;
import com.central.backend.model.co.SaveI18nInfoCo;
import com.central.backend.model.co.UpdateI18nInfoCo;
import com.central.common.dto.I18nSourceDTO;
import com.central.common.model.I18nInfo;
import com.central.common.model.PageResult;
import com.central.common.service.ISuperService;
import com.central.common.vo.I18nInfoPageVO;
import com.central.common.vo.LanguageLabelVO;

public interface II18nInfosService extends ISuperService<I18nInfo> {

    Boolean deleteById(Long id, I18nInfo i18nInfo);

    I18nInfo selectById(Long id) ;

    List<I18nInfo> findListByZhCn(Integer fromOf, String zhCn);

    I18nSourceDTO getBackendFullI18nSource();

    I18nSourceDTO getFrontFullI18nSource(Integer fromOf);

    void initI18nSourceRedis();

    boolean updateI18nInfo(Integer from, UpdateI18nInfoCo param);

    boolean saveI18nInfo(Integer from, SaveI18nInfoCo param);

    PageResult<I18nInfoPageVO> findInfos(QueryI18nInfoPageCo param);

    List<LanguageLabelVO> getLanguageLabel();

}
