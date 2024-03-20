package com.qiusen.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
/**
 * 评论表(Comment)表数据库访问层
 *
 * @author qiusen
 * @since 2024-03-21 00:09:33
 */
import com.qiusen.domain.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
}
