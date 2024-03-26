package com.qiusen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiusen.constants.SystemConstants;
import com.qiusen.domain.entity.Menu;
import com.qiusen.domain.vo.AdminMenuDetailVo;
import com.qiusen.domain.vo.AdminMenuListVo;
import com.qiusen.domain.vo.AdminRoleMenuTreeVo;
import com.qiusen.domain.vo.AdminTreeSelectVo;
import com.qiusen.domain.entity.RoleMenu;
import com.qiusen.enums.AppHttpCodeEnum;
import com.qiusen.enums.ResponseResult;
import com.qiusen.exception.SystemException;
import com.qiusen.mapper.MenuMapper;
import com.qiusen.service.MenuService;
import com.qiusen.service.RoleMenuService;
import com.qiusen.utils.BeanCopyUtils;
import com.qiusen.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜单权限表(Menu)表服务实现类
 *
 * @author qiusen
 * @since 2024-03-24 16:32:23
 */
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private RoleMenuService roleMenuService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private MenuMapper menuMapper;
    @Override
    public List<String> selectPermsByUserId(Long id) {
        //如果是管理员，返回所有的权限
        if (SecurityUtils.isAdmin()) {
            LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
            wrapper.in(Menu::getMenuType, SystemConstants.MENU, SystemConstants.BUTTON);
            wrapper.eq(Menu::getStatus, SystemConstants.STATUS_NORMAL);
            List<Menu> menus = list(wrapper);
            List<String> perms = menus.stream()
                    .map(Menu::getPerms)
                    .collect(Collectors.toList());
            return perms;
        }
        //否则返回所具有的权限
        return getBaseMapper().selectPermsByUserId(id);
    }

    @Override
    public List<Menu> selectRouterMenuTreeByUserId(Long userId) {
        MenuMapper menuMapper = getBaseMapper();
        List<Menu> menus = null;
        //判断是否是管理员
        if (SecurityUtils.isAdmin()) {
            //如果是 获取所有符合要求的Menu
            menus = menuMapper.selectAllRouterMenu();
        } else {
            //否则 获取当前用户所具有的Menu
            menus = menuMapper.selectRouterMenuTreeByUserId(userId);
        }
        //构建tree
        //先找出第一层的菜单 然后去找他们的子菜单设置到children属性中
        return builderMenuTree(menus, 0L);
    }

    @Override
    public List<AdminMenuListVo> getAllList(String status, String menuName) {
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.hasText(status), Menu::getStatus, status);
        queryWrapper.eq(StringUtils.hasText(menuName), Menu::getMenuName, menuName);
        return BeanCopyUtils.copyBeanList(list(queryWrapper), AdminMenuListVo.class);
    }

    @Override
    public AdminMenuDetailVo getMenuDetailById(Integer id) {
        return BeanCopyUtils.copyBean(getById(id), AdminMenuDetailVo.class);
    }

    @Override
    public ResponseResult put(Menu menu) {
        if(menu.getId().equals(menu.getParentId())){
            throw new SystemException(AppHttpCodeEnum.MENU_PARENT_ID_ERR);
        }
        updateById(menu);
        return ResponseResult.okResult();
    }


    @Override
    public List<AdminTreeSelectVo> treeselect() {
        List<Menu> menus = getBaseMapper().selectAllRouterMenu();
        //构建tree
        //先找出第一层的菜单 然后去找他们的子菜单设置到children属性中
        List<AdminTreeSelectVo> adminTreeSelectVos = new ArrayList<>();
        for (Menu menu : menus) {
            AdminTreeSelectVo adminTreeSelectVo = new AdminTreeSelectVo();
            adminTreeSelectVo.setId(menu.getId());
            adminTreeSelectVo.setParentId(menu.getParentId());
            adminTreeSelectVo.setLabel(menu.getMenuName());
            adminTreeSelectVos.add(adminTreeSelectVo);
        }

        return builderAdminMenuTree(adminTreeSelectVos, 0L);
    }

    private List<AdminTreeSelectVo> builderAdminMenuTree(List<AdminTreeSelectVo> menus, Long parentId) {
        return menus.stream()
                .filter(menu -> menu.getParentId().equals(parentId))
                .map(menu -> menu.setChildren(getChildren(menu, menus)))
                .collect(Collectors.toList());
    }

    private List<Menu> builderMenuTree(List<Menu> menus, Long parentId) {
        return menus.stream()
                .filter(menu -> menu.getParentId().equals(parentId))
                .map(menu -> menu.setChildren(getChildren(menu, menus)))
                .collect(Collectors.toList());
    }

    private List<AdminTreeSelectVo> getChildren(AdminTreeSelectVo menu, List<AdminTreeSelectVo> menus) {
        return menus.stream()
                .filter(m -> m.getParentId().equals(menu.getId()))
                .map(m->m.setChildren(getChildren(m, menus)))
                .collect(Collectors.toList());
    }

    /**
     * 获取存入参数的 子Menu集合
     * @param menu
     * @param menus
     * @return
     */
    private List<Menu> getChildren(Menu menu, List<Menu> menus) {
        return menus.stream()
                .filter(m -> m.getParentId().equals(menu.getId()))
                .map(m->m.setChildren(getChildren(m,menus)))
                .collect(Collectors.toList());
    }

    @Override
    public AdminRoleMenuTreeVo getRoleMenuTreeSelect(Integer id) {
        List<AdminTreeSelectVo> treeSelect = menuService.treeselect();
        List<String> checkedKeys;

        //判断是否是管理员
        if (id.toString().equals(SystemConstants.ADMAIN)) {
            //如果是 获取所有符合要求的Menu
            List<Menu> menus = menuMapper.selectAllRouterMenu();
            checkedKeys = menus.stream().map(item -> item.getId().toString()).collect(Collectors.toList());
        }else {
            List<RoleMenu> roleMenus;
            roleMenus = roleMenuService.getMenusByRoleId(id);
            checkedKeys = roleMenus.stream().map(item -> item.getMenuId().toString()).collect(Collectors.toList());
        }
        return new AdminRoleMenuTreeVo(treeSelect, checkedKeys);
    }
}
