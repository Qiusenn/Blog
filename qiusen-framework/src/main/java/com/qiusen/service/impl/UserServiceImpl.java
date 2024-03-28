package com.qiusen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiusen.domain.dto.AdminUserAddDto;
import com.qiusen.domain.dto.AdminUserDto;
import com.qiusen.domain.dto.AdminUserUpdateDto;
import com.qiusen.domain.entity.Role;
import com.qiusen.domain.entity.Tag;
import com.qiusen.domain.entity.User;
import com.qiusen.domain.entity.UserRole;
import com.qiusen.domain.vo.*;
import com.qiusen.enums.AppHttpCodeEnum;
import com.qiusen.enums.ResponseResult;
import com.qiusen.exception.SystemException;
import com.qiusen.mapper.UserMapper;
import com.qiusen.service.RoleService;
import com.qiusen.service.UserRoleService;
import com.qiusen.service.UserService;
import com.qiusen.utils.BeanCopyUtils;
import com.qiusen.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleService roleService;

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

    @Override
    public ResponseResult register(User user) {
        //对数据进行非空判断
        if(!StringUtils.hasText(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.USERNAME_NOT_NULL);
        }
        if(!StringUtils.hasText(user.getPassword())){
            throw new SystemException(AppHttpCodeEnum.PASSWORD_NOT_NULL);
        }
        if(!StringUtils.hasText(user.getEmail())){
            throw new SystemException(AppHttpCodeEnum.EMAIL_NOT_NULL);
        }
        if(!StringUtils.hasText(user.getNickName())){
            throw new SystemException(AppHttpCodeEnum.NICKNAME_NOT_NULL);
        }
        //对数据进行是否存在的判断
        if(userNameExist(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        }
        if(nickNameExist(user.getNickName())){
            throw new SystemException(AppHttpCodeEnum.NICKNAME_EXIST);
        }
        //对密码进行加密
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        //存入数据库
        save(user);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, AdminUserDto adminUserDto) {
        //分页查询
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.hasText(adminUserDto.getUserName()),User::getUserName,adminUserDto.getUserName());
        queryWrapper.eq(StringUtils.hasText(adminUserDto.getPhonenumber()),User::getPhonenumber,adminUserDto.getPhonenumber());
        queryWrapper.eq(StringUtils.hasText(adminUserDto.getStatus()),User::getStatus,adminUserDto.getStatus());
        Page<User> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page, queryWrapper);
        //封装数据返回
        PageVo pageVo = new PageVo(BeanCopyUtils.copyBeanList(page.getRecords(), AdminUserVo.class),page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    /**
     * 添加用户数据 并根据id更新用户角色id
     * @param adminUserAddDto
     */
    @Override
    @Transactional
    public void add(AdminUserAddDto adminUserAddDto) {
        save(BeanCopyUtils.copyBean(adminUserAddDto, User.class));
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, adminUserAddDto.getUserName());
        User user = getOne(queryWrapper);
        userRoleService.add(user.getId(), adminUserAddDto.getRoleIds());
    }

    @Override
    public AdminUserDetailVo getUserById(Long id) {
        AdminUserDetailVo adminUserDetailVo = new AdminUserDetailVo();
        List<UserRole> userRoleList = userRoleService.getUserRolesByUserId(id);

        List<String> roleIds = userRoleList.stream().map(item -> item.getRoleId().toString()).collect(Collectors.toList());
        AdminUserDetailInfo user = BeanCopyUtils.copyBean(getById(id), AdminUserDetailInfo.class);
        List<Role> list = roleService.list();
        adminUserDetailVo.setRoles(list);

        adminUserDetailVo.setRoleIds(roleIds);
        adminUserDetailVo.setUser(user);
        return adminUserDetailVo;
    }

    @Override
    @Transactional
    public void put(AdminUserUpdateDto adminUserUpdateDto) {
        User user = BeanCopyUtils.copyBean(adminUserUpdateDto, User.class);
        updateById(user);
        // 删除用户角色对应的数据, 重新插入用户角色对于数据
        userRoleService.delBranchByRoleId(adminUserUpdateDto.getId(), adminUserUpdateDto.getRoleIds());
    }

    private boolean nickNameExist(String nickName) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getNickName, nickName);
        return count(queryWrapper) > 0;
    }

    private boolean userNameExist(String userName) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, userName);
        return count(queryWrapper) > 0;
    }
}
