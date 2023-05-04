package com.central.oauth.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.central.common.constant.CommonConstant;
import com.central.common.constant.SecurityConstants;
import com.central.common.model.LoginAppUser;
import com.central.common.model.Result;
import com.central.common.model.SysUser;
import com.central.common.model.enums.UserTypeEnum;
import com.central.common.redis.template.RedisRepository;
import com.central.common.utils.GoogleAuthUtil;
import com.central.oauth.exception.CustomOAuth2Exception;
import com.central.oauth.modle.CodeErrorAuthEnum;
import com.central.oauth.service.IValidateCodeService;
import com.central.user.feign.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zlt
 * @date 2018/12/10
 * <p>
 * Blog: https://zlt2000.gitee.io
 * Github: https://github.com/zlt2000
 */
@Slf4j
@Service
public class ValidateCodeServiceImpl implements IValidateCodeService {

    @Resource
    private UserService userService;

    /**
     * 保存用户验证码，和randomStr绑定
     *
     * @param deviceId  客户端生成
     * @param imageCode 验证码信息
     */
    @Override
    public void saveImageCode(String deviceId, String imageCode) {
        RedisRepository.setExpire(buildKey(deviceId), imageCode, SecurityConstants.DEFAULT_IMAGE_EXPIRE);
    }

    /**
     * 发送验证码
     * <p>
     * 1. 先去redis 查询是否 60S内已经发送
     * 2. 未发送： 判断手机号是否存 ? false :产生4位数字  手机号-验证码
     * 3. 发往消息中心-》发送信息
     * 4. 保存redis
     *
     * @param mobile 手机号
     * @return true、false
     */
    @Override
    public Result sendSmsCode(String mobile) {
        Object tempCode = RedisRepository.get(buildKey(mobile));
        if (tempCode != null) {
            log.error("用户:{}验证码未失效{}", mobile, tempCode);
            return Result.failed("验证码未失效，请失效后再次申请");
        }

        SysUser user = userService.findByMobile(mobile);
        if (user == null) {
            log.error("根据用户手机号{}查询用户为空", mobile);
            return Result.failed("手机号不存在");
        }

        String code = RandomUtil.randomNumbers(4);
        log.info("短信发送请求消息中心 -> 手机号:{} -> 验证码：{}", mobile, code);
        RedisRepository.setExpire(buildKey(mobile), code, SecurityConstants.DEFAULT_IMAGE_EXPIRE);
        return Result.succeed("true");
    }

    /**
     * 获取验证码
     *
     * @param deviceId 前端唯一标识/手机号
     */
    @Override
    public String getCode(String deviceId) {
        return (String) RedisRepository.get(buildKey(deviceId));
    }

    /**
     * 删除验证码
     *
     * @param deviceId 前端唯一标识/手机号
     */
    @Override
    public void remove(String deviceId) {
        RedisRepository.delete(buildKey(deviceId));
    }

    /**
     * 验证验证码
     */
    @Override
    public void validate(String deviceId, String validCode) {
        if (StrUtil.isBlank(deviceId)) {
            throw new CustomOAuth2Exception(CodeErrorAuthEnum.ERROR_AUTH_DEVICE_ID.getCode(), "请在请求参数中携带deviceId参数");
        }
        String code = this.getCode(deviceId);
        if (StrUtil.isBlank(validCode)) {
            throw new CustomOAuth2Exception(CodeErrorAuthEnum.ERROR_AUTH_EMPTY_VALIDATE_CODE.getCode(), "请填写验证码");
        }

        if (code == null) {
            throw new CustomOAuth2Exception(CodeErrorAuthEnum.ERROR_AUTH_VALIDATE_CODE.getCode(), "验证码不存在或已过期");
        }

        if (!StrUtil.equals(code, validCode.toLowerCase())) {
            throw new CustomOAuth2Exception(CodeErrorAuthEnum.ERROR_AUTH_VALIDATE_CODE_ERR.getCode(), "验证码不正确");
        }

        this.remove(deviceId);
    }

    @Override
    public void validateGoogleCode(String googleCode, String username) {

//        try {
        if (StrUtil.isBlank(username)) {
            throw new CustomOAuth2Exception(CodeErrorAuthEnum.ERROR_AUTH_NO_PARAMS_USERNAME.getCode(), "请在请求参数中携带username参数");
        }
        LoginAppUser loginAppUser = userService.findByUsername(username);
        // TODO 通过类型来判断用户的处理方式是错误的
        if (loginAppUser == null) {
            throw new CustomOAuth2Exception(CodeErrorAuthEnum.ERROR_AUTH_USERNAME_PASSWORD.getCode(), "用户名或密码错误");
        }
        if (!loginAppUser.getType().equals(CommonConstant.USER_TYPE_BACKEND) ) {
            throw new CustomOAuth2Exception(CodeErrorAuthEnum.ERROR_AUTH_USERNAME_PASSWORD.getCode(), "用户名或密码错误");
        }

        if (loginAppUser.getVerify() != null && loginAppUser.getVerify() == 1) {

            if (StrUtil.isBlank(googleCode)) {
                throw new CustomOAuth2Exception(CodeErrorAuthEnum.ERROR_AUTH_GOOGLE_CODE.getCode(), "请在请求参数中携带googleCode参数");
            }

            Integer code = Integer.parseInt(googleCode);
            String secret = loginAppUser.getGaKey();

            // TODO 定义全局常量
            if (StrUtil.isBlank(secret) || loginAppUser.getGaBind() == null || loginAppUser.getGaBind() != 1) {
                throw new CustomOAuth2Exception(CodeErrorAuthEnum.ERROR_AUTH_GOOGLE_CODE_BIND.getCode(), "请先绑定谷歌身份验证器");
            }
            boolean checkCode = GoogleAuthUtil.check_code(secret, code);
            if (!checkCode) {
                throw new CustomOAuth2Exception(CodeErrorAuthEnum.ERROR_AUTH_GOOGLE_CODE_VALI.getCode(), "谷歌身份验证失败");
            }
        }
//        }catch (Exception ex){
//            throw new CustomOAuth2Exception(CodeErrorAuthEnum.ERROR_AUTH_GOOGLE_CODE_VALI.getCode(), "谷歌身份验证失败");
//        }
    }

    @Override
    public void validateAppUserType(String username) {
        if (StrUtil.isBlank(username)) {
            throw new CustomOAuth2Exception(CodeErrorAuthEnum.ERROR_AUTH_NO_PARAMS_USERNAME.getCode(), "请在请求参数中携带username参数");
        }
        LoginAppUser loginAppUser = userService.findByUsername(username);
        // TODO 通过类型来判断用户的处理方式是错误的
        if (loginAppUser == null || !UserTypeEnum.APP.name().equals(loginAppUser.getType())) {
            throw new CustomOAuth2Exception(CodeErrorAuthEnum.ERROR_AUTH_USERNAME_PASSWORD.getCode(), "用户名或密码错误");
        }
    }

    private String buildKey(String deviceId) {
        return SecurityConstants.DEFAULT_CODE_KEY + ":" + deviceId;
    }
}
