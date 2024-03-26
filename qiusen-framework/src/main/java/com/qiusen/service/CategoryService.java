package com.qiusen.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qiusen.domain.dto.CategryListDto;
import com.qiusen.domain.entity.Category;
import com.qiusen.domain.vo.CategoryListVo;
import com.qiusen.domain.vo.CategoryVo;
import com.qiusen.domain.vo.PageVo;
import com.qiusen.enums.ResponseResult;

import java.util.List;

/**
 * 分类表(Category)表服务接口
 *
 * @author qiusen
 * @since 2024-03-19 13:55:25
 */
public interface CategoryService extends IService<Category> {
    ResponseResult getCategoryList();

    List<CategoryVo> listAllCategory();

    PageVo queryList(Integer pageNum, Integer pageSize, CategryListDto categryListDto);

    CategoryListVo getCategoryById(Integer id);

    void updateCategory(CategoryListVo categoryListVo);
}
