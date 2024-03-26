package com.qiusen.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiusen.constants.SystemConstants;
import com.qiusen.domain.dto.CategryListDto;
import com.qiusen.domain.entity.Article;
import com.qiusen.domain.entity.Category;
import com.qiusen.domain.vo.CategoryListVo;
import com.qiusen.domain.vo.CategoryVo;
import com.qiusen.domain.vo.PageVo;
import com.qiusen.enums.ResponseResult;
import com.qiusen.mapper.ArticleMapper;
import com.qiusen.mapper.CategoryMapper;
import com.qiusen.service.ArticleService;
import com.qiusen.service.CategoryService;
import com.qiusen.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 分类表(Category)表服务实现类
 *
 * @author qiusen
 * @since 2024-03-19 13:57:52
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private ArticleService articleService;
    @Override
    public ResponseResult getCategoryList() {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        List<Article> listArticle = articleService.list(queryWrapper);
        Set<Long> ids = listArticle.stream().map(Article::getCategoryId).collect(Collectors.toSet());

        List<Category> categories = listByIds(ids);
        categories = categories.stream().filter(o -> SystemConstants.STATUS_NORMAL.equals(o.getStatus())).collect(Collectors.toList());

        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(categories, CategoryVo.class);

        return ResponseResult.okResult(categoryVos);
    }

    @Override
    public List<CategoryVo> listAllCategory() {
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Category::getStatus, SystemConstants.NORMAL);
        List<Category> list = list(wrapper);
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(list, CategoryVo.class);
        return categoryVos;

    }

    @Override
    public PageVo queryList(Integer pageNum, Integer pageSize, CategryListDto categryListDto) {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(categryListDto.getName()), Category::getName, categryListDto.getName());
        queryWrapper.eq(StringUtils.hasText(categryListDto.getStatus()), Category::getStatus, categryListDto.getStatus());
        Page<Category> page = new Page<>();
        page.setSize(pageSize);
        page.setCurrent(pageNum);
        Page<Category> categoryPage = page(page, queryWrapper);
        return new PageVo(BeanCopyUtils.copyBeanList(categoryPage.getRecords(), CategoryListVo.class), categoryPage.getTotal());
    }

    @Override
    public CategoryListVo getCategoryById(Integer id) {
        return BeanCopyUtils.copyBean(getById(id), CategoryListVo.class);
    }

    @Override
    public void updateCategory(CategoryListVo categoryListVo) {
        updateById(BeanCopyUtils.copyBean(categoryListVo, Category.class));
    }
}
