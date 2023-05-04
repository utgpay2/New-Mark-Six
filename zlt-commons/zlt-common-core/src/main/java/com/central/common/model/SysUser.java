package com.central.common.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author zlt
 * 用户实体
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_user")
@ApiModel("用户实体")
public class SysUser extends SuperEntity {

    @ApiModelProperty("站点id")
    private Long siteId;
    @ApiModelProperty("站点编码")
    private String siteCode;
    @ApiModelProperty("站点名称")
    private String siteName;
    @ApiModelProperty("上级id")
    private Long parentId;
    @ApiModelProperty("上级账号")
    private String parentName;
    @ApiModelProperty(value = "邀请码")
    private String inviteCode;
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "昵称")
    private String nickname;
    @ApiModelProperty(value = "我的推广码")
    private String promotionCode;
    @ApiModelProperty(value = "头像地址")
    private String headImgUrl;
    @ApiModelProperty(value = "手机号")
    private String mobile;
    @ApiModelProperty(value = "性别(0:女,1:男)")
    private Integer sex;
    @ApiModelProperty(value = "账号类型：APP：前端app用户，BACKEND：后端管理用户")
    private String type;
    @ApiModelProperty("会员等级 0/false:普通会员,1/true:vip")
    private Boolean vip;
    @ApiModelProperty("vip到期时间")
    private Date vipExpire;
    @ApiModelProperty("K币余额")
    private BigDecimal kBalance;
    @ApiModelProperty("管理站点")
    private String roleSites;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "状态：0/false.禁用，1/true.启用")
    private Boolean enabled;
    @ApiModelProperty(value = "逻辑删除 0/false: 未删除，1/true: 已删除")
    private Boolean isDel;
    @ApiModelProperty(value = "是否登录过 0/false：未登录，1/true：已登录")
    private Boolean isLogin;
    @ApiModelProperty(value = "最后登录时间")
    private Date loginTime;
    @ApiModelProperty(value = "最后登录ip")
    private String loginIp;
	@ApiModelProperty(value = "谷歌验证码是否绑定1 1：已绑定，其他：未绑定")
	private Integer gaBind;
    @ApiModelProperty(value = "谷歌验证码KEY")
    private String gaKey;
    @ApiModelProperty(value = "是否验证 1：是，其他：否")
    private Integer verify;
    @ApiModelProperty(value = "创建方式 0:注册, 1:后台创建")
    private Integer isReg;

    @TableField(exist = false)
    private List<SysRole> roles;
    @TableField(exist = false)
    private String roleId;

    @TableField(exist = false)
    private String roleName;
    @TableField(exist = false)
    private String oldPassword;
    @TableField(exist = false)
    private String newPassword;


    @ApiModelProperty(value = "当前余额")
    @TableField(exist = false)
    private BigDecimal currentBalance;


    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public BigDecimal getCurrentBalance() {
        return currentBalance == null ? BigDecimal.ZERO.setScale(2) : currentBalance.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

}
