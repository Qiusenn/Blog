package com.qiusen.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiusen.mapper.CommentMapper;
import com.qiusen.service.CommentService;
import com.qiusen.domain.entity.Comment;
import org.springframework.stereotype.Service;
/**
 * 评论表(Comment)表服务实现类
 *
 * @author qiusen
 * @since 2024-03-21 00:09:35
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
}
