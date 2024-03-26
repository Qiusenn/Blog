package com.qiusen.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AdminRoleMenuTreeVo {

    /* 所有权限列表 */
    private List<AdminTreeSelectVo> menus;

    /* 用户已分配列表 */
    private List<String> checkedKeys;
}
