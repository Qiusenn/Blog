package com.qiusen.domain.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "评论")
public class Comment {

    private Long id;
    @ApiModelProperty(notes = "评论类型（0代表文章评论，1代表友链评论）")
    /* 评论类型（0代表文章评论，1代表友链评论） */
    private String type;

    @ApiModelProperty(notes = "文章id")
    /* 文章id */
    private Long articleId;

    @ApiModelProperty(notes = "根评论id")
    /* 根评论id */
    private Long rootId;

    @ApiModelProperty(notes = "评论内容")
    /* 评论内容 */
    private String content;

    @ApiModelProperty(notes = "所回复的目标评论的userid")
    /* 所回复的目标评论的userid */
    private Long toCommentUserId;

    @ApiModelProperty(notes = "回复目标评论id")
    /* 回复目标评论id */
    private Long toCommentId;

    @ApiModelProperty(notes = "创建人的用户id")
    /**
     * 创建人的用户id
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    @ApiModelProperty(notes = "创建时间")
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(notes = "更新人")
    /**
     * 更新人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    @ApiModelProperty(notes = "更新时间")
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(notes = "删除标志（0代表未删除，1代表已删除）")
    /* 删除标志（0代表未删除，1代表已删除） */
    private Integer delFlag;


}

