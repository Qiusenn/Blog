package com.qiusen.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 角色和菜单关联表(RoleMenu)表实体类
 *
 * @author makejava
 * @since 2024-03-26 15:56:39
 */
@SuppressWarnings("serial")
@AllArgsConstructor
@Data
@NoArgsConstructor
@TableName(value = "sys_role_menu")
public class RoleMenu {

    /* 角色ID */
    private Long roleId;
    
    /* 菜单ID */
    private Long menuId;


}

