package com.qiusen.controller;

import com.qiusen.domain.dto.AdminRoleChangeStatusDto;
import com.qiusen.domain.dto.AdminRoleDetailDto;
import com.qiusen.domain.dto.AdminRoleDto;
import com.qiusen.domain.dto.RoleDto;
import com.qiusen.domain.vo.AdminRoleDetailVo;
import com.qiusen.domain.vo.AdminRoleMenuTreeVo;
import com.qiusen.domain.vo.PageVo;
import com.qiusen.enums.ResponseResult;
import com.qiusen.service.RoleService;
import io.swagger.models.auth.In;
import org.simpleframework.xml.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("system/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 条件分页获取角色列表
     * @param pageNum
     * @param pageSize
     * @param roleDto
     * @return
     */
    @GetMapping("list")
    public ResponseResult<PageVo> list(Integer pageNum, Integer pageSize, RoleDto roleDto){
        return ResponseResult.okResult(roleService.queryList(pageNum, pageSize, roleDto));
    }

    /**
     * 更新状态
     * @param adminRoleChangeStatusDto
     * @return
     */
    @PutMapping("changeStatus")
    public ResponseResult putStatusByRoleId(@RequestBody AdminRoleChangeStatusDto adminRoleChangeStatusDto){
        roleService.putStatusByRoleId(adminRoleChangeStatusDto);
        return ResponseResult.okResult();
    }

    @PostMapping
    public ResponseResult addRole(@RequestBody AdminRoleDto AdminRoleDto){
        roleService.addRole(AdminRoleDto);
        return ResponseResult.okResult();
    }

    @GetMapping("{id}")
    public ResponseResult getById(@PathVariable Integer id) {
        AdminRoleDetailVo data = roleService.getRoleById(id);
        return ResponseResult.okResult(data);
    }

    @PutMapping
    public ResponseResult updateRole(@RequestBody AdminRoleDetailDto adminRoleDetailDto) {
        roleService.updateRole(adminRoleDetailDto);
        return ResponseResult.okResult();
    }

    @DeleteMapping("{id}")
    public ResponseResult updateRole(@PathVariable String id) {
        List<String> split = Arrays.asList(id.split(","));
        roleService.removeByIds(split);
        return ResponseResult.okResult();
    }


}
