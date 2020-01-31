package com.zzhua.service.impl;

import com.zzhua.domain.Role;
import com.zzhua.mapper.RoleMapper;
import com.zzhua.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Copyright (C), 2019, 深圳太极云软技术有限公司
 * Author：   zzhua
 * Created by Administrator on 2020/1/30
 * <p>
 * Description:
 */

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> selectRolesByUid(Integer uid) {
        return roleMapper.selectRolesByUid(uid);
    }
}
