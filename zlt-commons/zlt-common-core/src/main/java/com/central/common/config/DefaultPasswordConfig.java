package com.central.common.config;

import com.central.common.utils.PwdEncoderUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 密码加密配置类，默认使用 bcrypt 加解密
 * 使用：继承 DefaultPasswordConfig， @Autowired public PasswordEncoder passwordEncoder
 */
public class DefaultPasswordConfig {
	@Bean
	@ConditionalOnMissingBean
	public PasswordEncoder passwordEncoder() {
		return PwdEncoderUtil.getDelegatingPasswordEncoder("bcrypt");
	}
}
