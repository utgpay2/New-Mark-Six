package com.central.backend.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.central.backend.mapper.StatementMapper;
import com.central.backend.service.IStatementService;
import com.central.common.constant.RedisConstants;
import com.central.common.model.Result;
import com.central.common.model.Statement;
import com.central.common.model.SysUser;
import com.central.common.redis.template.RedisRepository;
import com.central.common.service.impl.SuperServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;


@Slf4j
@Service
public class StatementServiceIpml extends SuperServiceImpl<StatementMapper, Statement> implements IStatementService {

    @Override
    public List<Statement> findList() {

        return baseMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public Result saveOrUpdateQuiz(Statement statement, SysUser user) {
        if (null != statement.getId() && 0 != statement.getId()) {
            statement.setUpdateTime(new Date());
            statement.setUpdateBy(null != user ? user.getUsername() : statement.getUpdateBy());
        } else {
            statement.setCreateBy(null != user ? user.getUsername() : statement.getCreateBy());
            statement.setCreateTime(new Date());
            statement.setUpdateTime(new Date());
            statement.setUpdateBy(null != user ? user.getUsername() : statement.getCreateBy());
        }
        this.saveOrUpdate(statement);
        return Result.succeed("保存成功");
    }




}
