package com.central.marksix.service;

import com.central.common.model.Line;
import com.central.common.service.ISuperService;

import java.util.List;
import java.util.Map;


public interface ILineService extends ISuperService<Line> {

    /**
     * 获取线路
     *
     * @return
     */
    Map<String, List<String>> getLines();
}
