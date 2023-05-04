package com.central.common.model.enums;

/**
 * 广告位置
 */
public enum SiteAdDeviceEnum {
    PC("PC"),
    H5("H5"),
    ;

    private final String device;

    SiteAdDeviceEnum(String device) {
        this.device = device;
    }

    public String getDevice() {
        return device;
    }
}
