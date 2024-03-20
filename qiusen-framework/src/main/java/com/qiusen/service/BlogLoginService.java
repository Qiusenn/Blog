package com.qiusen.service;

import com.qiusen.domain.entity.User;
import com.qiusen.enums.ResponseResult;

public interface BlogLoginService {
    ResponseResult login(User userDto);

    ResponseResult logout();
}
