package com.central.porn.entity.vo;

import com.central.common.vo.LanguageNameMulti;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel("站点广告")
public class AnnouncementUserVo implements Serializable {
    @ApiModelProperty(value = "广告Id")
    private Long id;
    @ApiModelProperty(value = "公告标题(中文)")
    private String titleZh;
    @ApiModelProperty(value = "公告内容(中文)")
    private String contentZh;
    @ApiModelProperty(value = "公告标题(英文)")
    private String titleEn;
    @ApiModelProperty(value = "公告内容(英文)")
    private String contentEn;
    @ApiModelProperty(value = "公告标题(柬文)")
    private String titleKh;
    @ApiModelProperty(value = "公告内容(柬文)")
    private String contentKh;
    @ApiModelProperty(value = "状态 0下架,1上架")
    private Integer status;
    @ApiModelProperty(value = "用户id")
    private Long userId;
    @ApiModelProperty(value = "状态 0未读,1已读")
    private Integer isRead;
    @ApiModelProperty(value = "公告时间")
    private Date annTime;
    @ApiModelProperty(value = "读取时间")
    private Date readTime;
}
