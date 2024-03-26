package com.qiusen.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qiusen.domain.dto.TagDto;
import com.qiusen.domain.dto.TagListDto;
import com.qiusen.domain.entity.Tag;
import com.qiusen.domain.vo.PageVo;
import com.qiusen.domain.vo.TagVo;
import com.qiusen.enums.ResponseResult;

import java.util.List;

/**
 * 标签(Tag)表服务接口
 *
 * @author qiusen
 * @since 2024-03-24 15:32:40
 */
public interface TagService extends IService<Tag> {
    ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto);

    ResponseResult add(TagListDto tagListDto);

    ResponseResult del(String id);

    ResponseResult updateTag(TagDto tagDto);

    ResponseResult get(Long id);

    List<TagVo> listAllTag();
}
