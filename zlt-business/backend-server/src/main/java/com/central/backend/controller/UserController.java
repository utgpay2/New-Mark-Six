package com.central.backend.controller;


import com.central.backend.co.*;
import com.central.backend.service.IKpnSiteOrderService;
import com.central.backend.service.ISysUserService;
import com.central.common.annotation.LoginUser;
import com.central.common.model.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Slf4j
@RestController
@Api(tags = "会员管理api")
@Validated
@RequestMapping("/user")
public class UserController {


    @Autowired
    private ISysUserService userService;


    @Autowired
    private IKpnSiteOrderService orderService;



    @ApiOperation("查询会员列表")
    @ResponseBody
    @GetMapping("/findUserList")
    public Result<PageResult<SysUser>> findUserList(@ModelAttribute SysUserCo params) {
        PageResult<SysUser> userList = userService.findUserList(params);
        if (userList!=null && userList.getData().size() > 0){
            List<Long> userIds = userList.getData().stream().map(SysUser::getId).collect(Collectors.toList());
            //查询充值订单数据
            List<KpnSiteUserOrder> orderMobileList = orderService.findOrderMobileList(userIds);
            if (orderMobileList!=null && orderMobileList.size()>0){
                Map<Long, KpnSiteUserOrder> map = orderMobileList.stream().collect(Collectors.toMap(KpnSiteUserOrder::getUserId, (p) -> p));
                userList.getData().stream().forEach(info ->{
                    KpnSiteUserOrder siteOrderInfo = map.get(info.getId());
                    if (siteOrderInfo!=null){
                        info.setMobile(siteOrderInfo.getMobile());
                    }
                });
            }
        }
        return Result.succeed(userList);
    }

    /**
     * 新增or更新
     *
     * @param sysUser
     * @return
     */
    @ApiOperation("新增or更新")
    @PostMapping("/saveOrUpdateUserInfo")
    public Result saveOrUpdateUserInfo(@RequestBody SysUser user, @LoginUser SysUser sysUser) {

        if (sysUser!=null){
            if (user.getId() == null) {
                user.setUpdateBy(sysUser.getUsername());
                user.setCreateBy(sysUser.getUsername());
            }else {
                user.setUpdateBy(sysUser.getUsername());
            }
        }
        return userService.saveOrUpdateUserInfo(user);
    }


    /**
     * 重置登录密码
     * @param id
     */
    @ApiOperation("重置登录密码")
    @PutMapping(value = "/password/{id}")
    public Result resetPassword(@PathVariable Long id) {
        String password = userService.resetUpdatePassword(id);
        return Result.succeed(password, "重置成功");
    }



    @ApiOperation(value = "修改会员状态")
    @GetMapping("/updateEnabled")
    public Result updateEnabled(@Valid @ModelAttribute EnabledUserCo params, @LoginUser SysUser sysUser) {
        if (sysUser!=null){
            params.setUpdateBy(sysUser.getUsername());
        }
        Result result = userService.updateEnabled(params);
        return result;
    }

}
