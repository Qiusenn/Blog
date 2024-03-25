package com.qiusen.controller;


import com.qiusen.domain.vo.AdminMenuListVo;
import com.qiusen.enums.ResponseResult;
import com.qiusen.service.MenuService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/system/menu")
@Api(tags = "菜单")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("list")
    public ResponseResult list(@RequestParam(value = "status", required = false) String status,
                               @RequestParam(value = "menuName", required = false) String menuName){
        List<AdminMenuListVo> adminMenuListVos =  menuService.getAllList(status, menuName);
        return ResponseResult.okResult(adminMenuListVos);
    }
}
