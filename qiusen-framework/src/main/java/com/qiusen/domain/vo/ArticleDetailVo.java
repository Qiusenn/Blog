package com.qiusen.domain.vo;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ArticleDetailVo {

    @TableId
    private Long id;

    /* 标题 */
    private String title;

    /* 文章内容 */
    private String content;

    /* 所属分类id */
    private Long categoryId;

    /* 分类名称 */
    private String categoryName;

    /* 访问量 */
    private Long viewCount;

    /* 是否允许评论 1是，0否 */
    private String isComment;

    private Date createTime;

}
