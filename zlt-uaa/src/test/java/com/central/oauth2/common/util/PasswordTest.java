package com.central.oauth2.common.util;


import com.central.common.utils.PwdEncoderUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
public class PasswordTest {

    @Test
    public void should_generate_pwd(){
        PasswordEncoder encoder = PwdEncoderUtil.getDelegatingPasswordEncoder("bcrypt");

        //  {bcrypt}$2a$10$tuV7Vn328Z8fgZQQtXAmyOWyit6LFfBDrCizKxv7eo9hvo0/AaYIi
        //  {bcrypt}$2a$10$GXryVnkKQfdyq1QjVN5WM.VOEHWqMW7tN74NTMcyNSMDr7fUy.X.C
        //  {bcrypt}$2a$10$2qTjlX7XfLwovyE71x4WzODh.Tt/hIeKVukcc1qyWTBubbkhu6pzu
//        String encodeBase64Key = "dmlkZW8tc2lnbmFsLXBsYXRmb3JtOmQzWml4RDdOcThvdDcy";
        //{bcrypt}$2a$10$ZQyOB7XMEYl8Xxp1yJyU.uozYMvl5vHUbJpCvUS6zwsj33VjfDYfO
        //{bcrypt}$2a$10$YWhJH1V/Hx5fwXmxOOLL1ujol3Uwbrr8pP8FgTfdBLhHf.26CNMnC
//        String encodeBase64Key = "video-signal-webapp:dasRttEUQ9ink4Wpgb4zED";
        //{bcrypt}$2a$10$7AwWQN73.tIo.iuxLHRv2OL4w44yPyxIkhfPv4K9lTGtbDHwZlyPi
//        String encodeBase64Key = "video-signal-platform_YYDS";
        String encodeBase64Key = "123456";

        String result = encoder.encode(encodeBase64Key);
//        String result = encoder.encode("admin123");
        log.info("{}",result);
        Boolean match1 = encoder.matches(encodeBase64Key,"$2a$10$ZQyOB7XMEYl8Xxp1yJyU.uozYMvl5vHUbJpCvUS6zwsj33VjfDYfO");
        log.info("match is {}",match1);
        Boolean match2 = encoder.matches(encodeBase64Key,"{bcrypt}$2a$10$GXryVnkKQfdyq1QjVN5WM.VOEHWqMW7tN74NTMcyNSMDr7fUy.X.C");
        log.info("match is {}",match2);
        Boolean match3 = encoder.matches(encodeBase64Key,"{bcrypt}$2a$10$2qTjlX7XfLwovyE71x4WzODh.Tt/hIeKVukcc1qyWTBubbkhu6pzu");
        log.info("match is {}",match3);
        Boolean match3_1 = encoder.matches(encodeBase64Key,"$2a$10$2qTjlX7XfLwovyE71x4WzODh.Tt/hIeKVukcc1qyWTBubbkhu6pzu");
        log.info("match is {}",match3_1);
    }
}
