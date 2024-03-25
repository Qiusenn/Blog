package com.qiusen.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiusen.domain.entity.ArticleTag;
import com.qiusen.mapper.ArticleTagMapper;
import com.qiusen.service.ArticleTagService;
import org.springframework.stereotype.Service;
/**
 * 文章标签关联表(ArticleTag)表服务实现类
 *
 * @author qiusen
 * @since 2024-03-25 18:10:10
 */
@Service("articleTagService")
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService {
}
