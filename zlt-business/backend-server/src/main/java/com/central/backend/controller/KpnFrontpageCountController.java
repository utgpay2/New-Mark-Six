package com.central.backend.controller;

import java.util.List;
import java.util.Map;

import cn.hutool.core.util.ObjectUtil;
import com.central.backend.model.vo.KpnFrontpageCountVO;
import com.central.backend.service.IKpnFrontpageCountService;
import com.central.common.annotation.LoginUser;
import com.central.common.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import com.central.common.model.Result;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 首页访问量统计
 *
 * @author yixiu
 * @date 2023-02-09 19:41:45
 */
@Slf4j
@RestController
@RequestMapping("/kpnfrontpagecount")
@Api(tags = "首页访问量统计")
public class KpnFrontpageCountController {
    @Autowired
    private IKpnFrontpageCountService kpnFrontpageCountService;

    /**
     * 运营数据
     */
    @ApiOperation(value = "运营数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "status", value = "1：今日 2：昨日 3：本月 4：总计", required = true, dataType = "Integer")
    })
    @GetMapping
    public Result list(@RequestParam Map<String, Object> params,@ApiIgnore @LoginUser SysUser user) {
        if (ObjectUtil.isEmpty(params)) {
            return Result.failed("请求参数不能为空");
        }
        KpnFrontpageCountVO kpnFrontpageCountVO = kpnFrontpageCountService.findSummaryData(params,user);
        return Result.succeed(kpnFrontpageCountVO, "查询成功");
    }
    /**
     * 数据走势
     */
    @ApiOperation(value = "数据走势")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startTime", value = "如果是日 1 到 31 如果是月 1到12", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "endTime", value = "如果是日 1 到 31 如果是月 1到12", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "status", value = "1：日 2：月", required = true, dataType = "Integer")
    })
    @GetMapping("/dataTrend")
    public Result dataTrend(@RequestParam Map<String, Object> params,@ApiIgnore @LoginUser SysUser user) {
        if (ObjectUtil.isEmpty(params)) {
            return Result.failed("请求参数不能为空");
        }
        List<KpnFrontpageCountVO> kpnFrontpageCountList = kpnFrontpageCountService.dataTrend(params,user);
        return Result.succeed(kpnFrontpageCountList, "查询成功");
    }
//
//    /**
//     * 查询
//     */
//    @ApiOperation(value = "查询")
//    @GetMapping("/{id}")
//    public Result findUserById(@PathVariable Long id) {
//        KpnFrontpageCount model = kpnFrontpageCountService.getById(id);
//        return Result.succeed(model, "查询成功");
//    }
//
//    /**
//     * 新增or更新
//     */
//    @ApiOperation(value = "保存")
//    @PostMapping
//    public Result save(@RequestBody KpnFrontpageCount kpnFrontpageCount) {
//        kpnFrontpageCountService.saveOrUpdate(kpnFrontpageCount);
//        return Result.succeed("保存成功");
//    }
//
//    /**
//     * 删除
//     */
//    @ApiOperation(value = "删除")
//    @DeleteMapping("/{id}")
//    public Result delete(@PathVariable Long id) {
//        kpnFrontpageCountService.removeById(id);
//        return Result.succeed("删除成功");
//    }
}
