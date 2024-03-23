package com.qiusen.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qiusen.domain.entity.Comment;
import com.qiusen.enums.ResponseResult;

/**
 * 评论表(Comment)表服务接口
 *
 * @author qiusen
 * @since 2024-03-21 00:09:35
 */
public interface CommentService extends IService<Comment> {
    ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize);

    ResponseResult addComment(Comment comment);
}
