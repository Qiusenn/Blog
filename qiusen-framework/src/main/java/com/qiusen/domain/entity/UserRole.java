package com.qiusen.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户和角色关联表(UserRole)表实体类
 *
 * @author makejava
 * @since 2024-03-26 20:54:15
 */
@SuppressWarnings("serial")
@AllArgsConstructor
@Data
@NoArgsConstructor
@TableName("sys_user_role")
public class UserRole {
    
    /* 用户ID */
    private Long userId;
    
    /* 角色ID */
    private Long roleId;


}

