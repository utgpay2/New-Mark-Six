package com.central.backend.controller;

import java.util.Map;

import cn.hutool.core.util.ObjectUtil;
import com.central.backend.model.vo.KpnTagVO;
import com.central.backend.service.IKpnTagService;
import com.central.common.annotation.LoginUser;
import com.central.common.model.KpnTag;
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
 * 影片标签
 *
 * @author yixiu
 * @date 2023-02-03 16:32:41
 */
@Slf4j
@RestController
@RequestMapping("/kpntag")
@Api(tags = "影片标签")
public class KpnTagController {
    @Autowired
    private IKpnTagService kpnTagService;

    /**
     * 列表
     */
    @ApiOperation(value = "查询列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "nameZh", value = "标签名称(中文)", required = false, dataType = "String"),
            @ApiImplicitParam(name = "nameEn", value = "标签名称(英文)", required = false, dataType = "String"),
            @ApiImplicitParam(name = "nameKh", value = "标签名称(柬文)", required = false, dataType = "String"),
            @ApiImplicitParam(name = "id", value = "标签ID", required = false, dataType = "Long"),
            @ApiImplicitParam(name = "categoryId", value = "分类ID", required = false, dataType = "Long"),
            @ApiImplicitParam(name = "orderByParms", value = "排序字段：1影片数量、2播放数量，3收藏数，默认标签创建时间", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "sortBy", value = "排序方式：1正序、2倒叙(默认)", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "分页结束位置", required = true, dataType = "Integer")
    })
    @GetMapping
    public Result<PageResult<KpnTagVO>> list(@RequestParam Map<String, Object> params, @ApiIgnore @LoginUser SysUser user) {
        if (ObjectUtil.isEmpty(params)) {
            return Result.failed("请求参数不能为空");
        }
        if (ObjectUtil.isEmpty(params.get("page"))) {
            return Result.failed("分页起始位置不能为空");
        }
        if (ObjectUtil.isEmpty(params.get("limit"))) {
            return Result.failed("分页结束位置不能为空");
        }
        return Result.succeed(kpnTagService.findList(params,user));
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
        KpnTag model = kpnTagService.getById(id);
        return Result.succeed(model, "查询成功");
    }

    /**
     * 新增or更新
     */
    @ApiOperation(value = "新增or更新")
    @PostMapping
    public Result saveOrUpdateKpnTag(@RequestBody KpnTag kpnTag, @ApiIgnore @LoginUser SysUser user) {
        if (ObjectUtil.isEmpty(kpnTag)) {
            return Result.failed("请求参数不能为空");
        }
        if (ObjectUtil.isEmpty(kpnTag.getNameZh())) {
            return Result.failed("中文名称不能为空");
        }
        if (ObjectUtil.isEmpty(kpnTag.getNameKh())) {
            return Result.failed("柬文名称不能为空");
        }
        if (ObjectUtil.isEmpty(kpnTag.getNameEn())) {
            return Result.failed("英文名称不能为空");
        }
        if (ObjectUtil.isEmpty(kpnTag.getCategoryName())) {
            return Result.failed("所属分类不能为空");
        }
        if (ObjectUtil.isEmpty(kpnTag.getCategoryId())) {
            return Result.failed("所属分类ID不能为空");
        }
        return kpnTagService.saveOrUpdateKpnTag(kpnTag,user);
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
        return kpnTagService.removeKpnTag(id);
    }
}
