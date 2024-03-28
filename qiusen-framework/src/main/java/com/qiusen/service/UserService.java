package com.qiusen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qiusen.domain.dto.AdminUserAddDto;
import com.qiusen.domain.dto.AdminUserDto;
import com.qiusen.domain.dto.AdminUserUpdateDto;
import com.qiusen.domain.entity.User;
import com.qiusen.domain.vo.AdminUserDetailVo;
import com.qiusen.domain.vo.PageVo;
import com.qiusen.enums.ResponseResult;

public interface UserService extends IService<User> {
    ResponseResult userInfo();

    ResponseResult updateUserInfo(User user);

    ResponseResult register(User user);

    ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, AdminUserDto adminUserDto);

    void add(AdminUserAddDto adminUserAddDto);

    AdminUserDetailVo getUserById(Long id);

    void put(AdminUserUpdateDto adminUserUpdateDto);
}
