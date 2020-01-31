package com.zzhua.service;

import com.zzhua.domain.Role;

import java.util.List;

/**
 * Copyright (C), 2019, 深圳太极云软技术有限公司
 * Author：   zzhua
 * Created by Administrator on 2020/1/30
 * <p>
 * Description:
 */


public interface RoleService {

    List<Role> selectRolesByUid(Integer uid);
}
