package com.qiusen.controller;


import com.qiusen.domain.entity.Menu;
import com.qiusen.domain.vo.AdminMenuDetailVo;
import com.qiusen.domain.vo.AdminMenuListVo;
import com.qiusen.domain.vo.AdminRoleMenuTreeVo;
import com.qiusen.domain.vo.AdminTreeSelectVo;
import com.qiusen.enums.ResponseResult;
import com.qiusen.service.MenuService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/menu")
@Api(tags = "菜单")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 获取菜单列表
     * @param status
     * @param menuName
     * @return
     */
    @GetMapping("list")
    public ResponseResult list(@RequestParam(value = "status", required = false) String status,
                               @RequestParam(value = "menuName", required = false) String menuName){
        List<AdminMenuListVo> adminMenuListVos =  menuService.getAllList(status, menuName);
        return ResponseResult.okResult(adminMenuListVos);
    }

    /**
     * 保存菜单数据
     * @param menu
     * @return
     */
    @PostMapping
    public ResponseResult save(@RequestBody Menu menu) {
        menuService.save(menu);
        return ResponseResult.okResult();
    }

    /**
     * 根据id获取菜单数据
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public ResponseResult getMenuById(@PathVariable Integer id){
        AdminMenuDetailVo adminMenuDetailVo = menuService.getMenuDetailById(id);
        return ResponseResult.okResult(adminMenuDetailVo);
    }

    /**
     * 更新菜单数据
     * @param menu
     * @return
     */
    @PutMapping
    public ResponseResult put(@RequestBody Menu menu){
        return menuService.put(menu);
    }

    /**
     * 根据id删除菜单数据
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public ResponseResult del(@PathVariable Integer id){
        menuService.removeById(id);
        return ResponseResult.okResult();
    }

    @GetMapping("treeselect")
    public ResponseResult treeselect(){
        List<AdminTreeSelectVo> list = menuService.treeselect();
        return ResponseResult.okResult(list);
    }

    @GetMapping("/roleMenuTreeselect/{id}")
    public ResponseResult getRoleMenuTreeSelect(@PathVariable Integer id) {
        AdminRoleMenuTreeVo data = menuService.getRoleMenuTreeSelect(id);
        return ResponseResult.okResult(data);
    }

}
