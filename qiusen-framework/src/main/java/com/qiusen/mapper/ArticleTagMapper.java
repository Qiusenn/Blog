package com.qiusen.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
/**
 * 文章标签关联表(ArticleTag)表数据库访问层
 *
 * @author qiusen
 * @since 2024-03-25 18:10:08
 */
import com.qiusen.domain.entity.ArticleTag;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleTagMapper extends BaseMapper<ArticleTag> {
}
