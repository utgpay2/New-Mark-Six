package com.central.common.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("kpn_site_movie")
@ApiModel("站点影片")
public class KpnSiteMovie extends SuperEntity {

    @ApiModelProperty(value = "站点id")
    private Long siteId;
    @ApiModelProperty(value = "站点编码")
    private String siteCode;
    @ApiModelProperty(value = "站点名称")
    private String siteName;
    @ApiModelProperty(value = "演员id")
    private Long actorId;
    @ApiModelProperty("演员中文名")
    private String actorNameZh;
    @ApiModelProperty("演员英文名")
    private String actorNameEn;
    @ApiModelProperty("演员柬文名")
    private String actorNameKh;
    @ApiModelProperty(value = "演员创建时间")
    private Date actorCreateTime;
    @ApiModelProperty(value = "影片id")
    private Long movieId;
    @ApiModelProperty(value = "影片名称-中文")
    private String nameZh;
    @ApiModelProperty(value = "影片名称-英文")
    private String nameEn;
    @ApiModelProperty(value = "影片名称-柬文")
    private String nameKh;
    @ApiModelProperty(value = "时长")
    private String duration;
    @ApiModelProperty("付费类型 0/false:免费,1/true:付费")
    private Boolean payType;
    @ApiModelProperty(value = "影片站点播放量")
    private Long vv;
    @ApiModelProperty(value = "影片站点收藏量")
    private Long favorites;
    @ApiModelProperty(value = "状态 0待发布,1上架,2下架")
    private Integer status;
    @ApiModelProperty("播放地址")
    private String url;
    @ApiModelProperty("封面地址")
    private String coverUrl;
    @ApiModelProperty("影片所属国家 0:日本,1:中国大陆,2:中国台湾,3:韩国,4:欧美,5:东南亚,6:其他地区")
    private Integer country;
    @ApiModelProperty("影片类型 0/false无码 1/true有码")
    private Boolean type;
    @ApiModelProperty("拍摄性质 0:专业拍摄,1:偷拍,2:自拍,3:其他")
    private Integer shootingType;
    @ApiModelProperty("字幕类型 0:无字幕,1:中文,2:英文,3:中英,4:其他")
    private Integer subtitleType;
    @ApiModelProperty("番号")
    private String serialNumber;
    @ApiModelProperty("制作商")
    private String company;
    @ApiModelProperty("发行时间(默认为处理时间)")
    private Date publishTime;
}
