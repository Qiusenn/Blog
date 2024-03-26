package com.qiusen.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiusen.domain.dto.AdminRoleChangeStatusDto;
import com.qiusen.domain.dto.AdminRoleDetailDto;
import com.qiusen.domain.dto.AdminRoleDto;
import com.qiusen.domain.dto.RoleDto;
import com.qiusen.domain.entity.Role;
import com.qiusen.domain.vo.*;
import com.qiusen.entity.RoleMenu;
import com.qiusen.mapper.MenuMapper;
import com.qiusen.mapper.RoleMapper;
import com.qiusen.service.MenuService;
import com.qiusen.service.RoleMenuService;
import com.qiusen.service.RoleService;
import com.qiusen.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色信息表(Role)表服务实现类
 *
 * @author qiusen
 * @since 2024-03-24 16:32:49
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMenuService roleMenuService;


    @Override
    public List<String> selectRoleKeyByUserId(Long id) {
        //判断是否是管理员 如果是返回集合中只需要有admin
        if(id == 1L){
            List<String> roleKeys = new ArrayList<>();
            roleKeys.add("admin");
            return roleKeys;
        }
        //否则查询用户所具有的角色信息
        return getBaseMapper().selectRoleKeyByUserId(id);
    }

    @Override
    public PageVo queryList(Integer pageNum, Integer pageSize, RoleDto roleDto) {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(roleDto.getRoleName()), Role::getRoleName, roleDto.getRoleName());
        queryWrapper.like(StringUtils.hasText(roleDto.getStatus()), Role::getStatus, roleDto.getStatus());
        Page<Role> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        Page<Role> rolePage = page(page, queryWrapper);
        return new PageVo(BeanCopyUtils.copyBeanList(rolePage.getRecords(), AdminRoleListVo.class), page.getTotal());

    }

    @Override
    public void putStatusByRoleId(AdminRoleChangeStatusDto adminRoleChangeStatusDto) {
        Role role = new Role();
        role.setId(Long.valueOf(adminRoleChangeStatusDto.getRoleId()));
        role.setStatus(adminRoleChangeStatusDto.getStatus());
        updateById(role);
    }

    @Override
    @Transactional
    public void addRole(AdminRoleDto adminRoleDto) {
        Role role = BeanCopyUtils.copyBean(adminRoleDto, Role.class);
        boolean isSave = save(role);
        if (isSave) {
            LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Role::getRoleName, adminRoleDto.getRoleName());
            Long id = getOne(queryWrapper).getId();
            List<String> menuIds = adminRoleDto.getMenuIds();
            List<RoleMenu> roleMenuList = menuIds.stream().map(menuId -> new RoleMenu(id, Long.valueOf(menuId))).collect(Collectors.toList());
            roleMenuService.saveBatch(roleMenuList);
        }
    }

    @Override
    public AdminRoleDetailVo getRoleById(Integer id) {
        return BeanCopyUtils.copyBean(getById(id), AdminRoleDetailVo.class);
    }

    @Override
    @Transactional
    public void updateRole(AdminRoleDetailDto adminRoleDetailDto) {
        updateById(BeanCopyUtils.copyBean(adminRoleDetailDto, Role.class));
        roleMenuService.removeByRoleId(adminRoleDetailDto.getId());
        List<RoleMenu> list = adminRoleDetailDto.getMenuIds().stream().map(id -> new RoleMenu(adminRoleDetailDto.getId(), Long.valueOf(id))).collect(Collectors.toList());
        roleMenuService.saveBatch(list);

    }

}
