package com.central.push.feign.callback;

import com.central.common.model.Result;
import com.central.push.feign.PushService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * PushService降级工场
 */
@Slf4j
public class PushServiceFallbackFactory implements FallbackFactory<PushService> {

    @Override
    public PushService create(Throwable throwable) {
        return new PushService() {

            @Override
            public Result sendAllMessage(String message) {
                log.error("sendAllMessage群发消息异常");
                return Result.failed("群发消息失败");
            }

            @Override
            public Result sendOneMessage(String userName, String message) {
                log.error("sendOneMessage给用户:{}推送消息异常",userName);
                return Result.failed("私发消息失败");
            }

            @Override
            public Result sendMessageByGroupId(String groupId, String message) {
                log.error("sendMessageByGroupId指定群组推送消息异常,groupId={},message={}",groupId,message);
                return Result.failed("消息推送失败");
            }

            @Override
            public Result sendMessageByGroupIdAndUserName(String groupId, String userName, String message) {
                log.error("sendMessageByGroupId指定群组推送消息异常,groupId={},userName={},message={}", groupId, userName, message);
                return Result.failed("消息推送失败");
            }
        };
    }
}
