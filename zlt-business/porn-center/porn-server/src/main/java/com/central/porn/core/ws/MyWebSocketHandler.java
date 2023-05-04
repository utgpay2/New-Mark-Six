//package com.central.porn.core;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//import org.springframework.web.socket.CloseStatus;
//import org.springframework.web.socket.WebSocketHandler;
//import org.springframework.web.socket.WebSocketMessage;
//import org.springframework.web.socket.WebSocketSession;
//
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
//@Slf4j
//@Service
//public class MyWebSocketHandler implements WebSocketHandler {
//    private static final ConcurrentHashMap<String, WebSocketSession> map = new ConcurrentHashMap();
//
//    @Override
//    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//        log.info("connect websocket successfu!");
//        map.put(session.getId(), session);
//        log.info("当前在线人数:{}", map.size());
//    }
//
//    @Override
//    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
//        log.info("get message : {}", message);
//        for (Map.Entry<String, WebSocketSession> entry : map.entrySet()) {
//            String sessionId = entry.getKey();
//            WebSocketSession socketSession = entry.getValue();
//
//            if (sessionId.equalsIgnoreCase(session.getId())) {
//                continue;
//            }
//            socketSession.sendMessage(message);
//        }
//    }
//
//    @Override
//    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
//        log.info("handle message error");
//    }
//
//    @Override
//    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
//        log.error("connection closed: " + closeStatus);
//        session.close();
//        map.remove(session.getId());
//    }
//
//    @Override
//    public boolean supportsPartialMessages() {
//        return false;
//    }
//}
