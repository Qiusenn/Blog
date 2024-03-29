package com.qiusen.controller;

import com.qiusen.domain.dto.AdminRoleChangeStatusDto;
import com.qiusen.domain.dto.AdminRoleDetailDto;
import com.qiusen.domain.dto.AdminRoleDto;
import com.qiusen.domain.dto.RoleDto;
import com.qiusen.domain.vo.AdminRoleDetailVo;
import com.qiusen.domain.vo.PageVo;
import com.qiusen.enums.ResponseResult;
import com.qiusen.service.RoleService;
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

    /**
     * 添加角色
     * @param AdminRoleDto
     * @return
     */
    @PostMapping
    public ResponseResult addRole(@RequestBody AdminRoleDto AdminRoleDto){
        roleService.addRole(AdminRoleDto);
        return ResponseResult.okResult();
    }

    /**
     * 根据id获取角色
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public ResponseResult getById(@PathVariable Integer id) {
        AdminRoleDetailVo data = roleService.getRoleById(id);
        return ResponseResult.okResult(data);
    }

    /**
     * 更新角色
     * @param adminRoleDetailDto
     * @return
     */
    @PutMapping
    public ResponseResult updateRole(@RequestBody AdminRoleDetailDto adminRoleDetailDto) {
        roleService.updateRole(adminRoleDetailDto);
        return ResponseResult.okResult();
    }

    /**
     * 删除角色
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public ResponseResult delRoleById(@PathVariable String id) {
        List<String> split = Arrays.asList(id.split(","));
        roleService.removeByIds(split);
        return ResponseResult.okResult();
    }

    /**
     * 获取全部角色列表
     * @return
     */
    @GetMapping("listAllRole")
    public ResponseResult listAllRole() {
        return ResponseResult.okResult(roleService.list());
    }


}
