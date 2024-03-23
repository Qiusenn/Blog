package com.qiusen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qiusen.domain.entity.User;
import com.qiusen.enums.ResponseResult;

public interface UserService extends IService<User> {
    ResponseResult userInfo();

    ResponseResult updateUserInfo(User user);
}
