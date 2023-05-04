package com.central.oauth.service.impl;

import com.central.common.redis.template.RedisRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@Slf4j
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class RedisTokensServiceImplTest {

    @Autowired
    RedisTokensServiceImpl redisTokensService;

    @Autowired
    private RedisRepository redisRepository;

    @Test
    public void should_query_sucess(){

        Long size = redisRepository.opsForList().size("client_id_to_access:webApp");
        log.info("{}",size);
    }

}