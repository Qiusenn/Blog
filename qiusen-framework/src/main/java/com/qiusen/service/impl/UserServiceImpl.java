package com.qiusen.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiusen.domain.entity.User;
import com.qiusen.mapper.UserMapper;
import com.qiusen.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
