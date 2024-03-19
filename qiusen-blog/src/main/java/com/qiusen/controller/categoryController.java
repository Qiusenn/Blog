package com.qiusen.controller;


import com.qiusen.enums.ResponseResult;
import com.qiusen.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/category")
@Api(tags = "分类")
@RestController
public class categoryController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation("分类列表")
    @GetMapping("/getCategoryList")
    public ResponseResult getCategoryList() {
        return categoryService.getCategoryList();
    }
}
