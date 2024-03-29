package com.qiusen.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.qiusen.domain.dto.CategryListDto;
import com.qiusen.domain.dto.AdminCategoryAddVo;
import com.qiusen.domain.entity.Category;
import com.qiusen.domain.vo.CategoryListVo;
import com.qiusen.domain.vo.CategoryVo;
import com.qiusen.domain.vo.ExcelCategoryVo;
import com.qiusen.domain.vo.PageVo;
import com.qiusen.enums.AppHttpCodeEnum;
import com.qiusen.enums.ResponseResult;
import com.qiusen.service.CategoryService;
import com.qiusen.utils.BeanCopyUtils;
import com.qiusen.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/content/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 获取所有分类列表
     * @return
     */
    @GetMapping("/listAllCategory")
    public ResponseResult listAllCategory(){
        List<CategoryVo> list = categoryService.listAllCategory();
        return ResponseResult.okResult(list);
    }

    /**
     * 导出分类
     * @param response
     */
    @PreAuthorize("@ps.hasPermission('content:category:export')")
    @GetMapping("/export")
    public void export(HttpServletResponse response){
        try {
            //设置下载文件的请求头
            WebUtils.setDownLoadHeader("分类.xlsx",response);
            //获取需要导出的数据
            List<Category> categoryVos = categoryService.list();
            List<ExcelCategoryVo> excelCategoryVos = BeanCopyUtils.copyBeanList(categoryVos, ExcelCategoryVo.class);
            //把数据写入到Excel中
            EasyExcel.write(response.getOutputStream(), ExcelCategoryVo.class).autoCloseStream(Boolean.FALSE).sheet("分类导出").doWrite(excelCategoryVos);
        } catch (Exception e) {
            //如果出现异常也要响应json
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
            WebUtils.renderString(response, JSON.toJSONString(result));
        }
    }

    /**
     * 分页获取条件获取分类列表
     * @param pageNum
     * @param pageSize
     * @param categryListDto
     * @return
     */
    @GetMapping("list")
    public ResponseResult<PageVo> list(Integer pageNum, Integer pageSize, CategryListDto categryListDto) {
        PageVo data = categoryService.queryList(pageNum, pageSize, categryListDto);
        return ResponseResult.okResult(data);
    }

    /**
     * 添加分类
     * @param adminCategoryAddVo
     * @return
     */
    @PostMapping
    public ResponseResult add(@RequestBody AdminCategoryAddVo adminCategoryAddVo) {
        Category category = BeanCopyUtils.copyBean(adminCategoryAddVo, Category.class);
        categoryService.save(category);
        return ResponseResult.okResult();
    }

    /**
     * 根据id获取 分类
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public ResponseResult<PageVo> getById(@PathVariable Integer id) {
        CategoryListVo data = categoryService.getCategoryById(id);
        return ResponseResult.okResult(data);
    }

    /**
     * 更新分类
     * @param categoryListVo
     * @return
     */
    @PutMapping()
    public ResponseResult<PageVo> updateCategory(@RequestBody CategoryListVo categoryListVo) {
        categoryService.updateCategory(categoryListVo);
        return ResponseResult.okResult();
    }

    /**
     * 逻辑删除分类
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public ResponseResult del(@PathVariable String id) {
        List<String> split = Arrays.asList(id.split(","));
        categoryService.removeByIds(split);
        return ResponseResult.okResult();
    }

}

