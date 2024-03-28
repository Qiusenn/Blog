package com.qiusen.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qiusen.domain.entity.UserRole;

import java.util.List;

/**
 * 用户和角色关联表(UserRole)表服务接口
 *
 * @author qiusen
 * @since 2024-03-26 20:54:17
 */
public interface UserRoleService extends IService<UserRole> {
    void add(Long id, List<String> roleIds);

    List<UserRole> getUserRolesByUserId(Long id);


    void delBranchByRoleId(Long id, List<String> roleIds);
}
