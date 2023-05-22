package com.central.backend.controller;

import java.util.List;
import java.util.Map;

import cn.hutool.core.util.ObjectUtil;
import com.central.backend.model.vo.CategoryVO;
import com.central.backend.service.ISiteCategoryLotteryService;
import com.central.backend.service.ICategoryService;
import com.central.common.annotation.LoginUser;
import com.central.common.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import springfox.documentation.annotations.ApiIgnore;

/**
 * 下注分类
 *
 * @author yixiu
 * @date 2023-02-03 16:32:41
 */
@Slf4j
@RestController
@RequestMapping("/mkscategory")
@Api(tags = "下注分类")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private ISiteCategoryLotteryService categoryLotteryService;
    /**
     * 查询所有分类
     */
    @ApiOperation(value = "查询所有下注分类（超级管理员权限）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "分类名称", required = false, dataType = "String"),
            @ApiImplicitParam(name = "sortBy", value = "排序方式：1正序(默认)、2倒叙", required = false, dataType = "Integer")
    })
    @GetMapping("/all")
    public Result<List<Category>> listAll(@ApiIgnore @RequestParam Map<String, Object> params) {
        return Result.succeed(categoryService.findList(params));
    }


    /**
     * 新增or更新
     */
    @ApiOperation(value = "新增or更新下注分类（超级管理员权限）")
    @PostMapping("/saveorupdatecategory")
    public Result saveOrUpdateCategory(@RequestBody Category category, @ApiIgnore @LoginUser SysUser user) {
        if (ObjectUtil.isEmpty(category)) {
            return Result.failed("请求参数不能为空");
        }
        if (ObjectUtil.isEmpty(category.getName())) {
            return Result.failed("分类名称不能为空");
        }
        if (ObjectUtil.isEmpty(category.getSort())) {
            return Result.failed("顺序不能为空");
        }
        if (ObjectUtil.isNotNull(category.getRemark())) {
            if(category.getRemark().length()>100){
                return Result.failed("分类描述长度不能超过100");
            }
        }
        return categoryService.saveOrUpdateCategory(category,user);
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除下注分类（超级管理员权限）")
    @DeleteMapping("/deletecategory/{id}")
    public Result deleteCategory(@PathVariable Long id) {
        if (ObjectUtil.isEmpty(id)) {
            return Result.failed("ID不能为空");
        }
        return categoryService.deleteCategory(id);
    }
    @ApiOperation(value = "查询站点下注分类（系统管理员权限）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "分类名称", required = false, dataType = "String"),
            @ApiImplicitParam(name = "siteLotteryId", value = "站点彩种ID", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "sortBy", value = "排序方式：1正序(默认)、2倒叙", required = false, dataType = "Integer")
    })
    @GetMapping("/listsitecategory")
    public Result<List<CategoryVO>> listSiteCategory(@ApiIgnore @RequestParam Map<String, Object> params) {
        if (ObjectUtil.isEmpty(params)) {
            return Result.failed("请求参数不能为空");
        }
        if (ObjectUtil.isEmpty(params.get("siteLotteryId"))) {
            return Result.failed("站点彩种ID不能为空");
        }
        return Result.succeed(categoryLotteryService.findList(params));
    }
    /**
     * 新增or更新
     */
    @ApiOperation(value = "新增or更新站点下注分类（系统管理员权限）")
    @PostMapping("/saveorupdatesitecategory")
    public Result saveOrUpdateSiteCategory(@RequestBody SiteCategoryLottery category, @ApiIgnore @LoginUser SysUser user) {
        if (ObjectUtil.isEmpty(category)) {
            return Result.failed("请求参数不能为空");
        }
        if (ObjectUtil.isEmpty(category.getCategoryId())) {
            return Result.failed("站点分类ID不能为空");
        }
        if (ObjectUtil.isEmpty(category.getSiteLotteryId())) {
            return Result.failed("站点彩种ID不能为空");
        }
        return categoryLotteryService.saveOrUpdateSiteCategory(category,user);
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除站点下注分类（系统管理员权限）")
    @DeleteMapping("/deletesitecategory/{id}")
    public Result deleteSiteCategory(@PathVariable Long id) {
        if (ObjectUtil.isEmpty(id)) {
            return Result.failed("ID不能为空");
        }
        return categoryLotteryService.deleteSiteCategory(id);
    }
}
