package com.central.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.central.backend.mapper.RuleIllustrateMapper;
import com.central.backend.service.IRuleIllustrateService;
import com.central.common.model.Result;
import com.central.common.model.RuleIllustrate;
import com.central.common.model.SysUser;
import com.central.common.service.impl.SuperServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Slf4j
@Service
public class RuleIllustrateServiceIpml extends SuperServiceImpl<RuleIllustrateMapper, RuleIllustrate> implements IRuleIllustrateService {


    @Override
    public Result saveOrUpdateQuiz(RuleIllustrate ruleIllustrate, SysUser user) {
        if (null != ruleIllustrate.getId() && 0 != ruleIllustrate.getId()) {
            ruleIllustrate.setUpdateTime(new Date());
            ruleIllustrate.setUpdateBy(null != user ? user.getUsername() : ruleIllustrate.getUpdateBy());
        } else {
            ruleIllustrate.setCreateBy(null != user ? user.getUsername() : ruleIllustrate.getCreateBy());
            ruleIllustrate.setCreateTime(new Date());
            ruleIllustrate.setUpdateTime(new Date());
            ruleIllustrate.setUpdateBy(null != user ? user.getUsername() : ruleIllustrate.getCreateBy());
        }
        this.saveOrUpdate(ruleIllustrate);
        return Result.succeed("保存成功");
    }

    @Override
    public List<RuleIllustrate> findList(Long  lotteryId) {
        QueryWrapper<RuleIllustrate> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(RuleIllustrate::getLotteryId,lotteryId);
        return baseMapper.selectList(queryWrapper);
    }
}
