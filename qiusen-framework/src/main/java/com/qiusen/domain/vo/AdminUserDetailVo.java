package com.qiusen.domain.vo;

import com.qiusen.domain.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AdminUserDetailVo {
    private List<String> roleIds;
    private List<Role> roles;
    private AdminUserDetailInfo user;
}
