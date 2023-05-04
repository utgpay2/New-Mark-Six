package com.central.porn.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.central.common.constant.PornConstants;
import com.central.common.model.KpnTag;
import com.central.common.redis.template.RedisRepository;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.porn.entity.vo.KpnTagVo;
import com.central.porn.mapper.KpnTagMapper;
import com.central.porn.service.IKpnTagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class KpnTagServiceImpl extends SuperServiceImpl<KpnTagMapper, KpnTag> implements IKpnTagService {

    @Override
    public List<KpnTagVo> getByTagIds(List<Long> tagIds) {
        if (CollectionUtil.isEmpty(tagIds)) {
            return new ArrayList<>();
        }
        List<KpnTag> kpnTags = this.lambdaQuery().in(KpnTag::getId, tagIds).list();

        List<KpnTagVo> result = new ArrayList<>();
        for (KpnTag cachedKpnTag : kpnTags) {
            KpnTagVo kpnTagVo = new KpnTagVo();
            BeanUtil.copyProperties(cachedKpnTag, kpnTagVo);
//            kpnTagVo.setName(LanguageUtil.getLanguageName(kpnTagVo));
            result.add(kpnTagVo);
        }
        return result;
    }
}
