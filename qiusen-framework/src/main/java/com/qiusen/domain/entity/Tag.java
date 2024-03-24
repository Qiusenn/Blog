package com.qiusen.domain.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 标签(Tag)表实体类
 *
 * @author makejava
 * @since 2024-03-24 15:32:38
 */
@SuppressWarnings("serial")
@AllArgsConstructor
@Data
@NoArgsConstructor
@TableName("sg_tag")
public class Tag {

    private Long id;
    
    /* 标签名 */
    private String name;

    private Long createBy;

    private Date createTime;

    private Long updateBy;

    private Date updateTime;
    
    /* 删除标志（0代表未删除，1代表已删除） */
    private Integer delFlag;
    
    /* 备注 */
    private String remark;


}

