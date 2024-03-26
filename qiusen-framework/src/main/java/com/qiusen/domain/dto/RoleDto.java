package com.qiusen.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class RoleDto {

    /* 角色名称 */
    private String roleName;

    /* 角色状态（0正常 1停用） */
    private String status;
}
