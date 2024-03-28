package com.qiusen.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AdminUserVo {
    private String avatar;
    private Long id;
    private String nickName;
    private String email;
    private String createTime;
    private String phonenumber;
    private String sex;
    private String status;
    private String updateBy;
    private String updateTime;
    private String userName;


}
