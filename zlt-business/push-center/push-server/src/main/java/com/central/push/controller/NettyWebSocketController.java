package com.central.push.controller;

import com.central.common.model.Result;
import com.central.push.config.NettyWebSocketGroupServer;
import com.central.push.config.NettyWebSocketServer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ws/api")
@Api(tags = "webSocket消息推送")
public class NettyWebSocketController {

    /**
     * 群发消息内容
     *
     * @param message
     * @return
     */
    @PostMapping(value = "/sendAll")
    @ApiOperation(value = "群发消息")
    public Result sendAllMessage(@RequestParam("message") String message) {
        NettyWebSocketServer.batchSendMessage(message);
        return Result.succeed("群消息发送成功");
    }

    /**
     * 指定用户发消息
     *
     * @param message  消息内容
     * @param userName 用户名
     * @return
     */
    @ApiOperation(value = "指定用户推送消息")
    @PostMapping(value = "/sendOne")
    public Result sendOneMessage(@RequestParam("userName") String userName, @RequestParam("message") String message) {
        String msg = NettyWebSocketServer.sendOneMessage(message, userName);
        if (StringUtils.isBlank(msg)) {
            return Result.succeed(userName + "消息推送成功");
        }
        return Result.failed(msg);
    }

    /**
     * 指定房间发消息
     *
     * @param message 消息内容
     * @param groupId  群组ID
     * @return
     */
    @ApiOperation(value = "指定群组推送消息")
    @PostMapping(value = "/sendMessageByGroupId")
    public Result sendMessageByGroupId(@RequestParam("groupId") String groupId, @RequestParam("message") String message) {
        String msg = NettyWebSocketGroupServer.sendMessageByGroupId(groupId, message);
        if (StringUtils.isBlank(msg)) {
            return Result.succeed(groupId + "号群组消息推送成功");
        }
        return Result.failed(msg);
    }

    /**
     * 指定群组和用户发消息
     *
     * @param message 消息内容
     * @param groupId  群组ID
     * @param userName  用户名
     * @return
     */
    @ApiOperation(value = "指定群组和用户推送消息")
    @PostMapping(value = "/sendMessageByGroupIdAndUserName")
    public Result sendMessageByGroupIdAndUserName(@RequestParam("groupId") String groupId, @RequestParam("userName") String userName, @RequestParam("message") String message) {
        String msg = NettyWebSocketGroupServer.sendMessageByGroupIdAndUserName(groupId, userName, message);
        if (StringUtils.isBlank(msg)) {
            return Result.succeed(groupId + "号群组" + userName + "消息推送成功");
        }
        return Result.failed(msg);
    }

    @ApiOperation(value = "查询所有连接")
    @GetMapping(value = "/getAllConnect")
    public Result getAllConnect() {
        Object allConnect = NettyWebSocketGroupServer.getAllConnect();
        return Result.succeed(allConnect);
    }
}