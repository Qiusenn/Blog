package com.qiusen.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
/**
 * 用户和角色关联表(UserRole)表数据库访问层
 *
 * @author makejava
 * @since 2024-03-26 20:54:15
 */
import com.qiusen.domain.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {
}
