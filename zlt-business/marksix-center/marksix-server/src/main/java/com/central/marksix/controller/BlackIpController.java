package com.central.marksix.controller;

import com.central.common.model.Result;
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
@RequestMapping("/blackip")
public class BlackIpController {
    @GetMapping("/iperror")
    public Result iperror() {
        return Result.failed("IP地址已经被加入黑名单");
    }
}
