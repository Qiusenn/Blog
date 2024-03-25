package com.qiusen.controller;

import com.qiusen.domain.dto.AddArticleDto;
import com.qiusen.domain.dto.AdminArticleDetailDto;
import com.qiusen.domain.dto.ArticleListDto;
import com.qiusen.domain.vo.AdminArticleDetailVo;
import com.qiusen.domain.vo.PageVo;
import com.qiusen.enums.ResponseResult;
import com.qiusen.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/content/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 添加文章
     * @param article
     * @return
     */
    @PostMapping
    public ResponseResult add(@RequestBody AddArticleDto article){
        return articleService.add(article);
    }

    /**
     * 获取文章列表
     * @param pageNum
     * @param pageSize
     * @param articleListDto
     * @return
     */
    @GetMapping("list")
    public ResponseResult<PageVo> getList(Integer pageNum, Integer pageSize, ArticleListDto articleListDto) {
        PageVo page = articleService.getList(pageNum, pageSize, articleListDto);
        return ResponseResult.okResult(page);
    }

    /**
     * 获取博客信息
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public ResponseResult getById(@PathVariable Integer id) {
        AdminArticleDetailVo article = articleService.getArticleById(id);
        return ResponseResult.okResult(article);
    }

    /**
     * 更改博客信息
     * @param adminArticleDetailDto
     * @return
     */
    @PutMapping
    public ResponseResult update(@RequestBody AdminArticleDetailDto adminArticleDetailDto) {
        articleService.updateArticle(adminArticleDetailDto);
        return ResponseResult.okResult();
    }

    @DeleteMapping("{id}")
    public ResponseResult del(@PathVariable Integer id) {
        articleService.removeById(id);
        return ResponseResult.okResult();
    }

}

