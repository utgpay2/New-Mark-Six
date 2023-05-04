package com.central.porn.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.central.common.constant.PornConstants;
import com.central.common.model.KpnLine;
import com.central.common.redis.template.RedisRepository;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.porn.entity.vo.KpnLineVo;
import com.central.porn.enums.KpnLineEnum;
import com.central.porn.mapper.KpnLineMapper;
import com.central.porn.service.IKpnLineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@Service
public class KpnLineServiceImpl extends SuperServiceImpl<KpnLineMapper, KpnLine> implements IKpnLineService {

    @Override
    public Map<String, List<String>> getLines() {
        Map<String, List<String>> lineMap = (Map<String, List<String>>) RedisRepository.get(PornConstants.RedisKey.KPN_LINE);
        if (CollectionUtil.isEmpty(lineMap)) {
            List<KpnLine> kpnLines = this.lambdaQuery()
                    .eq(KpnLine::getStatus, true)
                    .orderByAsc(KpnLine::getId)
                    .list();

            lineMap = new TreeMap<>();
            for (KpnLine kpnLine : kpnLines) {
                Integer line = kpnLine.getLine();
                List<String> domains = lineMap.get(String.valueOf(line));
                if (CollectionUtil.isEmpty(domains)) {
                    domains = new ArrayList<>();
                    lineMap.put(String.valueOf(line), domains);
                }
                domains.add(kpnLine.getDomain());
            }

            if (CollectionUtil.isNotEmpty(lineMap)) {
                RedisRepository.setExpire(PornConstants.RedisKey.KPN_LINE, lineMap, PornConstants.RedisKey.EXPIRE_TIME_30_DAYS, TimeUnit.SECONDS);
            }
        }

        //转翻译
        Map<String, List<String>> lineNameMap = new TreeMap<>();
        for (Map.Entry<String, List<String>> entry : lineMap.entrySet()) {
            Integer line = Integer.valueOf(entry.getKey());
            lineNameMap.put(KpnLineEnum.getNameByLine(line), entry.getValue());
        }
        return lineNameMap;


//        kpnLines.stream().map(kpnLine -> {
//            KpnLineVo kpnLineVo = new KpnLineVo();
//            kpnLineVo.setDomain(kpnLine.getDomain());
//            kpnLineVo.setName(KpnLineEnum.getNameByLine(kpnLine.getLine()));
//            return kpnLineVo;
//        }).collect(Collectors.toList());

    }
}
