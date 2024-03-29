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


    /**
     * 获取标签列表
     * @param pageNum
     * @param pageSize
     * @param tagListDto
     * @return
     */
    @ApiOperation("获取标签列表")
    @GetMapping("/list")
    public ResponseResult<PageVo> list(Integer pageNum, Integer pageSize, TagListDto tagListDto){
        return tagService.pageTagList(pageNum,pageSize,tagListDto);
    }

    /**
     * 新增标签
     * @param tagListDto
     * @return
     */
    @ApiOperation("新增标签")
    @PostMapping
    public ResponseResult add(@RequestBody TagListDto tagListDto){
        return tagService.add(tagListDto);
    }

    /**
     * 删除标签
     * @param id
     * @return
     */
    @ApiOperation("删除标签")
    @DeleteMapping("{id}")
    public ResponseResult del(@PathVariable String id){
        return tagService.del(id);
    }

    /**
     * 修改标签
     * @param TagDto
     * @return
     */
    @ApiOperation("修改标签")
    @PutMapping
    public ResponseResult add(@RequestBody TagDto TagDto){
        return tagService.updateTag(TagDto);
    }

    /**
     * 查询标签
     * @param id
     * @return
     */
    @ApiOperation("查询标签")
    @GetMapping("{id}")
    public ResponseResult get(@PathVariable Long id){
        return tagService.get(id);
    }

    /**
     * 获取全部标签
     * @return
     */
    @ApiOperation("获取全部标签")
    @GetMapping("/listAllTag")
    public ResponseResult listAllTag(){
        List<TagVo> list = tagService.listAllTag();
        return ResponseResult.okResult(list);
    }
}
