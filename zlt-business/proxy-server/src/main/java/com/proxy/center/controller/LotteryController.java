package com.proxy.center.controller;

import com.central.common.annotation.LoginUser;
import com.central.common.model.Lottery;
import com.central.common.model.Result;
import com.central.common.model.SysUser;
import com.central.common.vo.SiteLotteryVo;
import com.proxy.center.service.ILotteryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/lottery")
@Api(tags = "彩票相关API接口")
public class LotteryController {

    @Autowired
    private ILotteryService lotteryService;
    /**
     * 列表
     */
    @ApiOperation(value = "查询彩种列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sortBy", value = "排序方式：1正序(默认)、2倒叙", required = false, dataType = "Integer")
    })
    @GetMapping
    public Result<List<SiteLotteryVo>> list(@ApiIgnore @RequestParam Map<String, Object> params, @ApiIgnore @LoginUser SysUser user) {
        if(null==params){
            params = new HashMap<>();
        }
        params.put("siteId", user.getSiteId());
        return Result.succeed(lotteryService.findListBySiteId(params));
    }
}
