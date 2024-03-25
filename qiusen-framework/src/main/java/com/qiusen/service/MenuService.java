package com.qiusen.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qiusen.domain.entity.Menu;

import java.util.List;

/**
 * 菜单权限表(Menu)表服务接口
 *
 * @author qiusen
 * @since 2024-03-24 16:32:23
 */
public interface MenuService extends IService<Menu> {
    List<String> selectPermsByUserId(Long id);

    List<Menu> selectRouterMenuTreeByUserId(Long userId);
}
