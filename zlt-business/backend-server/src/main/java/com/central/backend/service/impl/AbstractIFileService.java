package com.central.backend.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.central.backend.mapper.FileMapper;
import com.central.backend.model.FileInfo;
import com.central.backend.service.IFileService;
import com.central.backend.util.FileUtil;
import com.central.backend.util.PictureUtil;
import com.central.common.model.PageResult;
import com.central.oss.config.FileServerProperties;
import com.central.oss.model.ObjectInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * AbstractIFileService 抽取类
 * 根据zlt.file-server.type 实例化具体对象
 */
@Slf4j
public abstract class AbstractIFileService extends ServiceImpl<FileMapper, FileInfo> implements IFileService {
    private static final String FILE_SPLIT = ".";

    @Autowired
    private FileServerProperties fileProperties;


    @Override
    public FileInfo upload(MultipartFile file) {
        if (file!=null){
            //随机生成文件名字
            file = PictureUtil.generateRandomName(file);
        }
        FileInfo fileInfo = FileUtil.getFileInfo(file);
        if (!fileInfo.getName().contains(FILE_SPLIT)) {
            throw new IllegalArgumentException("缺少后缀名");
        }
        ObjectInfo objectInfo = uploadFile(file);
        fileInfo.setPath(objectInfo.getObjectPath());

        fileInfo.setUrl(objectInfo.getObjectUrl().replace(fileProperties.getMinio().getEndpoint(), fileProperties.getMinio().getExternalEndpoint()));
        // 设置文件来源
        fileInfo.setSource(fileType());
        // 将文件信息保存到数据库
//        baseMapper.insert(fileInfo);

        return fileInfo;
    }

    /**
     * 文件来源
     *
     * @return
     */
    protected abstract String fileType();

    /**
     * 上传文件
     *
     * @param file
     */
    protected abstract ObjectInfo uploadFile(MultipartFile file);

    /**
     * 删除文件
     * @param path 文件路径
     */
    @Override
    public void delete(String path) {
//        FileInfo fileInfo = baseMapper.selectById(id);
//        if (fileInfo != null) {
//            baseMapper.deleteById(fileInfo.getId());
            this.deleteFile(path);
//        }
    }

    /**
     * 删除文件资源
     *
     * @param objectPath 文件路径
     */
    protected abstract void deleteFile(String objectPath);

    @Override
    public PageResult<FileInfo> findList(Map<String, Object> params) {
        Page<FileInfo> page = new Page<>(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));
        List<FileInfo> list = baseMapper.findList(page, params);
        return PageResult.<FileInfo>builder().data(list).count(page.getTotal()).build();
    }
}
