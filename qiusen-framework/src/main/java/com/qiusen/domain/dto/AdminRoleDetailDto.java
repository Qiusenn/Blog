package com.qiusen.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AdminRoleDetailDto {
    /* 角色ID */
    private Long id;

    /* 角色名 */
    private String roleName;

    /* 角色权限字符串 */
    private String roleKey;

    /* 显示顺序 */
    private Integer roleSort;

    /* 角色状态（0正常 1停用） */
    private String status;

    /* 菜单ids */
    private List<String> menuIds;

    /* 备注 */
    private String remark;

}
