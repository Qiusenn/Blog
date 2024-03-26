package com.qiusen.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qiusen.entity.RoleMenu;

import java.util.List;

/**
 * 角色和菜单关联表(RoleMenu)表服务接口
 *
 * @author qiusen
 * @since 2024-03-26 15:56:41
 */
public interface RoleMenuService extends IService<RoleMenu> {
    /**
     * 根据角色id获取对应的表数据
     * @param id
     * @return
     */
    List<RoleMenu> getMenusByRoleId(Integer id);

    void removeByRoleId(Long id);
}
