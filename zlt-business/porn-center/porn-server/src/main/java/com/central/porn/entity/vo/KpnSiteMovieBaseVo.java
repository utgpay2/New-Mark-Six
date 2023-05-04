package com.central.porn.entity.vo;

import com.central.common.vo.LanguageNameMulti;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("站点影片基本信息")
public class KpnSiteMovieBaseVo extends LanguageNameMulti implements Serializable {

    @ApiModelProperty("影片id")
    protected Long id;

    @ApiModelProperty("唯一编码 20位")
    protected String code;

    @ApiModelProperty("影片封面url")
    protected String coverUrl;

    @ApiModelProperty("影片时长(HH:mm:ss 如00:10:02)")
    protected String duration;

    @ApiModelProperty("站点播放量")
    protected Long vv;

    @ApiModelProperty("站点收藏量")
    protected Long favorites;

    @ApiModelProperty(value = "影片名称(多语言)")
    protected String name;

    @ApiModelProperty("影片状态 0:下架,1:上架")
    private Integer status;

    @ApiModelProperty("付费类型 0/false:免费,1/true:付费")
    private Boolean payType;

//    @ApiModelProperty("影片中文名")
//    private String nameZh;
//
//    @ApiModelProperty("影片英文名")
//    private String nameEn;
//
//    @ApiModelProperty("影片柬文名")
//    private String nameKh;

    @ApiModelProperty("影片标签")
    protected List<KpnTagVo> tagVos;
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    public int compareByVv(KpnSiteMovieBaseVo kpnSiteMovieBaseVo) {
        return (int) (this.vv - kpnSiteMovieBaseVo.vv);
    }
}
