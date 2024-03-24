package com.qiusen.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiusen.domain.entity.Tag;
import com.qiusen.mapper.TagMapper;
import com.qiusen.service.TagService;
import org.springframework.stereotype.Service;
/**
 * 标签(Tag)表服务实现类
 *
 * @author qiusen
 * @since 2024-03-24 15:32:40
 */
@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {
}
