package com.qiusen.controller;

import com.qiusen.domain.dto.AdminLinkListDto;
import com.qiusen.domain.vo.AdminLinkDetailVo;
import com.qiusen.domain.vo.LinkVo;
import com.qiusen.domain.vo.PageVo;
import com.qiusen.enums.ResponseResult;
import com.qiusen.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/content/link")
public class LinkController {

    @Autowired
    private LinkService linkService;

    /**
     * 分页获取友链列表
     * @param pageNum
     * @param pageSize
     * @param adminLinkListDto
     * @return
     */
    @GetMapping("list")
    public ResponseResult<PageVo> list(Integer pageNum, Integer pageSize, AdminLinkListDto adminLinkListDto) {
        PageVo data =  linkService.queryList(pageNum, pageSize, adminLinkListDto);
        return ResponseResult.okResult(data);
    }

    /**
     * 添加友链
     * @param linkVo
     * @return
     */
    @PostMapping
    public ResponseResult add(@RequestBody LinkVo linkVo) {
        linkService.add(linkVo);
        return ResponseResult.okResult();
    }

    /**
     * 根据id获取对应友链
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public ResponseResult getLinkById(@PathVariable Integer id) {
        AdminLinkDetailVo adminLinkDetailVo = linkService.getLinkById(id);
        return ResponseResult.okResult(adminLinkDetailVo);
    }

    /**
     * 更新友链
     * @param AdminLinkDetailVo
     * @return
     */
    @PutMapping
    public ResponseResult updateLink(@RequestBody AdminLinkDetailVo AdminLinkDetailVo) {
        linkService.updateLink(AdminLinkDetailVo);
        return ResponseResult.okResult();
    }

    /**
     * 逻辑删除友链
     * @param ids
     * @return
     */
    @DeleteMapping("{ids}")
    public ResponseResult del(@PathVariable String ids) {
        List<String> split = Arrays.asList(ids.split(","));
        linkService.removeByIds(split);
        return ResponseResult.okResult();
    }
}
