package com.central.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.central.backend.model.FileInfo;
import com.central.common.model.PageResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;
import java.util.Map;

/**
 * 文件service
 */
public interface IFileService extends IService<FileInfo> {
    FileInfo upload(MultipartFile file) throws Exception;

    PageResult<FileInfo> findList(Map<String, Object> params);

    void delete(String id);

    void out(String id, OutputStream os);
}
