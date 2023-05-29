package com.central.backend.controller;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.central.backend.model.dto.ProxyAdminDto;
import com.central.backend.model.dto.SysAdminUserDto;
import com.central.backend.service.IAdminUserService;
import com.central.backend.service.ISiteService;
import com.central.backend.service.IThirdPartyService;
import com.central.backend.util.MD5;
import com.central.common.model.Result;
import com.central.common.model.Site;
import com.central.common.model.SysUser;
import com.central.common.model.ThirdParty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashSet;
import java.util.Set;

@Slf4j
@RestController
@Api(tags = "第三方api")
@Validated
@RequestMapping("/api/thirdParty")
public class ThirdPartyController {


    @Autowired
    private IAdminUserService iAdminUserService;

    @Autowired
    private ISiteService iSiteService;

    @Autowired
    IThirdPartyService iThirdPartyService;



    /**
     * 新增or更新
     *
     * @param sysUser
     * @return
     */
    @ApiOperation("新增代理")
    @PostMapping("/saveProxy")
    public Result saveOrUpdateAdminInfo(@RequestBody ProxyAdminDto proxyAdminDto) {

        if (ObjectUtil.isEmpty(proxyAdminDto)) {
            return Result.failed("请求参数不能为空");
        }
        if (ObjectUtil.isEmpty(proxyAdminDto.getUsername())) {
            return Result.failed("用户名不能为空");
        }

        if (ObjectUtil.isEmpty(proxyAdminDto.getPassword())) {
            return Result.failed("密码不能为空");
        }

        if (ObjectUtil.isEmpty(proxyAdminDto.getRandom())) {
            return Result.failed("随机字符不能为空");
        }
        if (ObjectUtil.isEmpty(proxyAdminDto.getSiteCode())) {
            return Result.failed("商户编码不能为空");
        }

        if (ObjectUtil.isEmpty(proxyAdminDto.getSign())) {
            return Result.failed("签名摘要不能为空");
        }
        Site site=iSiteService.getOne(new QueryWrapper<Site>().eq("code",proxyAdminDto.getSiteCode()));
        if (ObjectUtil.isEmpty(site)) {
            return Result.failed("商户不存在");
        }

        ThirdParty thirdParty= iThirdPartyService.getOne(new QueryWrapper<ThirdParty>().eq("site_code",proxyAdminDto.getSiteCode()));
        String secretKey=thirdParty.getSecretKey();
        String sign= MD5.encrypt(proxyAdminDto.getRandom()+proxyAdminDto.getSiteCode()+proxyAdminDto.getUsername()+secretKey);
        if (!proxyAdminDto.getSign().equals(sign)) {
            return Result.failed("签名摘要错误");
        }


        SysUser sysUser=iAdminUserService.getMerchantAdministrator(proxyAdminDto.getSiteCode());
        if (ObjectUtil.isEmpty(sysUser)) {
            return Result.failed("请联系后台添加商户管理员");
        }

        SysAdminUserDto sysAdminUserDto=new SysAdminUserDto();

        sysAdminUserDto.setEnabled(true);
        sysAdminUserDto.setPassword(proxyAdminDto.getPassword());
        sysAdminUserDto.setUsername(proxyAdminDto.getUsername());
        Set set=new HashSet<Long>();
        set.add(11L);
        sysAdminUserDto.setRoleIds(set);
        sysAdminUserDto.setSiteCode(proxyAdminDto.getSiteCode());
        sysAdminUserDto.setSiteId(site.getId());
        sysAdminUserDto.setSiteName(site.getName());

        return iAdminUserService.saveOrUpdateAdminInfo(sysAdminUserDto,sysUser);
    }
}
