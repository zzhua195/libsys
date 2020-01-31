package com.zzhua.service.impl;

import com.zzhua.domain.Perm;
import com.zzhua.mapper.PermMapper;
import com.zzhua.service.PermService;
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
public class PermServiceImpl implements PermService {

    @Autowired
    private PermMapper permMapper;

    @Override
    public List<Perm> selectPermsByUid(Integer uid) {
        return permMapper.selectPermsByUid(uid);
    }
}
