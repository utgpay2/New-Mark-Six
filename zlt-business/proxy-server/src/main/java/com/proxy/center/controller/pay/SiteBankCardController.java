package com.proxy.center.controller.pay;//package com.proxy.center.controller.pay;
//
//import java.util.List;
//import java.util.Map;
//
//import cn.hutool.core.util.ObjectUtil;
//import com.proxy.center.service.pay.ISiteBankCardService;
//import com.central.common.annotation.LoginUser;
//import com.central.common.model.SysUser;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
//import io.swagger.annotations.ApiOperation;
//import lombok.extern.slf4j.Slf4j;
//import com.central.common.model.pay.SiteBankCard;
//import com.central.common.model.PageResult;
//import com.central.common.model.Result;
//import springfox.documentation.annotations.ApiIgnore;
//
///**
// * 收款银行卡配置
// *
// * @author yixiu
// * @date 2023-02-03 19:35:22
// */
//@Slf4j
//@RestController
//@RequestMapping("/kpnsitebankcard")
//@Api(tags = "收款银行卡配置")
//public class KpnSiteBankCardController {
//    @Autowired
//    private ISiteBankCardService kpnSiteBankCardService;
//
//    /**
//     * 列表
//     */
//    @ApiOperation(value = "分页查询列表")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
//            @ApiImplicitParam(name = "limit", value = "分页结束位置", required = true, dataType = "Integer")
//    })
//    @GetMapping
//    public Result<PageResult<SiteBankCard>> listPage(@RequestParam Map<String, Object> params,@ApiIgnore @LoginUser SysUser user) {
//        if (ObjectUtil.isEmpty(params)) {
//            return Result.failed("请求参数不能为空");
//        }
//        if (ObjectUtil.isEmpty(params.get("page"))) {
//            return Result.failed("分页起始位置不能为空");
//        }
//        if (ObjectUtil.isEmpty(params.get("limit"))) {
//            return Result.failed("分页结束位置不能为空");
//        }
//        return Result.succeed(kpnSiteBankCardService.findListPage(params,user));
//    }
//    @ApiOperation(value = "查询列表")
//    @GetMapping("/list")
//    public Result<List<SiteBankCard>> list(@RequestParam Map<String, Object> params, @ApiIgnore @LoginUser SysUser user) {
//        return Result.succeed(kpnSiteBankCardService.findList(params,user));
//    }
//
//    /**
//     * 查询
//     */
//    @ApiOperation(value = "查询")
//    @GetMapping("/{id}")
//    public Result findUserById(@PathVariable Long id) {
//        if (ObjectUtil.isEmpty(id)) {
//            return Result.failed("ID不能为空");
//        }
//        SiteBankCard model = kpnSiteBankCardService.getById(id);
//        return Result.succeed(model, "查询成功");
//    }
//
//    /**
//     * 新增or更新
//     */
//    @ApiOperation(value = "新增or更新")
//    @PostMapping
//    public Result saveOrUpdateKpnSiteBankCard(@RequestBody SiteBankCard kpnSiteBankCard, @ApiIgnore @LoginUser SysUser user) {
//        if (ObjectUtil.isEmpty(kpnSiteBankCard)) {
//            return Result.failed("请求参数不能为空");
//        }
//        if (ObjectUtil.isEmpty(kpnSiteBankCard.getAccount())) {
//            return Result.failed("开户姓名不能为空");
//        }
//        if (ObjectUtil.isEmpty(kpnSiteBankCard.getCard())) {
//            return Result.failed("银行卡号不能为空");
//        }
//        if (ObjectUtil.isEmpty(kpnSiteBankCard.getBankId())) {
//            return Result.failed("银行id不能为空");
//        }
//        if (ObjectUtil.isEmpty(kpnSiteBankCard.getBankName())) {
//            return Result.failed("银行不能为空");
//        }
//        if (ObjectUtil.isEmpty(kpnSiteBankCard.getSort())) {
//            return Result.failed("排序不能为空");
//        }
//        if (ObjectUtil.isEmpty(kpnSiteBankCard.getStatus())) {
//            return Result.failed("状态不能为空");
//        }
//        return kpnSiteBankCardService.saveOrUpdateKpnSiteBankCard(kpnSiteBankCard,user);
//    }
//
//    /**
//     * 删除
//     */
//    @ApiOperation(value = "删除")
//    @DeleteMapping("/{id}")
//    public Result delete(@PathVariable Long id) {
//        if (ObjectUtil.isEmpty(id)) {
//            return Result.failed("ID不能为空");
//        }
//        return kpnSiteBankCardService.deleteKpnSiteBankCard(id);
//    }
//}
