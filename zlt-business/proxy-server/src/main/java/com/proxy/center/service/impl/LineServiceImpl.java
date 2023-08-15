package com.proxy.center.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.proxy.center.co.LineUpdateCo;
import com.proxy.center.mapper.LineMapper;
import com.proxy.center.service.IAsyncService;
import com.proxy.center.service.ILineService;
import com.central.common.model.Line;
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
public class LineServiceImpl extends SuperServiceImpl<LineMapper, Line> implements ILineService {

    @Autowired
    private IAsyncService asyncService;

    @Override
    public PageResult<Line> findLineList(Map<String, Object> params) {
        Page<Line> page = new Page<>(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));
        LambdaQueryWrapper<Line> wrapper = new LambdaQueryWrapper<>();
        Integer line = MapUtils.getInteger(params, "line");
        if (line != null) {
            wrapper.eq(Line::getLine, line);
        }
        wrapper.orderByDesc(Line::getCreateTime);
        Page<Line> list = baseMapper.selectPage(page, wrapper);
        long total = page.getTotal();
        return PageResult.<Line>builder().data(list.getRecords()).count(total).build();
    }

    @Override
    public boolean saveOrUpdateLine(Line line) {
        boolean insert =false;
        if (line.getId()==null){
            insert = super.save(line);
        }else {
            Line lineInfo = baseMapper.selectById(line.getId());
            if (lineInfo != null) {
                //修改
                insert = super.updateById(line);
            }
        }
        return insert;
    }

    @Override
    public Result updateEnabledLine(LineUpdateCo params) {
        Long id = params.getId();
        Line lineInfo = baseMapper.selectById(id);
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