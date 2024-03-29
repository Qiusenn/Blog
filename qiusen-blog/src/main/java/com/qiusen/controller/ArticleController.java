package com.qiusen.controller;

import com.qiusen.enums.ResponseResult;
import com.qiusen.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@Api(tags = "文章")
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 获取热门文章
     * @return
     */
    @ApiOperation("获取热门文章")
    @GetMapping("/hotArticleList")
    public ResponseResult hotArticleList() {
        return articleService.hotArticleList();
    }

    /**
     * 根据分类id分页获取文章列表
     * @param pageNum
     * @param pageSize
     * @param categoryId
     * @return
     */
    @ApiOperation("获取文章列表")
    @GetMapping("/articleList")
    public ResponseResult articleList(Integer pageNum,Integer pageSize,Long categoryId){
        return articleService.articleList(pageNum,pageSize,categoryId);
    }

    /**
     * 根据id获取文章详情
     * @param id
     * @return
     */
    @ApiOperation("获取文章详情")
    @GetMapping("/{id}")
    public ResponseResult getArticleDetail(@PathVariable("id") Long id){
        return articleService.getArticleDetail(id);
    }

    /**
     * 根据id更新博客文章对应浏览量
     * @param id
     * @return
     */
    @ApiOperation("获取文章浏览记录数")
    @PutMapping("/updateViewCount/{id}")
    public ResponseResult updateViewCount(@PathVariable("id") Long id){
        return articleService.updateViewCount(id);
    }



}
