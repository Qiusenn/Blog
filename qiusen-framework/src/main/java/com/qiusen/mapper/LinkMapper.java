package com.qiusen.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
/**
 * 友链(Link)表数据库访问层
 *
 * @author qiusen
 * @since 2024-03-19 20:42:13
 */
import com.qiusen.domain.entity.Link;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LinkMapper extends BaseMapper<Link> {
}
