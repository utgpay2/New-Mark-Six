package com.central.backend.service;

import com.central.backend.co.KpnLineUpdateCo;
import com.central.common.model.KpnLine;
import com.central.common.model.PageResult;
import com.central.common.model.Result;
import com.central.common.service.ISuperService;

import java.util.Map;

/*
 * @Author: Lulu
 * @Date: 2023/2/6
 */
public interface IKpnLineService extends ISuperService<KpnLine> {


    PageResult<KpnLine> findLineList(Map<String, Object> params);

    boolean saveOrUpdateLine(KpnLine line)  ;

    Result updateEnabledLine(KpnLineUpdateCo params);


    Result delete(Long id) ;


}
