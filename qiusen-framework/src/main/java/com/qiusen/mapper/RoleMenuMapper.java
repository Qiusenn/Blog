package com.qiusen.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
/**
 * 角色和菜单关联表(RoleMenu)表数据库访问层
 *
 * @author qiusen
 * @since 2024-03-26 15:56:39
 */
import com.qiusen.domain.entity.RoleMenu;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {
}
