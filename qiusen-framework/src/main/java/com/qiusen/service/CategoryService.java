package com.qiusen.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qiusen.domain.entity.Category;
import com.qiusen.enums.ResponseResult;

/**
 * 分类表(Category)表服务接口
 *
 * @author qiusen
 * @since 2024-03-19 13:55:25
 */
public interface CategoryService extends IService<Category> {
    ResponseResult getCategoryList();
}
