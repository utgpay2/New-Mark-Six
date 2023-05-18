//package com.central.backend.controller;
//
//import com.central.backend.co.*;
//import com.central.backend.service.ISiteOrderService;
//import com.central.common.annotation.LoginUser;
//import com.central.common.model.*;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.math.BigDecimal;
//
//@Slf4j
//@RestController
//@Api(tags = "订单列表api")
//@Validated
//@RequestMapping("/order")
//public class KpnSiteOrderController {
//
//    @Autowired
//    private ISiteOrderService orderService;
//
//
//
//
//    @ApiOperation("查询订单列表列表")
//    @ResponseBody
//    @GetMapping("/findOrderList")
//    public Result<PageResult<SiteUserOrder>> findOrderList(@ModelAttribute SiteOrderCo params) {
//        PageResult<SiteUserOrder> siteOrderList = orderService.findOrderList(params);
//        return Result.succeed(siteOrderList);
//    }
//
//
//    @ApiOperation(value = "总计")
//    @GetMapping("/findOrderTotal")
//    public Result<BigDecimal> findOrderTotal(@ModelAttribute SiteOrderTotalCo params) {
//        return Result.succeed(orderService.findOrderTotal(params));
//    }
//
//
//    @ApiOperation(value = "审核")
//    @GetMapping("/updateStatus")
//    public Result updateStatus(@Valid @ModelAttribute SiteOrderUpdateCo params, @LoginUser SysUser sysUser) {
//        if (sysUser!=null) {
//            params.setUpdateBy(sysUser.getUsername());
//        }
//        Result result = orderService.updateStatus(params);
//        return result;
//    }
//}
