package com.qiusen.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiusen.entity.RoleMenu;
import com.qiusen.mapper.RoleMenuMapper;
import com.qiusen.service.RoleMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色和菜单关联表(RoleMenu)表服务实现类
 *
 * @author qiusen
 * @since 2024-03-26 15:56:45
 */
@Service("roleMenuService")
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {
    @Override
    public List<RoleMenu> getMenusByRoleId(Integer id) {
        LambdaQueryWrapper<RoleMenu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RoleMenu::getRoleId, id);
        return list(queryWrapper);
    }

    @Override
    public void removeByRoleId(Long id) {
        LambdaQueryWrapper<RoleMenu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RoleMenu::getRoleId, id);
        remove(queryWrapper);
    }
}
