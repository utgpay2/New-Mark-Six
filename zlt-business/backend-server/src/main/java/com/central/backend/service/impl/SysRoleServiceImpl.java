package com.central.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.backend.mapper.SysRoleMapper;
import com.central.backend.mapper.SysRoleMenuMapper;
import com.central.backend.mapper.SysUserRoleMapper;
import com.central.backend.service.ISysRoleService;
import com.central.backend.vo.SysRoleVo;
import com.central.common.lock.DistributedLock;
import com.central.common.model.PageResult;
import com.central.common.model.Result;
import com.central.common.model.SysRole;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.backend.co.RolePageCo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 作者 owen E-mail: 624191343@qq.com
 */
@Slf4j
@Service
public class SysRoleServiceImpl extends SuperServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {
    private final static String LOCK_KEY_ROLECODE = "rolecode:";

    @Resource
    private SysUserRoleMapper userRoleMapper;

    @Resource
    private SysRoleMenuMapper roleMenuMapper;

    @Autowired
    private DistributedLock lock;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveRole(SysRole sysRole) throws Exception {
        String roleCode = sysRole.getCode();
        super.saveIdempotency(sysRole, lock
                , LOCK_KEY_ROLECODE+roleCode, new QueryWrapper<SysRole>().eq("code", roleCode), "角色code已存在");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteRole(Long id) {
        baseMapper.deleteById(id);
        roleMenuMapper.delete(id, null);
        userRoleMapper.deleteUserRole(null, id);
    }

    @Override
    public PageResult<SysRoleVo> findRoles(RolePageCo params) {
        Integer curPage = params.getPage();
        Integer limit = params.getLimit();
        Page<SysRoleVo> page = new Page<>(curPage == null ? 0 : curPage, limit == null ? -1 : limit);
        List<SysRoleVo> list = baseMapper.findList(page, params);
        return PageResult.<SysRoleVo>builder().data(list).count(page.getTotal()).build();
    }

    @Override
    @Transactional
    public Result saveOrUpdateRole(SysRole sysRole) throws Exception {
        if (sysRole.getId() == null) {
            this.saveRole(sysRole);
        } else {
            baseMapper.updateById(sysRole);
        }
        return Result.succeed("操作成功");
    }

    @Override
    public List<SysRole> findAll() {
        return baseMapper.findAll();
    }

    @Override
    public SysRole findSysRoleByName(String name){
        LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(name)) {
            wrapper.eq(SysRole::getName, name);
        }
        return baseMapper.selectOne(wrapper);
    }

    @Override
    public SysRole findSysRoleByCode(String code){
        LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(code)) {
            wrapper.eq(SysRole::getCode, code);
        }
        return baseMapper.selectOne(wrapper);
    }
}
