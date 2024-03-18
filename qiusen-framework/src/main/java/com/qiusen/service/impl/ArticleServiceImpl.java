package com.qiusen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiusen.enums.ResponseResult;
import com.qiusen.mapper.ArticleMapper;
import com.qiusen.service.ArticleService;
import com.qiusen.domain.entity.Article;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    /**
     * 分页获取热门文章
     * @return
     */
    @Override
    public ResponseResult hotArticleList() {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getDelFlag, 0);
        queryWrapper.eq(Article::getStatus, 0);
        //按照浏览量进行排序
        queryWrapper.orderByDesc(Article::getViewCount);

        Page<Article> page = new Page<>(1, 10);
        page(page, queryWrapper);
        List<Article> records = page.getRecords();
        return ResponseResult.okResult(records);
    }
}
