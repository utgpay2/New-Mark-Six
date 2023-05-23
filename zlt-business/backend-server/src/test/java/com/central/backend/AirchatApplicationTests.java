package com.central.backend;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class AirchatApplicationTests {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Test
    void contextLoads() {
        System.out.println(passwordEncoder.encode("zxc123"));
    }

}
