package com.central.porn.enums;

/**
 * 固定频道
 */
public enum KpnStableChannelEnum {
    RECOMMEND(4L, "推荐"),
    NEWEST(3L, "最新"),
    POPULAR(2L, "最热"),
    SEARCH(1L, "找片"),
    ;

    private Long sort;
    private String remark;

    KpnStableChannelEnum(Long sort, String remark) {
        this.sort = sort;
        this.remark = remark;
    }

    public Long getSort() {
        return sort;
    }
}
