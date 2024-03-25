package com.qiusen.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
/**
 * 菜单权限表(Menu)表数据库访问层
 *
 * @author qiusen
 * @since 2024-03-24 16:32:21
 */
import com.qiusen.domain.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    List<String> selectPermsByUserId(Long id);

    List<Menu> selectAllRouterMenu();

    List<Menu> selectRouterMenuTreeByUserId(Long userId);
}
