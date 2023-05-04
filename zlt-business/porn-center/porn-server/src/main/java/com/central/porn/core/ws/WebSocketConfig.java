//package com.central.porn.config;
//
//import com.central.porn.core.MyWebSocketHandler;
//import com.central.porn.core.WebSocketInterceptor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.socket.config.annotation.EnableWebSocket;
//import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
//import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
//import org.springframework.web.socket.server.standard.ServerEndpointExporter;
//
//@Configuration
//@EnableWebSocket
//public class WebSocketConfig implements WebSocketConfigurer {
//
//    @Autowired
//    private MyWebSocketHandler myWebSocketHandler;
//
//    @Autowired
//    private WebSocketInterceptor webSocketInterceptor;
//
//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        registry.addHandler(myWebSocketHandler, "/ws/online/{sessionId}")
//                .setAllowedOrigins("*")
//                .addInterceptors(webSocketInterceptor);
//    }
//}
