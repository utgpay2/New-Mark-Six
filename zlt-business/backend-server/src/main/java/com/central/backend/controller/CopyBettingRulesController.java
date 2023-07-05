package com.central.backend.controller;

import cn.hutool.core.util.ObjectUtil;
import com.central.backend.model.dto.CopyBettingSiteDto;
import com.central.backend.service.ICopyBettingRulesService;
import com.central.common.annotation.LoginUser;
import com.central.common.model.Result;
import com.central.common.model.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;


/**
 * 拷贝投注规则
 *
 * @author zlt
 * @date 2023-05-09 18:41:24
 */
@Slf4j
@RestController
@Api(tags = "拷贝投注规则")
public class CopyBettingRulesController {
    @Autowired
    private ICopyBettingRulesService iCopyBettingRulesService;

    @ApiOperation(value = "拷贝整个站点彩种规则到另一个站点（系统管理员权限）")
    @PostMapping("/copybettingsite")
    public Result copybettingsite(@RequestBody CopyBettingSiteDto copyBettingSiteDto, @ApiIgnore @LoginUser SysUser user) {
        if (ObjectUtil.isEmpty(copyBettingSiteDto)) {
            return Result.failed("请求参数不能为空");
        }
        if (ObjectUtil.isEmpty(copyBettingSiteDto.getSourceSiteId())) {
            return Result.failed("源站点ID不能为空");
        }
        if (ObjectUtil.isEmpty(copyBettingSiteDto.getTargetSiteId())) {
            return Result.failed("目标站点ID不能为空");
        }
        if (ObjectUtil.isEmpty(copyBettingSiteDto.getTargetSiteCode())) {
            return Result.failed("目标站点编码不能为空");
        }
        if (ObjectUtil.isEmpty(copyBettingSiteDto.getTargetSiteName())) {
            return Result.failed("目标站点名称不能为空");
        }
        return iCopyBettingRulesService.copybettingsite(copyBettingSiteDto,user);
    }
    @ApiOperation(value = "拷贝整个站点彩种规则到另一个站点（系统管理员权限）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sourceSiteLotteryId", value = "源站点彩种ID", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "targetSiteLotteryId", value = "目标站点彩种ID", required = true, dataType = "Integer")
    })
    @PostMapping("/copybettinglottery")
    public Result copybettinglottery(@RequestParam Long sourceSiteLotteryId,@RequestParam Long targetSiteLotteryId, @ApiIgnore @LoginUser SysUser user) {
        if (ObjectUtil.isEmpty(sourceSiteLotteryId)&&0!=sourceSiteLotteryId) {
            return Result.failed("源站点彩种ID不能为空");
        }
        if (ObjectUtil.isEmpty(targetSiteLotteryId)&&0!=targetSiteLotteryId) {
            return Result.failed("目标站点彩种ID不能为空");
        }
        return iCopyBettingRulesService.copybettinglottery(sourceSiteLotteryId,targetSiteLotteryId,user);
    }
}
