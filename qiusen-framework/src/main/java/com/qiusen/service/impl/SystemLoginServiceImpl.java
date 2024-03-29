package com.qiusen.service.impl;

import com.qiusen.domain.entity.User;
import com.qiusen.domain.vo.BlogUserLoginVo;
import com.qiusen.domain.vo.LoginUser;
import com.qiusen.domain.vo.UserInfoVo;
import com.qiusen.enums.ResponseResult;
import com.qiusen.service.BlogLoginService;
import com.qiusen.service.LoginService;
import com.qiusen.utils.BeanCopyUtils;
import com.qiusen.utils.JwtUtil;
import com.qiusen.utils.RedisCache;
import com.qiusen.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class SystemLoginServiceImpl implements LoginService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;
    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //判断是否认证通过
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("用户名或密码错误");
        }
        //获取userid 生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId ().toString();
        String jwt = JwtUtil.createJWT(userId);
        //把用户信息存入redis
        redisCache.setCacheObject("login:"+userId,loginUser);
        //把token封装 返回
        Map<String,String> map = new HashMap<>();
        map.put("token",jwt);
        return ResponseResult.okResult(map);
    }

    @Override
    public ResponseResult logout() {
        //获取当前登录的用户id
        Long userId = SecurityUtils.getUserId();
        //删除redis中对应的值
        redisCache.deleteObject("login:"+userId);
        return ResponseResult.okResult();
    }

//    @Override
//    public ResponseResult logout() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
//        //获取userid
//        Long userId = loginUser.getUser().getId();
//        //删除redis中的用户信息
//        redisCache.deleteObject("bloglogin:"+userId);
//        return ResponseResult.okResult();
//    }
}

