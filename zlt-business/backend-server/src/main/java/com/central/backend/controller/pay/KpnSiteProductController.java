package com.central.backend.controller.pay;

import java.util.List;
import java.util.Map;

import cn.hutool.core.util.ObjectUtil;
import com.central.backend.service.pay.IKpnSiteProductService;
import com.central.common.annotation.LoginUser;
import com.central.common.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import com.central.common.model.pay.KpnSiteProduct;
import com.central.common.model.PageResult;
import com.central.common.model.Result;
import springfox.documentation.annotations.ApiIgnore;

/**
 * vip产品
 *
 * @author yixiu
 * @date 2023-02-03 19:35:22
 */
@Slf4j
@RestController
@RequestMapping("/kpnsiteproduct")
@Api(tags = "vip支付产品")
public class KpnSiteProductController {
    @Autowired
    private IKpnSiteProductService kpnSiteProductService;

    /**
     * 列表
     */
    @ApiOperation(value = "分页查询列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "分页结束位置", required = true, dataType = "Integer")
    })
    @GetMapping("/page")
    public Result<PageResult<KpnSiteProduct>> listPage(@RequestParam Map<String, Object> params,@ApiIgnore @LoginUser SysUser user) {
        if (ObjectUtil.isEmpty(params)) {
            return Result.failed("请求参数不能为空");
        }
        if (ObjectUtil.isEmpty(params.get("page"))) {
            return Result.failed("分页起始位置不能为空");
        }
        if (ObjectUtil.isEmpty(params.get("limit"))) {
            return Result.failed("分页结束位置不能为空");
        }
        return Result.succeed(kpnSiteProductService.findListPage(params,user));
    }

    @ApiOperation(value = "查询列表")
    @GetMapping("/list")
    public Result<List<KpnSiteProduct>> list(@RequestParam Map<String, Object> params, @ApiIgnore @LoginUser SysUser user) {
        return Result.succeed(kpnSiteProductService.findList(params,user));
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
        KpnSiteProduct model = kpnSiteProductService.getById(id);
        return Result.succeed(model, "查询成功");
    }

    /**
     * 新增or更新
     */
    @ApiOperation(value = "新增or更新")
    @PostMapping
    public Result saveOrUpdateKpnSiteProduct(@RequestBody KpnSiteProduct kpnSiteProduct, @ApiIgnore @LoginUser SysUser user) {
        if (ObjectUtil.isEmpty(kpnSiteProduct)) {
            return Result.failed("请求参数不能为空");
        }
        if (ObjectUtil.isEmpty(kpnSiteProduct.getNameZh())) {
            return Result.failed("中文名称不能为空");
        }
        if (ObjectUtil.isEmpty(kpnSiteProduct.getNameEn())) {
            return Result.failed("英文名称不能为空");
        }
        if (ObjectUtil.isEmpty(kpnSiteProduct.getNameKh())) {
            return Result.failed("柬文名称不能为空");
        }
        if (ObjectUtil.isEmpty(kpnSiteProduct.getPrice())) {
            return Result.failed("价格不能为空");
        }
        if (ObjectUtil.isEmpty(kpnSiteProduct.getStatus())) {
            return Result.failed("状态不能为空");
        }
        if (ObjectUtil.isEmpty(kpnSiteProduct.getValidDays())) {
            return Result.failed("天数不能为空");
        }
        if (ObjectUtil.isEmpty(kpnSiteProduct.getSort())) {
            return Result.failed("排序不能为空");
        }
        if (ObjectUtil.isNotNull(kpnSiteProduct.getDiscountRemark())) {
            if(kpnSiteProduct.getDiscountRemark().length()>100){
                return Result.failed("简介长度不能超过100");
            }
        }
        return kpnSiteProductService.saveOrUpdateKpnSiteProduct(kpnSiteProduct,user);
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
        return kpnSiteProductService.deleteKpnSiteProduct(id);
    }
}
