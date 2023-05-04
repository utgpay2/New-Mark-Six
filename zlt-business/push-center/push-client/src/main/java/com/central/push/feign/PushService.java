package com.central.push.feign;

import com.central.common.constant.ServiceNameConstants;
import com.central.common.model.Result;
import com.central.push.feign.callback.PushServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @author zlt
 */
@FeignClient(name = ServiceNameConstants.PUSH_SERVICE, fallbackFactory = PushServiceFallbackFactory.class, decode404 = true)
public interface PushService {

    /**
     * 群发消息
     *
     * @param message
     * @return
     */
    @PostMapping(value = "/ws/api/sendAll")
    Result sendAllMessage(@RequestParam("message") String message);

    /**
     * 私发消息
     *
     * @param message
     * @param userName
     * @return
     */
    @PostMapping(value = "/ws/api/sendOne")
    Result sendOneMessage(@RequestParam("userName") String userName, @RequestParam("message") String message);

    /**
     * 指定群组推送消息
     * @param groupId
     * @param message
     * @return
     */
    @PostMapping(value = "/ws/api/sendMessageByGroupId")
    Result sendMessageByGroupId(@RequestParam("groupId") String groupId, @RequestParam("message") String message);

    /**
     * 指定群组和用户推送消息
     * @param groupId
     * @param message
     * @return
     */
    @PostMapping(value = "/ws/api/sendMessageByGroupIdAndUserName")
    Result sendMessageByGroupIdAndUserName(@RequestParam("groupId") String groupId, @RequestParam("userName") String userName, @RequestParam("message") String message);


}