package com.qiusen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qiusen.domain.entity.Article;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
}
