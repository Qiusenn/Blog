package com.qiusen.domain.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 评论表(Comment)表实体类
 *
 * @author qiusen
 * @since 2024-03-21 00:09:33
 */
@SuppressWarnings("serial")
@AllArgsConstructor
@Data
@NoArgsConstructor
@TableName("sg_comment")
public class Comment {

    private Long id;
    
    /* 评论类型（0代表文章评论，1代表友链评论） */
    private String type;
    
    /* 文章id */
    private Long articleId;
    
    /* 根评论id */
    private Long rootId;
    
    /* 评论内容 */
    private String content;
    
    /* 所回复的目标评论的userid */
    private Long toCommentUserId;
    
    /* 回复目标评论id */
    private Long toCommentId;

    private Long createBy;

    private Date createTime;

    private Long updateBy;

    private Date updateTime;
    
    /* 删除标志（0代表未删除，1代表已删除） */
    private Integer delFlag;


}

