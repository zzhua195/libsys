package com.zzhua.service;

import com.zzhua.domain.User;

/**
 * Copyright (C), 2019, 深圳太极云软技术有限公司
 * Author：   zzhua
 * Created by Administrator on 2020/1/29
 * <p>
 * Description:
 */


public interface UserService {
    User loginUser(String username);

    void addUser(User user);
}
