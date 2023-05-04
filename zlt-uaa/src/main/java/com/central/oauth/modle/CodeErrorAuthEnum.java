package com.central.oauth.modle;

/**
 * 消息错误码, 属于模块错误码
 * 请求 /oauth/token 返回的错误码
 */
public enum CodeErrorAuthEnum {

    /** 授权相关错误: 逻辑验证错误码 100~200 */
    ERROR_AUTH(100),
    /** 授权相关错误: 账号类型不能为空 */
    ERROR_AUTH_ACCOUNT_TYPE(101),
    /** 授权相关错误: 请在请求参数中携带deviceId参数 */
    ERROR_AUTH_DEVICE_ID(102),
    /** 授权相关错误: 请填写验证码 */
    ERROR_AUTH_EMPTY_VALIDATE_CODE(103),
    /** 授权相关错误: 验证码不存在或已过期 */
    ERROR_AUTH_VALIDATE_CODE(104),
    /** 授权相关错误: 验证码不正确 */
    ERROR_AUTH_VALIDATE_CODE_ERR(105),
    /** 授权相关错误: 请在请求参数中携带googleCode参数 */
    ERROR_AUTH_GOOGLE_CODE(106),
    /** 授权相关错误: 请在请求参数中携带username参数 */
    ERROR_AUTH_NO_PARAMS_USERNAME(107),
    /** 授权相关错误: 用户名或密码错误 */
    ERROR_AUTH_USERNAME_PASSWORD(108),
    /** 授权相关错误: 请先绑定谷歌身份验证器 */
    ERROR_AUTH_GOOGLE_CODE_BIND(109),
    /** 授权相关错误: 谷歌身份验证失败 */
    ERROR_AUTH_GOOGLE_CODE_VALI(110),
    /** 授权相关错误: 手机号或密码错误 */
    ERROR_AUTH_PHONE_PWD(111),
    /** 授权相关错误: openId错误 */
    ERROR_AUTH_OPENID(112),

    /** 授权相关错误: 获取游客失败 */
    ERROR_AUTH_GUEST_EMPTY(130),
    /** 授权相关错误: 获取用户信息失败 */
    ERROR_AUTH_USER_EMPTY(150),

    /** 安全认证失败 */
    ERROR_AUTH_SECURITY(120);

    private Integer code;
    CodeErrorAuthEnum(Integer code){
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
