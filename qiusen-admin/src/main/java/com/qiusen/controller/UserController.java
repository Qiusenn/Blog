package com.qiusen.controller;

import com.qiusen.domain.dto.AdminUserAddDto;
import com.qiusen.domain.dto.AdminUserDto;
import com.qiusen.domain.dto.AdminUserUpdateDto;
import com.qiusen.domain.dto.TagListDto;
import com.qiusen.domain.vo.AdminUserDetailVo;
import com.qiusen.domain.vo.PageVo;
import com.qiusen.enums.ResponseResult;
import com.qiusen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("system/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("list")
    public ResponseResult<PageVo> list(Integer pageNum, Integer pageSize, AdminUserDto adminUserDto){
        return userService.pageTagList(pageNum,pageSize,adminUserDto);
    }

    @PostMapping
    public ResponseResult add(@RequestBody AdminUserAddDto adminUserAddDto) {
        userService.add(adminUserAddDto);
        return ResponseResult.okResult();
    }

    @DeleteMapping("{ids}")
    public ResponseResult del(@PathVariable String ids) {
        List<String> split = Arrays.asList(ids.split(","));
        userService.removeByIds(split);
        return ResponseResult.okResult();
    }

    @GetMapping("{id}")
    public ResponseResult getUserById(@PathVariable Long id) {
        AdminUserDetailVo data = userService.getUserById(id);
        return ResponseResult.okResult(data);
    }

    @PutMapping
    public ResponseResult put(@RequestBody AdminUserUpdateDto adminUserUpdateDto) {
        userService.put(adminUserUpdateDto);
        return ResponseResult.okResult();
    }
}
