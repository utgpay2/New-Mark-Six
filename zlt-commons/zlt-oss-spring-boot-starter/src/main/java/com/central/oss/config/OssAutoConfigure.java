package com.central.oss.config;

import com.central.oss.template.FdfsTemplate;
import com.central.oss.template.MinioTemplate;
import com.central.oss.template.S3Template;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

/**
 * spring.factories入口
 */
@EnableConfigurationProperties(FileServerProperties.class)
@Import({FdfsTemplate.class, S3Template.class, MinioTemplate.class})
public class OssAutoConfigure {

}
