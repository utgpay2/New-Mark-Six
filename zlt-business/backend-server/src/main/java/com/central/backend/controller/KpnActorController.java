package com.central.backend.controller;

import java.util.Map;

import cn.hutool.core.util.ObjectUtil;
import com.central.backend.model.vo.KpnActorVO;
import com.central.backend.service.IKpnActorService;
import com.central.common.annotation.LoginUser;
import com.central.common.model.KpnActor;
import com.central.common.model.SysUser;
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
 * 演员列表
 *
 * @author yixiu
 * @date 2023-02-03 16:31:09
 */
@Slf4j
@RestController
@RequestMapping("/kpnactor")
@Api(tags = "演员列表")
public class KpnActorController {
    @Autowired
    private IKpnActorService kpnActorService;

    /**
     * 列表
     */
    @ApiOperation(value = "查询列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "演员ID", required = false, dataType = "Long"),
            @ApiImplicitParam(name = "nameZh", value = "中文名", required = false, dataType = "String"),
            @ApiImplicitParam(name = "nameEn", value = "英文名", required = false, dataType = "String"),
            @ApiImplicitParam(name = "nameKh", value = "柬文名", required = false, dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "起始时间查询", required = false),
            @ApiImplicitParam(name = "endTime", value = "结束时间查询", required = false),
            @ApiImplicitParam(name = "orderByParms", value = "排序字段：1影片数量、2播放数量，3收藏数，默认演员创建时间", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "sortBy", value = "排序方式：1正序、2倒叙(默认)", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "分页结束位置", required = true, dataType = "Integer")
    })
    @GetMapping
    public Result<PageResult<KpnActorVO>> list(@RequestParam Map<String, Object> params, @ApiIgnore @LoginUser SysUser user) {
        if (ObjectUtil.isEmpty(params)) {
            return Result.failed("请求参数不能为空");
        }
        if (ObjectUtil.isEmpty(params.get("page"))) {
            return Result.failed("分页起始位置不能为空");
        }
        if (ObjectUtil.isEmpty(params.get("limit"))) {
            return Result.failed("分页结束位置不能为空");
        }
        return Result.succeed(kpnActorService.findList(params,user));
    }

    /**
     * 查询
     */
    @ApiOperation(value = "查询演员详情")
    @GetMapping("/{id}")
    public Result findUserById(@PathVariable Long id) {
        if (ObjectUtil.isEmpty(id)) {
            return Result.failed("ID不能为空");
        }
        KpnActor model = kpnActorService.getById(id);
        return Result.succeed(model, "查询成功");
    }

    /**
     * 新增or更新
     */
    @ApiOperation(value = "添加演员或修改演员")
    @PostMapping
    public Result saveOrUpdateKpnActor(@RequestBody KpnActor kpnActor, @ApiIgnore @LoginUser SysUser user) {
        if (ObjectUtil.isEmpty(kpnActor)) {
            return Result.failed("请求参数不能为空");
        }
        if (ObjectUtil.isEmpty(kpnActor.getNameZh())) {
            return Result.failed("中文名不能为空");
        }
        if (ObjectUtil.isEmpty(kpnActor.getNameEn())) {
            return Result.failed("英文名不能为空");
        }
        if (ObjectUtil.isEmpty(kpnActor.getNameKh())) {
            return Result.failed("柬文名不能为空");
        }
        if (ObjectUtil.isEmpty(kpnActor.getSex())) {
            return Result.failed("性别不能为空");
        }
        if (ObjectUtil.isEmpty(kpnActor.getBirthday())) {
            return Result.failed("出生日期不能为空");
        }
        if (ObjectUtil.isEmpty(kpnActor.getCountryZh())) {
            return Result.failed("国籍(中文)不能为空");
        }
        if (ObjectUtil.isEmpty(kpnActor.getCountryEn())) {
            return Result.failed("国籍(英文)不能为空");
        }
        if (ObjectUtil.isEmpty(kpnActor.getCountryKh())) {
            return Result.failed("国籍(柬文)不能为空");
        }
        if (ObjectUtil.isEmpty(kpnActor.getAvatarUrl())) {
            return Result.failed("头像不能为空");
        }
        if (ObjectUtil.isEmpty(kpnActor.getPortraitUrl())) {
            return Result.failed("写真照片不能为空");
        }
        if (ObjectUtil.isNotNull(kpnActor.getRemark())) {
            if (kpnActor.getRemark().length() > 100) {
                return Result.failed("简介长度不能超过100");
            }
        }
        if (ObjectUtil.isNotNull(kpnActor.getInterestZh())) {
            if (kpnActor.getInterestZh().length() > 100) {
                return Result.failed("兴趣(中文)长度不能超过100");
            }
        }
        if (ObjectUtil.isNotNull(kpnActor.getInterestEn())) {
            if (kpnActor.getInterestEn().length() > 100) {
                return Result.failed("兴趣(英文)长度不能超过100");
            }
        }
        if (ObjectUtil.isNotNull(kpnActor.getInterestKh())) {
            if (kpnActor.getInterestKh().length() > 100) {
                return Result.failed("兴趣(柬文)长度不能超过100");
            }
        }
        return kpnActorService.saveOrUpdateKpnActor(kpnActor, user);
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
        return kpnActorService.deleteKpnActor(id);
    }
}
