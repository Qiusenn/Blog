package com.qiusen.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qiusen.domain.dto.AddArticleDto;
import com.qiusen.domain.dto.AdminArticleDetailDto;
import com.qiusen.domain.dto.ArticleListDto;
import com.qiusen.domain.entity.Article;
import com.qiusen.domain.vo.AdminArticleDetailVo;
import com.qiusen.domain.vo.ArticleVo;
import com.qiusen.domain.vo.PageVo;
import com.qiusen.enums.ResponseResult;

import java.util.List;

public interface ArticleService extends IService<Article> {
    ResponseResult hotArticleList();

    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);

    ResponseResult getArticleDetail(Long id);

    ResponseResult updateViewCount(Long id);

    ResponseResult add(AddArticleDto article);

    PageVo getList(Integer pageNum, Integer pageSize, ArticleListDto articleListDto);

    AdminArticleDetailVo getArticleById(Integer id);

    void updateArticle(AdminArticleDetailDto adminArticleDetailDto);

    void updateBatchByIdJob(List<Article> articles);
}
