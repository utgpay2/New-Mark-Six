package com.central.backend.controller;

import java.util.List;
import java.util.Map;

import cn.hutool.core.util.ObjectUtil;
import com.central.backend.model.vo.KpnTagCategoryVO;
import com.central.backend.service.IKpnTagCategoryService;
import com.central.common.annotation.LoginUser;
import com.central.common.model.KpnTagCategory;
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
 * 影片标签分类
 *
 * @author yixiu
 * @date 2023-02-03 16:32:41
 */
@Slf4j
@RestController
@RequestMapping("/kpntagcategory")
@Api(tags = "影片标签分类")
public class KpnTagCategoryController {
    @Autowired
    private IKpnTagCategoryService kpnTagCategoryService;

    /**
     * 列表
     */
    @ApiOperation(value = "查询列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "分类名称", required = false, dataType = "String"),
            @ApiImplicitParam(name = "id", value = "分类ID", required = false, dataType = "Long"),
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "分页结束位置", required = true, dataType = "Integer")
    })
    @GetMapping
    public Result<PageResult<KpnTagCategoryVO>> list(@RequestParam Map<String, Object> params, @ApiIgnore @LoginUser SysUser user) {
        if (ObjectUtil.isEmpty(params)) {
            return Result.failed("请求参数不能为空");
        }
        if (ObjectUtil.isEmpty(params.get("page"))) {
            return Result.failed("分页起始位置不能为空");
        }
        if (ObjectUtil.isEmpty(params.get("limit"))) {
            return Result.failed("分页结束位置不能为空");
        }
        return Result.succeed(kpnTagCategoryService.findList(params,user));
    }

    /**
     * 查询所有分类
     */
    @ApiOperation(value = "查询所有分类")
    @GetMapping("/all")
    public Result<List<KpnTagCategory>> listAll() {
        return Result.succeed(kpnTagCategoryService.findList());
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
        KpnTagCategory model = kpnTagCategoryService.getById(id);
        return Result.succeed(model, "查询成功");
    }

    /**
     * 新增or更新
     */
    @ApiOperation(value = "新增or更新")
    @PostMapping
    public Result saveOrUpdateKpnTagCategory(@RequestBody KpnTagCategory kpnTagCategory, @ApiIgnore @LoginUser SysUser user) {
        if (ObjectUtil.isEmpty(kpnTagCategory)) {
            return Result.failed("请求参数不能为空");
        }
        if (ObjectUtil.isEmpty(kpnTagCategory.getName())) {
            return Result.failed("分类名称不能为空");
        }
        if (ObjectUtil.isNotNull(kpnTagCategory.getRemark())) {
            if(kpnTagCategory.getRemark().length()>100){
                return Result.failed("分类描述长度不能超过100");
            }
        }
        return kpnTagCategoryService.saveOrUpdateKpnTagCategory(kpnTagCategory,user);
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
        return kpnTagCategoryService.deleteKpnTagCategory(id);
    }
}
