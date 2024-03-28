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
 * 用户表(User)表实体类
 *
 * @author makejava
 * @since 2024-03-19 22:02:49
 */
@SuppressWarnings("serial")
@AllArgsConstructor
@Data
@NoArgsConstructor
@TableName("sys_user")
@ApiModel(description = "用户")
public class User {
    
    /* 主键 */
    private Long id;

    @ApiModelProperty(notes = "用户名")
    /* 用户名 */
    private String userName;

    @ApiModelProperty(notes = "昵称")
    /* 昵称 */
    private String nickName;

    @ApiModelProperty(notes = "密码")
    /* 密码 */
    private String password;

    @ApiModelProperty(notes = "用户类型：0代表普通用户，1代表管理员")
    /* 用户类型：0代表普通用户，1代表管理员 */
    private String type;

    @ApiModelProperty(notes = "账号状态（0正常 1停用）")
    /* 账号状态（0正常 1停用） */
    private String status;

    @ApiModelProperty(notes = "邮箱")
    /* 邮箱 */
    private String email;

    @ApiModelProperty(notes = "手机号")
    /* 手机号 */
    private String phonenumber;

    @ApiModelProperty(notes = "用户性别（0男，1女，2未知）")
    /* 用户性别（0男，1女，2未知） */
    private String sex;

    @ApiModelProperty(notes = "头像")
    /* 头像 */
    private String avatar;

    @ApiModelProperty(notes = "创建人的用户id")
    @TableField(fill = FieldFill.INSERT)
    /* 创建人的用户id */
    private Long createBy;

    @ApiModelProperty(notes = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    /* 创建时间 */
    private Date createTime;

    @ApiModelProperty(notes = "更新人")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    /* 更新人 */
    private Long updateBy;

    @ApiModelProperty(notes = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    /* 更新时间 */
    private Date updateTime;

    @ApiModelProperty(notes = "删除标志（0代表未删除，1代表已删除）")
    /* 删除标志（0代表未删除，1代表已删除） */
    private Integer delFlag;


}

