package com.central.user.model.co;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel
public class SysUserCo {

    private Long id;

    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "昵称")
    private String nickname;
    @ApiModelProperty(value = "头像地址")
    private String headImgUrl;
    @ApiModelProperty(value = "手机号")
    private String mobile;
    @ApiModelProperty(value = "性别")
    private Integer sex;
    @ApiModelProperty(value = "状态：0.禁用，1.启用")
    private Boolean enabled;
    @ApiModelProperty(value = "账号类型：APP：前端app用户，APP_GUEST：前端app游客用户，BACKEND：后端管理用户")
    private String type;
    private String openId;

    @ApiModelProperty(value = "逻辑删除 0：未删除，1：已删除")
    private boolean isDel;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "是否登录过 0：未登录，1：已登录")
    private Boolean isLogin;
    @ApiModelProperty(value = "在线状态：1:在线，2：下线")
    private Integer onlineStatus;

    @ApiModelProperty(value = "逻辑删除 0：未删除，1：已删除")
    private boolean isBet;

    @ApiModelProperty(value = "逻辑删除 0：未删除，1：已删除")
    private String loginIp;

    @ApiModelProperty(value = "投注自动提交 开启：true,关闭：false")
    private Boolean isAutoBet;

    @ApiModelProperty(value = "谷歌验证码KEY")
    private String gaKey;

    @ApiModelProperty(value = "谷歌验证码是否绑定1 1：已绑定，其他：未绑定")
    private Integer gaBind;

    @ApiModelProperty(value = "是否验证 1：是，其他：否")
    private Integer verify;

    @ApiModelProperty(value = "父级")
    private String parent;

    @ApiModelProperty(value = "角色id")
    private String roleId;

    @ApiModelProperty(value = "最后登录时间")
    private String lastLoginTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;
}
