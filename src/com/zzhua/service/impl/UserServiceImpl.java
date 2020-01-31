package com.zzhua.service.impl;

import com.zzhua.domain.User;
import com.zzhua.mapper.UserMapper;
import com.zzhua.service.UserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright (C), 2019, 深圳太极云软技术有限公司
 * Author：   zzhua
 * Created by Administrator on 2020/1/29
 * <p>
 * Description:
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User loginUser(String username) {
        return userMapper.loginUser(username);
    }

    @Override
    public void addUser(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        Md5Hash hashedPwd = new Md5Hash(password, ByteSource.Util.bytes((username + "abcd").getBytes()), 2);
        user.setPassword(hashedPwd.toString());
        userMapper.addUser(user);
    }
}
