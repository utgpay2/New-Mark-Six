package com.central.oss.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 对象信息
 */
@Setter
@Getter
public class ObjectInfo {
    /**
     * 对象查看路径
     */
    private String objectUrl;
    /**
     * 对象保存路径
     */
    private String objectPath;
}
