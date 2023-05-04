package com.central.oauth.service;

import com.central.common.model.PageResult;
import com.central.common.model.SysUser;
import com.central.oauth.model.TokenVo;

import java.util.Map;

/**
 * @author zlt
 */
public interface ITokensService {
    /**
     * 查询token列表
     * @param params 请求参数
     * @param clientId 应用id
     */
    PageResult<TokenVo> listTokens(Map<String, Object> params, String clientId);


    Integer playerNums(String clientId);

    /**
     * 查看是否存在用户的key
     */
    Boolean exist(String username,String clientId);

    SysUser getUserInfoByToken(String token);
}
