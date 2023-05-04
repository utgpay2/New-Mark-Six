package com.central.backend.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.central.backend.model.co.QueryI18nInfoPageCo;
import com.central.backend.model.co.SaveI18nInfoCo;
import com.central.backend.model.co.UpdateI18nInfoCo;
import com.central.backend.service.II18nInfosService;
import com.central.common.annotation.LoginUser;
import com.central.common.dto.I18nSourceDTO;
import com.central.common.model.I18nInfo;
import com.central.common.model.PageResult;
import com.central.common.model.Result;
import com.central.common.model.SysUser;
import com.central.common.utils.I18nUtil;
import com.central.common.vo.I18nInfoPageVO;
import com.central.common.vo.LanguageLabelVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@Api(tags = "多语言配置")
@RequestMapping("/translate")
public class TranslateController {

    private final II18nInfosService i18nInfosService;

    public TranslateController(II18nInfosService i18nInfosService) {
        this.i18nInfosService = i18nInfosService;
    }

    @PostMapping("/backendUpdate")
    @ApiOperation(value = "更新后台国际化字典")
    public Result<String> backendUpdate(@Validated(SaveI18nInfoCo.Update.class) @RequestBody UpdateI18nInfoCo param,
        @ApiIgnore @LoginUser SysUser sysUser) {
        if (Objects.isNull(param.getFromOf())) {
            return Result.failed("参数必传");
        }
        param.setOperator(null!=sysUser?sysUser.getUsername():"");
        if (!i18nInfosService.updateI18nInfo(param.getFromOf(), param)) {
            return Result.failed("数据重复");
        }
        return Result.succeed("操作成功");
    }

    @PostMapping("/frontUpdate")
    @ApiOperation(value = "更新前台国际化字典")
    public Result<String> frontUpdate(@Validated(SaveI18nInfoCo.Update.class) @RequestBody UpdateI18nInfoCo param,
        @ApiIgnore @LoginUser SysUser sysUser) {
        if (Objects.isNull(param.getFromOf())) {
            return Result.failed("参数必传");
        }
        param.setOperator(null!=sysUser?sysUser.getUsername():"");
        if (!i18nInfosService.updateI18nInfo(param.getFromOf(), param)) {
            return Result.failed("数据重复");
        }
        return Result.succeed("操作成功");
    }

    @DeleteMapping("/backendDelete/{id}")
    @ApiOperation(value = "删除后台国际化字典")
    public Result<String> backendDelete(@PathVariable Long id) {
        return this.deleteById(id);
    }

    @DeleteMapping("/frontDelete/{id}")
    @ApiOperation(value = "删除前台国际化字典")
    public Result<String> frontDelete(@PathVariable Long id) {
        return this.deleteById(id);
    }

    private Result<String> deleteById(Long id) {
        I18nInfo i18nInfo = i18nInfosService.selectById(id);
        if (Objects.isNull(i18nInfo)) {
            return Result.failed("删除失败");
        }
        Boolean b = i18nInfosService.deleteById(id, i18nInfo);
        if (b) {
            return Result.succeed("操作成功");
        } else {
            return Result.failed("删除失败");
        }
    }

    @PostMapping("/backendSave")
    @ApiOperation(value = "新增后台国际化字典")
    public Result<String> backendSave(@Validated({SaveI18nInfoCo.Save.class}) @RequestBody SaveI18nInfoCo param,
        @ApiIgnore @LoginUser SysUser sysUser) {
        param.setOperator(null!=sysUser?sysUser.getUsername():"");
        return this.saveI18nInfo(param);
    }

    @PostMapping("/frontSave")
    @ApiOperation(value = "新增前台国际化字典")
    public Result<String> frontSave(@Validated(SaveI18nInfoCo.Save.class) @RequestBody SaveI18nInfoCo param,
        @ApiIgnore @LoginUser SysUser sysUser) {
        param.setOperator(null!=sysUser?sysUser.getUsername():"");
        return this.saveI18nInfo(param);
    }

    private Result<String> saveI18nInfo(SaveI18nInfoCo param) {
        if (Objects.isNull(param.getFromOf())) {
            return Result.failed("参数必传");
        }
        if (!i18nInfosService.saveI18nInfo(param.getFromOf(), param)) {
            return Result.failed("数据重复");
        }
        return Result.succeed("操作成功");
    }

    @GetMapping("/backendInfos")
    @ApiOperation(value = "查询后台国际化字典分页")
    public Result<PageResult<I18nInfoPageVO>> backendInfos(@ModelAttribute QueryI18nInfoPageCo param) {
        if (ObjectUtil.isEmpty(param)) {
            return Result.failed("请求参数不能为空");
        }
        if (ObjectUtil.isEmpty(param.getPage())) {
            return Result.failed("分页起始位置不能为空");
        }
        if (ObjectUtil.isEmpty(param.getLimit())) {
            return Result.failed("分页结束位置不能为空");
        }
        return Result.succeed(i18nInfosService.findInfos(param));
    }

    @GetMapping("/frontInfos")
    @ApiOperation(value = "查询前台国际化字典分页")
    public Result<PageResult<I18nInfoPageVO>> frontInfos(@ModelAttribute QueryI18nInfoPageCo param) {
        if (ObjectUtil.isEmpty(param)) {
            return Result.failed("请求参数不能为空");
        }
        if (ObjectUtil.isEmpty(param.getPage())) {
            return Result.failed("分页起始位置不能为空");
        }
        if (ObjectUtil.isEmpty(param.getLimit())) {
            return Result.failed("分页结束位置不能为空");
        }
        return Result.succeed(i18nInfosService.findInfos(param));
    }

    @GetMapping("/backendFullSource")
    @ApiOperation(value = "获取所有的后台国际化资源")
    public Result<I18nSourceDTO> backendFullSource() {
        return Result.succeed(i18nInfosService.getBackendFullI18nSource());
    }

    @GetMapping("/languageLabel")
    @ApiOperation(value = "获取语言标签")
    public Result<List<LanguageLabelVO>> languageLabel() {
        List<LanguageLabelVO> languageLabelVOS = i18nInfosService.getLanguageLabel();
        if (CollUtil.isNotEmpty(languageLabelVOS)) {
            languageLabelVOS.forEach(languageLabelVO -> {
                languageLabelVO.setValue(I18nUtil.getBackendValue(languageLabelVO.getValue()));
            });
            return Result.succeed(languageLabelVOS);
        }
        return Result.succeed(languageLabelVOS);
    }
}
