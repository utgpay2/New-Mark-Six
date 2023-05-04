package com.central.porn.enums;

/**
 * 站点专题状态
 */
public enum KpnSiteTopicComposingEnum {
    ONE_FALSE(1, "排版id1"),
    TWO_FALSE(2, "排版id2"),
    THREE_FALSE(3, "排版id3"),
    Four_FALSE(4, "排版id4");
    private final Integer status;
    private final String remark;

    KpnSiteTopicComposingEnum(Integer status, String remark) {
        this.status = status;
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public String getRemark() {
        return remark;
    }
}
