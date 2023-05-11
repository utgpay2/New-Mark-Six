package com.central.backend.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.hutool.core.util.ObjectUtil;
import com.central.backend.model.dto.NumberAttributesDto;
import com.central.backend.service.INumberAttributesService;
import com.central.common.annotation.LoginUser;
import com.central.common.model.NumberAttributes;
import com.central.common.model.SysUser;
import com.central.common.utils.StringUtils;
import org.springframework.beans.BeanUtils;
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
 * 号码属性表
 *
 * @author zlt
 * @date 2023-05-08 15:05:53
 */
@Slf4j
@RestController
@RequestMapping("/numberattributes")
@Api(tags = "号码属性表")
public class NumberAttributesController {
    @Autowired
    private INumberAttributesService numberAttributesService;

    /**
     * 列表
     */
    @ApiOperation(value = "查询列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "year", value = "年份", required = true, dataType = "Integer")
    })
    @GetMapping
    public Result<List<NumberAttributes>> list(@RequestParam Map<String, Object> params) {
        if (ObjectUtil.isEmpty(params)) {
            return Result.failed("请求参数不能为空");
        }
        if (ObjectUtil.isEmpty(params.get("year"))) {
            return Result.failed("年份不能为空");
        }
        return Result.succeed(numberAttributesService.findList(params));
    }

    /**
     * 查询
     */
    @ApiOperation(value = "查询")
    @GetMapping("/{id}")
    public Result findUserById(@PathVariable Long id) {
        NumberAttributes model = numberAttributesService.getById(id);
        return Result.succeed(model, "查询成功");
    }

    /**
     * 新增或修改号码属性
     */
    @ApiOperation(value = "新增或修改号码属性")
    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody NumberAttributesDto numberAttributesDto, @ApiIgnore @LoginUser SysUser user) {
        NumberAttributes numberAttributes = new NumberAttributes();
        BeanUtils.copyProperties(numberAttributesDto,numberAttributes);
        if(StringUtils.isNotNull(numberAttributes.getId())){
            numberAttributes.setUpdateBy(user.getUsername());
            numberAttributes.setUpdateTime(new Date());
        }else {
            numberAttributes.setCreateBy(user.getUsername());
            numberAttributes.setCreateTime(new Date());
            numberAttributes.setUpdateBy(user.getUsername());
            numberAttributes.setUpdateTime(new Date());
        }
        numberAttributesService.saveOrUpdate(numberAttributes);
        return Result.succeed("保存成功");
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        numberAttributesService.removeById(id);
        return Result.succeed("删除成功");
    }
}
