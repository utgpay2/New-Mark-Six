package com.central.porn.core.listener;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@RefreshScope
@Configuration
public class VipExpireKeyListenerConfig {
    @Value("${spring.redis.database:0}")
    private Integer database;

    @Bean
    public MessageListenerAdapter messageListenerAdapter() {
        return new MessageListenerAdapter(myRedisKeyListener());
    }

    @Bean
    public VipExpireKeyListener myRedisKeyListener() {
        return new VipExpireKeyListener();
    }

    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                                   MessageListenerAdapter messageListenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
//        container.addMessageListener(messageListenerAdapter, new PatternTopic("topic-test"));//测试消息发布订阅功能
        container.addMessageListener(messageListenerAdapter, new PatternTopic("__keyevent@"+database+"__:expired"));
//        container.addMessageListener(messageListenerAdapter, new PatternTopic("__keyevent@0__:del"));
//        container.addMessageListener(messageListenerAdapter, new PatternTopic("__keyspace@0__:salelock")); //message是key名，用于监听某个key
//        container.addMessageListener(messageListenerAdapter, new PatternTopic("__key*@0__:*"));//通配符订阅事件

        return container;
    }
}
