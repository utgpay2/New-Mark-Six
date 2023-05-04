package com.central.backend.controller.pay;

import java.util.List;
import java.util.Map;

import cn.hutool.core.util.ObjectUtil;
import com.central.common.annotation.LoginUser;
import com.central.common.model.SysUser;
import com.central.common.model.pay.KpnSiteBank;
import com.central.backend.service.pay.IKpnSiteBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import com.central.common.model.PageResult;
import com.central.common.model.Result;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 收款银行渠道
 *
 * @author yixiu
 * @date 2023-02-03 19:35:22
 */
@Slf4j
@RestController
@RequestMapping("/kpnsitebank")
@Api(tags = "收款银行渠道")
public class KpnSiteBankController {
    @Autowired
    private IKpnSiteBankService kpnSiteBankService;

    /**
     * 列表
     */
    @ApiOperation(value = "分页查询列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "分页结束位置", required = true, dataType = "Integer")
    })
    @GetMapping
    public Result<PageResult<KpnSiteBank>> listPage(@RequestParam Map<String, Object> params,@ApiIgnore @LoginUser SysUser user) {
        if (ObjectUtil.isEmpty(params)) {
            return Result.failed("请求参数不能为空");
        }
        if (ObjectUtil.isEmpty(params.get("page"))) {
            return Result.failed("分页起始位置不能为空");
        }
        if (ObjectUtil.isEmpty(params.get("limit"))) {
            return Result.failed("分页结束位置不能为空");
        }
        return Result.succeed(kpnSiteBankService.findListPage(params,user));
    }
    @ApiOperation(value = "查询列表")
    @GetMapping("/list")
    public Result<List<KpnSiteBank>> list(@RequestParam Map<String, Object> params, @ApiIgnore @LoginUser SysUser user) {
        return Result.succeed(kpnSiteBankService.findList(params,user));
    }

    /**
     * 查询
     */
    @ApiOperation(value = "查询")
    @GetMapping("/{id}")
    public Result findUserById(@PathVariable Long id) {
        if (ObjectUtil.isEmpty(id)) {
            return Result.failed("ID不能为空");
        }
        KpnSiteBank model = kpnSiteBankService.getById(id);
        return Result.succeed(model, "查询成功");
    }

    /**
     * 新增or更新
     */
    @ApiOperation(value = "新增or更新")
    @PostMapping
    public Result saveOrUpdateKpnSiteBank(@RequestBody KpnSiteBank kpnSiteBank, @ApiIgnore @LoginUser SysUser user) {
        if (ObjectUtil.isEmpty(kpnSiteBank)) {
            return Result.failed("请求参数不能为空");
        }
        if (ObjectUtil.isEmpty(kpnSiteBank.getName())) {
            return Result.failed("银行名称不能为空");
        }
        if (ObjectUtil.isEmpty(kpnSiteBank.getLogoUrl())) {
            return Result.failed("银行图标不能为空");
        }
        if (ObjectUtil.isNotNull(kpnSiteBank.getRemark())) {
            if(kpnSiteBank.getRemark().length()>100){
                return Result.failed("简介长度不能超过100");
            }
        }
        return kpnSiteBankService.saveOrUpdateKpnSiteBank(kpnSiteBank,user);
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        if (ObjectUtil.isEmpty(id)) {
            return Result.failed("ID不能为空");
        }
        return kpnSiteBankService.deleteKpnSiteBank(id);
    }
}
