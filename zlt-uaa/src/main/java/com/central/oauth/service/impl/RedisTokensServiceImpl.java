package com.central.oauth.service.impl;

import cn.hutool.core.util.PageUtil;
import cn.hutool.core.util.StrUtil;
import com.central.common.constant.SecurityConstants;
import com.central.common.model.PageResult;
import com.central.common.model.SysUser;
import com.central.common.redis.template.RedisRepository;
import com.central.oauth.model.TokenVo;
import com.central.oauth.service.ITokensService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * token管理服务(redis token)
 *
 * @author zlt
 * @date 2019/7/12
 * <p>
 * Blog: https://zlt2000.gitee.io
 * Github: https://github.com/zlt2000
 */
@Slf4j
@Service
public class RedisTokensServiceImpl implements ITokensService {
    @Autowired
    private RedisRepository redisRepository;
    @Autowired
    private TokenStore tokenStore;

    @Override
    public PageResult<TokenVo> listTokens(Map<String, Object> params, String clientId) {
        Integer page = MapUtils.getInteger(params, "page");
        Integer limit = MapUtils.getInteger(params, "limit");
        int[] startEnds = PageUtil.transToStartEnd(page, limit);
        //根据请求参数生成redis的key
        String redisKey = getRedisKey(params, clientId);
        long size = redisRepository.length(redisKey);
        List<TokenVo> result = new ArrayList<>(limit);
        RedisSerializer<Object> valueSerializer = RedisSerializer.java();
        //查询token集合
        List<Object> tokenObjs = redisRepository.getList(redisKey, startEnds[0], startEnds[1]-1, valueSerializer);
        if (tokenObjs != null) {
            for (Object obj : tokenObjs) {
                DefaultOAuth2AccessToken accessToken = (DefaultOAuth2AccessToken)obj;
                //构造token对象
                TokenVo tokenVo = new TokenVo();
                tokenVo.setTokenValue(accessToken.getValue());
                tokenVo.setExpiration(accessToken.getExpiration());

                //获取用户信息
                Object authObj = redisRepository.get(SecurityConstants.REDIS_TOKEN_AUTH + accessToken.getValue(), valueSerializer);
                OAuth2Authentication authentication = (OAuth2Authentication)authObj;
                if (authentication != null) {
                    OAuth2Request request = authentication.getOAuth2Request();
                    tokenVo.setUsername(authentication.getName());
                    tokenVo.setClientId(request.getClientId());
                    tokenVo.setGrantType(request.getGrantType());
                }

                Map<String, Object> additionalInformation = accessToken.getAdditionalInformation();
                String accountType = (String)additionalInformation.get(SecurityConstants.ACCOUNT_TYPE_PARAM_NAME);
                tokenVo.setAccountType(accountType);

                result.add(tokenVo);
            }
        }
        return PageResult.<TokenVo>builder().data(result).count(size).build();
    }

    @Override
    public Integer playerNums(String clientId) {
        String redisKey = SecurityConstants.REDIS_UNAME_TO_ACCESS+clientId;
        log.info("redisKey is {}",redisKey);
        Set<String> keySet = redisRepository.keys(redisKey+"*");
//        log.info("size: =++++= {}",keySet);
        return keySet.size();
    }

    /**
     * 根据请求参数生成redis的key
     */
    private String getRedisKey(Map<String, Object> params, String clientId) {
        String result;
        String username = MapUtils.getString(params, "username");
        if (StrUtil.isNotEmpty(username)) {
            result = SecurityConstants.REDIS_UNAME_TO_ACCESS + clientId + ":" + username;
        } else {
            result = SecurityConstants.REDIS_CLIENT_ID_TO_ACCESS + clientId;
        }
        return result;
    }

    public Boolean exist(String username,String clientId){
        String key = SecurityConstants.REDIS_UNAME_TO_ACCESS + clientId + ":" + username;
        return redisRepository.exists(key);
    }

    @Override
    public SysUser getUserInfoByToken(String token) {
        if (StringUtils.isBlank(token)){
            return null;
        }
        OAuth2AccessToken accessToken = tokenStore.readAccessToken(token);
        if (accessToken == null) { // 没有找到token，无效的token
            log.error("Invalid access token:{}", token);
            return null;
        }
        OAuth2Authentication authentication = tokenStore.readAuthentication(accessToken);
        if (authentication == null) { // 无效的token
            log.error("Invalid access token:{}", token);
            return null;
        }
        SysUser sysUser = (SysUser) authentication.getPrincipal();
        return sysUser;
    }
}
