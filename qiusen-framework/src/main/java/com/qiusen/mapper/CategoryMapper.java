package com.qiusen.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qiusen.domain.entity.Category;
import org.apache.ibatis.annotations.Mapper;

/**
 * 分类表(Category)表数据库访问层
 *
 * @author qiusen
 * @since 2024-03-19 13:59:25
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}
