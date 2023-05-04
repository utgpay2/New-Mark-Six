package com.central.backend.controller;

import cn.hutool.core.bean.BeanUtil;
import com.central.backend.vo.FullSourceVo;
import com.central.common.dto.I18nSourceDTO;
import com.central.common.language.LanguageEnum;
import com.central.common.model.Result;
import com.central.common.model.enums.KpnMovieCountryEnum;
import com.central.common.utils.I18nUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 
 *
 * @author yixiu
 * @date 2023-02-03 15:50:11
 */
@Slf4j
@RestController
@Api(tags = "国家及语言")
public class ConfigController {

    /**
     * 列表
     */
    @ApiOperation(value = "查询国家列表")
    @GetMapping("/country")
    public Result<Map<Integer, String>> list() {
        return Result.succeed(KpnMovieCountryEnum.getOptions());
    }

    /**
     * 列表
     */
    @ApiOperation(value = "语言列表列表")
    @GetMapping("/language")
    public Result<Map<String, String>> languageList() {
        return Result.succeed(LanguageEnum.getOptions());
    }

    /**
     * 语言包-PC
     */
    @ApiOperation(value = "管理后台语言包")
    @GetMapping("/backendfullsource")
    public Result<FullSourceVo> backendFullSource() {
        try {
            FullSourceVo fullSourceVo = new FullSourceVo();
            I18nSourceDTO frontFullSource = I18nUtil.getBackendFullSource();
            BeanUtil.copyProperties(frontFullSource, fullSourceVo);

            return Result.succeed(fullSourceVo, "succeed");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.failed("failed");
        }
    }
}
