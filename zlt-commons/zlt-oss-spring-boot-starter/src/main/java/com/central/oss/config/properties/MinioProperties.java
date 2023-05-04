package com.central.oss.config.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * MinIO 协议配置
 */
@Setter
@Getter
public class MinioProperties {
    /**
     * 用户名
     */
    private String accessKey;
    /**
     * 密码
     */
    private String accessKeySecret;
    /**
     * 访问端点
     */
    private String endpoint;
    /**
     * bucket名称
     */
    private String bucketName;

    private String externalEndpoint;
}
