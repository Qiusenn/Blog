package com.qiusen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qiusen.domain.entity.Article;
import com.qiusen.enums.ResponseResult;

public interface ArticleService extends IService<Article> {
    ResponseResult hotArticleList();
}
