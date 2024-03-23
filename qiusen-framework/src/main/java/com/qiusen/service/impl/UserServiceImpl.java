package com.qiusen.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiusen.domain.entity.User;
import com.qiusen.domain.vo.UserInfoVo;
import com.qiusen.enums.ResponseResult;
import com.qiusen.mapper.UserMapper;
import com.qiusen.service.UserService;
import com.qiusen.utils.BeanCopyUtils;
import com.qiusen.utils.SecurityUtils;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public ResponseResult userInfo() {
        //获取当前用户id
        Long userId = SecurityUtils.getUserId();
        //根据用户id查询用户信息
        User user = getById(userId);
        //封装成UserInfoVo
        UserInfoVo vo = BeanCopyUtils.copyBean(user,UserInfoVo.class);
        return ResponseResult.okResult(vo);
    }

    @Override
    public ResponseResult updateUserInfo(User user) {
        updateById(user);
        return ResponseResult.okResult();
    }
}
