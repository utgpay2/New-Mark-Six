package com.central.backend.util;


import cn.hutool.core.io.FileUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * 图片格式校验
 */
public class PictureUtil {
    public final static String png = ".png";
    public final static String jpeg = ".jpeg";
    public final static String jpg = ".jpg";
    public final static String gif = ".gif";
    public final static String mp4 = ".mp4";

    /**
     * 校验格式
     * @param file
     * @return
     */
    public static Boolean verifyFormat(MultipartFile file){
        Boolean identification=false;
       String  fileName=file.getOriginalFilename();
        //格式
        String substring = fileName.substring(fileName.lastIndexOf("."));
        if (substring.equals(png) ||substring.equals(jpeg)||substring.equals(jpg)||substring.equals(gif)||substring.equals(mp4)){
            identification=true;
        }
        if(file.getSize()>10000000)
        {
            identification=false;
        }
        return identification;
    }


    public static MultipartFile generateRandomName(MultipartFile file){
        //修改名称
        String originalFilename=file.getOriginalFilename();
        String newFileName = UUID.randomUUID().toString();

        File fileTemp= new File(originalFilename);
        try {
            FileUtils.copyInputStreamToFile(file.getInputStream(),fileTemp);
            fileTemp= FileUtil.rename(fileTemp,newFileName,true,true);
            InputStream inputStream=new FileInputStream(fileTemp);
            file=new MockMultipartFile(fileTemp.getName(),fileTemp.getName(),"multipart/form-data",inputStream);
            fileTemp.delete();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
