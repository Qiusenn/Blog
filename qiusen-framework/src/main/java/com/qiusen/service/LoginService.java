package com.qiusen.service;

import com.qiusen.domain.entity.User;
import com.qiusen.enums.ResponseResult;

public interface LoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
