package com.central.push.config;


import com.alibaba.fastjson.JSONObject;
import com.central.common.model.PushResult;
import com.central.push.constant.SocketTypeConstant;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.yeauty.annotation.*;
import org.yeauty.pojo.Session;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * https://blog.csdn.net/w1014074794/article/details/113985267
 */
@Slf4j
@ServerEndpoint(path = "/ws/asset/{token}", host = "${ws.host}", port = "${ws.port}")
@Component
public class NettyWebSocketServer {

    @Autowired
    private CustomReactiveAuthentication customReactiveAuthentication;

    // concurrent包的线程安全map，用来存放每个客户端对应的Session对象。
    private static Map<String, Session> connectSession = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathVariable String token) {
        String userName = customReactiveAuthentication.authentication(token);
        if (ObjectUtils.isEmpty(userName)) {
            PushResult pushResult = PushResult.failed("认证失败");
            session.sendText(JSONObject.toJSONString(pushResult));
            session.close();
            log.error("/ws/asset/onOpen连接失败,获取用户信息失败,token={}", token);
            return;
        }
        connectSession.put(userName, session);
        log.info("用户:{} 加入连接，当前连接数为：{}", userName, connectSession.size());
        onMessage(session, "连接成功");
    }

    @OnClose
    public void onClose(Session session, @PathVariable String token) {
        String userName = customReactiveAuthentication.authentication(token);
        if (ObjectUtils.isEmpty(userName)) {
            return;
        }
        connectSession.remove(userName);
        log.info("用户:{} 关闭连接，当前连接数为：{}", userName, connectSession.size());
    }

    @OnError
    public void onError(Session session, @PathVariable String token, Throwable throwable) {
        log.error("token{},连接发生错误：{}", token, throwable.getMessage());
        throwable.printStackTrace();
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        log.info("来自客户端的消息：{}", message);
        PushResult pushResult = PushResult.succeed(message, SocketTypeConstant.HEARTBEAT, "客户端消息接收成功");
        session.sendText(JSONObject.toJSONString(pushResult));
    }

    @OnBinary
    public void onBinary(Session session, byte[] bytes) {
        for (byte b : bytes) {
            System.out.println(b);
        }
        session.sendBinary(bytes);
    }

    @OnEvent
    public void onEvent(Session session, Object evt) {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            switch (idleStateEvent.state()) {
                case READER_IDLE:
                    System.out.println("read idle");
                    break;
                case WRITER_IDLE:
                    System.out.println("write idle");
                    break;
                case ALL_IDLE:
                    System.out.println("all idle");
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 群发消息
     *
     * @param message
     * @throws IOException
     */
    public static void batchSendMessage(String message) {
        for (Session session : connectSession.values()) {
            if (session.isOpen()) {
                session.sendText(message);
            }
        }
    }

    /**
     * 指定userName发送消息
     *
     * @param userName
     * @param message
     * @throws IOException
     */
    public static String sendOneMessage(String message, String userName) {
        Session session = null;
        for (Map.Entry<String, Session> map : connectSession.entrySet()) {
            if (map.getKey().equals(userName)) {
                session = map.getValue();
                break;
            }
        }
        if (session != null) {
            session.sendText(message);
            return null;
        }
        log.error("没有找到指定ID的会话：{}", userName);
        return "消息推送失败,没有找到指定会话";
    }
}

