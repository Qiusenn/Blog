package com.qiusen.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
/**
 * 标签(Tag)表数据库访问层
 *
 * @author makejava
 * @since 2024-03-24 15:32:38
 */
import com.qiusen.domain.entity.Tag;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TagMapper extends BaseMapper<Tag> {
}
