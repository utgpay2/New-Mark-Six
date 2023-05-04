package com.central.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.backend.co.KpnLineUpdateCo;
import com.central.backend.mapper.KpnLineMapper;
import com.central.backend.service.IAsyncService;
import com.central.backend.service.IKpnLineService;
import com.central.common.model.KpnLine;
import com.central.common.model.PageResult;
import com.central.common.model.Result;
import com.central.common.service.impl.SuperServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Slf4j
@Service
public class KpnLineServiceImpl extends SuperServiceImpl<KpnLineMapper, KpnLine> implements IKpnLineService {

    @Autowired
    private IAsyncService asyncService;

    @Override
    public PageResult<KpnLine> findLineList(Map<String, Object> params) {
        Page<KpnLine> page = new Page<>(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));
        LambdaQueryWrapper<KpnLine> wrapper = new LambdaQueryWrapper<>();
        Integer line = MapUtils.getInteger(params, "line");
        if (line != null) {
            wrapper.eq(KpnLine::getLine, line);
        }
        wrapper.orderByDesc(KpnLine::getCreateTime);
        Page<KpnLine> list = baseMapper.selectPage(page, wrapper);
        long total = page.getTotal();
        return PageResult.<KpnLine>builder().data(list.getRecords()).count(total).build();
    }

    @Override
    public boolean saveOrUpdateLine(KpnLine line) {
        boolean insert =false;
        if (line.getId()==null){
            insert = super.save(line);
        }else {
            KpnLine lineInfo = baseMapper.selectById(line.getId());
            if (lineInfo != null) {
                //修改
                insert = super.updateById(line);
            }
        }
        return insert;
    }

    @Override
    public Result updateEnabledLine(KpnLineUpdateCo params) {
        Long id = params.getId();
        KpnLine lineInfo = baseMapper.selectById(id);
        if (lineInfo == null) {
            return Result.failed("此线路不存在");
        }
        lineInfo.setStatus(params.getStatus());
        lineInfo.setUpdateBy(params.getUpdateBy());
        int i = baseMapper.updateById(lineInfo);
        return i>0 ? Result.succeed(lineInfo, "更新成功"): Result.failed("更新失败");
    }

    @Override
    public Result delete(Long id) {
        int i = baseMapper.deleteById(id);
        return i>0 ? Result.succeed( "删除成功"): Result.failed("删除失败");
    }
}