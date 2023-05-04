package com.central.porn.controller;

import com.central.common.model.Result;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
/**
 * 
 *
 * @author yixiu
 * @date 2023-02-03 15:50:11
 */
@Slf4j
@RestController
@RequestMapping("/kpnblackip")
public class KpnBlackIpController {
    @GetMapping("/iperror")
    public Result iperror() {
        return Result.failed("IP地址已经被加入黑名单");
    }
}
