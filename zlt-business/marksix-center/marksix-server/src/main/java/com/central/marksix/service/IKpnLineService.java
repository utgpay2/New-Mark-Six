package com.central.marksix.service;

import com.central.common.model.KpnLine;
import com.central.common.service.ISuperService;

import java.util.List;
import java.util.Map;


public interface IKpnLineService extends ISuperService<KpnLine> {

    /**
     * 获取线路
     *
     * @return
     */
    Map<String, List<String>> getLines();
}
