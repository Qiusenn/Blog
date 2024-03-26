package com.qiusen.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qiusen.domain.dto.AdminRoleChangeStatusDto;
import com.qiusen.domain.dto.AdminRoleDetailDto;
import com.qiusen.domain.dto.AdminRoleDto;
import com.qiusen.domain.dto.RoleDto;
import com.qiusen.domain.entity.Role;
import com.qiusen.domain.vo.*;

import java.util.List;

/**
 * 角色信息表(Role)表服务接口
 *
 * @author qiusen
 * @since 2024-03-24 16:32:49
 */
public interface RoleService extends IService<Role> {
    List<String> selectRoleKeyByUserId(Long id);

    PageVo queryList(Integer pageNum, Integer pageSize, RoleDto roleDto);

    void putStatusByRoleId(AdminRoleChangeStatusDto adminRoleChangeStatusDto);

    void addRole(AdminRoleDto adminRoleDto);

    AdminRoleDetailVo getRoleById(Integer id);

    void updateRole(AdminRoleDetailDto adminRoleDetailDto);
}
