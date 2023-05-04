package com.central.backend.service.impl;

import cn.hutool.core.util.StrUtil;
import com.central.backend.model.FileInfo;
import com.central.common.constant.CommonConstant;
import com.central.oss.config.FileServerProperties;
import com.central.oss.model.ObjectInfo;
import com.central.oss.template.MinioTemplate;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.OutputStream;

/**
 * Minio分布文件服务
 */
@Service
@ConditionalOnProperty(prefix = FileServerProperties.PREFIX, name = "type", havingValue = FileServerProperties.TYPE_MINIO)
public class MinioServiceImpl extends AbstractIFileService {
    @Resource
    private MinioTemplate minioTemplate;

    @Override
    protected String fileType() {
        return FileServerProperties.TYPE_MINIO;
    }

    @Override
    protected ObjectInfo uploadFile(MultipartFile file) {
        return minioTemplate.upload(file);
    }

    @Override
    protected void deleteFile(String objectPath) {
        S3Object s3Object = parsePath(objectPath);
        minioTemplate.delete(s3Object.bucketName, s3Object.objectName);
    }

    @Override
    public void out(String id, OutputStream os) {
        FileInfo fileInfo = baseMapper.selectById(id);
        if (fileInfo != null) {
            S3Object s3Object = parsePath(fileInfo.getPath());
            minioTemplate.out(s3Object.bucketName, s3Object.objectName, os);
        }
    }

    @Setter
    @Getter
    private class S3Object {
        private String bucketName;
        private String objectName;
    }

    private S3Object parsePath(String path) {
        S3Object s3Object = new S3Object();
        if (StrUtil.isNotEmpty(path)) {
            int splitIndex = path.lastIndexOf(CommonConstant.PATH_SPLIT);
            if (splitIndex != -1) {
                s3Object.bucketName = path.substring(0, splitIndex);
                s3Object.objectName = path.substring(splitIndex + 1);
            }
        }
        return s3Object;
    }
}
