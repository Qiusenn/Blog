package com.qiusen.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiusen.domain.entity.User;
import com.qiusen.domain.entity.UserRole;
import com.qiusen.mapper.UserRoleMapper;
import com.qiusen.service.UserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户和角色关联表(UserRole)表服务实现类
 *
 * @author qiusen
 * @since 2024-03-26 20:54:17
 */
@Service("userRoleService")
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
    @Override
    public void add(Long id, List<String> roleIds) {
        List<UserRole> collect = roleIds.stream().map(item -> new UserRole(id, Long.valueOf(item))).collect(Collectors.toList());
        saveBatch(collect);
    }

    @Override
    public List<UserRole> getUserRolesByUserId(Long id) {
        LambdaQueryWrapper<UserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserRole::getUserId, id);
        return list(queryWrapper);
    }

    @Override
    @Transactional
    public void delBranchByRoleId(Long id, List<String> roleIds) {
        LambdaQueryWrapper<UserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserRole::getUserId, id);
        remove(queryWrapper);
        List<UserRole> collect = roleIds.stream().map(item -> new UserRole(id, Long.valueOf(item))).collect(Collectors.toList());
        saveBatch(collect);

    }
}