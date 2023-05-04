package com.central.backend.controller.movie;

import com.central.common.vo.LanguageNameMulti;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel("站点影片")
public class KpnSiteMovieVo extends LanguageNameMulti {
    @ApiModelProperty(value = "站点影片ID")
    private Long id;
    @ApiModelProperty("播放地址")
    private String url;
    @ApiModelProperty("封面地址")
    private String coverUrl;
    @ApiModelProperty(value = "影片id")
    private Long movieId;
    @ApiModelProperty("影片名称(多语言)")
    private String name;
    @ApiModelProperty(value = "影片名称-中文")
    private String nameZh;
    @ApiModelProperty(value = "影片名称-英文")
    private String nameEn;
    @ApiModelProperty(value = "影片名称-柬文")
    private String nameKh;
    @ApiModelProperty(value = "演员id")
    private Long actorId;
    @ApiModelProperty(value = "演员名(多语言)")
    private String actorName;
//    @ApiModelProperty(value = "演员中文名")
//    private String actorNameZh;
//    @ApiModelProperty(value = "演员英文名")
//    private String actorNameEn;
//    @ApiModelProperty(value = "演员柬文名")
//    private String actorNameKh;
    @ApiModelProperty("影片站点播放量")
    private Long vv;
    @ApiModelProperty("影片站点收藏量")
    private Long favorites;
    @ApiModelProperty("付费类型 0:免费,1:VIP")
    private Integer payType;
    @ApiModelProperty("付费类型(多语言)")
    private String payTypeDisplay;
    @ApiModelProperty("状态 0待发布,1上架,2下架")
    private Integer status;
    @ApiModelProperty("状态(多语言)")
    private String statusDisplay;
    @ApiModelProperty("国家")
    private Integer country;
    @ApiModelProperty("影片类型 0:无码 1:有码")
    private Integer type;
    @ApiModelProperty("拍摄性质")
    private Integer shootingType;
    @ApiModelProperty("字幕类型")
    private Integer subtitleType;
    @ApiModelProperty("影片时长(HH:mm:ss 如00:10:02)")
    private String duration;
    @ApiModelProperty("番号")
    private String serialNumber;
    @ApiModelProperty("制作商")
    private String company;
    @ApiModelProperty("发行时间(默认为处理时间)")
    private Date publishTime;
    @ApiModelProperty("简介")
    private String remark;
    @ApiModelProperty("标签")
    private List<TagVo> tagVos;
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
    @ApiModelProperty(value = "更新人")
    private String updateBy;
}
