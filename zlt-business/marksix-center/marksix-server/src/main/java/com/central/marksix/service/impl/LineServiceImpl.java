package com.central.marksix.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.central.common.constant.MarksixConstants;
import com.central.common.model.Line;
import com.central.common.redis.template.RedisRepository;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.marksix.enums.LineEnum;
import com.central.marksix.mapper.LineMapper;
import com.central.marksix.service.ILineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class LineServiceImpl extends SuperServiceImpl<LineMapper, Line> implements ILineService {

    @Override
    public Map<String, List<String>> getLines() {
        Map<String, List<String>> lineMap = (Map<String, List<String>>) RedisRepository.get(MarksixConstants.RedisKey.KPN_LINE);
        if (CollectionUtil.isEmpty(lineMap)) {
            List<Line> lines = this.lambdaQuery()
                    .eq(Line::getStatus, true)
                    .orderByAsc(Line::getId)
                    .list();

            lineMap = new TreeMap<>();
            for (Line kpnLine : lines) {
                Integer line = kpnLine.getLine();
                List<String> domains = lineMap.get(String.valueOf(line));
                if (CollectionUtil.isEmpty(domains)) {
                    domains = new ArrayList<>();
                    lineMap.put(String.valueOf(line), domains);
                }
                domains.add(kpnLine.getDomain());
            }

            if (CollectionUtil.isNotEmpty(lineMap)) {
                RedisRepository.setExpire(MarksixConstants.RedisKey.KPN_LINE, lineMap, MarksixConstants.RedisKey.EXPIRE_TIME_30_DAYS, TimeUnit.SECONDS);
            }
        }

        //转翻译
        Map<String, List<String>> lineNameMap = new TreeMap<>();
        for (Map.Entry<String, List<String>> entry : lineMap.entrySet()) {
            Integer line = Integer.valueOf(entry.getKey());
            lineNameMap.put(LineEnum.getNameByLine(line), entry.getValue());
        }
        return lineNameMap;


//        kpnLines.stream().map(kpnLine -> {
//            LineVo kpnLineVo = new LineVo();
//            kpnLineVo.setDomain(kpnLine.getDomain());
//            kpnLineVo.setName(LineEnum.getNameByLine(kpnLine.getLine()));
//            return kpnLineVo;
//        }).collect(Collectors.toList());

    }
}
