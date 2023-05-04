package com.central.porn.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 找片条件
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieSearchConditionDto {

    /**
     * 国家 -1:全部,0:日本,1:中国大陆,2:中国台湾,3:韩国,4:欧美,5:东南亚,6:其他地区
     */
    private Integer country;

    /**
     * 影片类型: -1:全部,0:无码,1有码
     */
    private Integer type;

    /**
     * 付费类型: -1:全部,0:免费,1:vip
     */
    private Integer payType;

    /**
     * 拍摄类型: -1:全部,0:专业拍摄,1:偷拍,2:自拍,3:其他
     */
    private Integer shooting;

    /**
     * 字幕类型: -1:全部,0:无字幕,1:中文字幕,2:英文字幕,3:中英文字幕,4:其他字幕
     */
    private Integer subtitle;


}
