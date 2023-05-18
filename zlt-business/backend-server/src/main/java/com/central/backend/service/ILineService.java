package com.central.backend.service;

import com.central.backend.co.LineUpdateCo;
import com.central.common.model.Line;
import com.central.common.model.PageResult;
import com.central.common.model.Result;
import com.central.common.service.ISuperService;

import java.util.Map;

/*
 * @Author: Lulu
 * @Date: 2023/2/6
 */
public interface ILineService extends ISuperService<Line> {


    PageResult<Line> findLineList(Map<String, Object> params);

    boolean saveOrUpdateLine(Line line)  ;

    Result updateEnabledLine(LineUpdateCo params);


    Result delete(Long id) ;


}
