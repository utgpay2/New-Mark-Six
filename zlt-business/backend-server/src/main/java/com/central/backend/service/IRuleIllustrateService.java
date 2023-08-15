package com.central.backend.service;

import com.central.common.model.Result;
import com.central.common.model.RuleIllustrate;
import com.central.common.model.Statement;
import com.central.common.model.SysUser;
import com.central.common.service.ISuperService;

import java.util.List;

public interface IRuleIllustrateService extends ISuperService<RuleIllustrate> {

    Result saveOrUpdateQuiz(RuleIllustrate ruleIllustrate, SysUser user);

    List<RuleIllustrate> findList(Long  lotteryId);
}
