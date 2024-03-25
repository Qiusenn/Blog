package com.qiusen.controller;

import com.qiusen.domain.dto.TagDto;
import com.qiusen.domain.dto.TagListDto;
import com.qiusen.domain.vo.PageVo;
import com.qiusen.domain.vo.TagVo;
import com.qiusen.enums.ResponseResult;
import com.qiusen.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/content/tag")
@Api(tags = "标签")
public class TagController {
    @Autowired
    private TagService tagService;


    @ApiOperation("获取标签列表")
    @GetMapping("/list")
    public ResponseResult<PageVo> list(Integer pageNum, Integer pageSize, TagListDto tagListDto){
        return tagService.pageTagList(pageNum,pageSize,tagListDto);
    }

    @ApiOperation("新增标签")
    @PostMapping
    public ResponseResult add(@RequestBody TagListDto tagListDto){
        return tagService.add(tagListDto);
    }

    @ApiOperation("删除标签")
    @DeleteMapping("{id}")
    public ResponseResult del(@PathVariable Long id){
        return tagService.del(id);
    }

    @ApiOperation("修改标签")
    @PutMapping
    public ResponseResult add(@RequestBody TagDto TagDto){
        return tagService.updateTag(TagDto);
    }

    @ApiOperation("查询标签")
    @GetMapping("{id}")
    public ResponseResult get(@PathVariable Long id){
        return tagService.get(id);
    }

    @GetMapping("/listAllTag")
    public ResponseResult listAllTag(){
        List<TagVo> list = tagService.listAllTag();
        return ResponseResult.okResult(list);
    }
}
