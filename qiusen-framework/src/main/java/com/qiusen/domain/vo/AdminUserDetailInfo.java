package com.qiusen.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AdminUserDetailInfo {
    private String email;
    private Long id;
    private String nickName;
    private String sex;
    private String status;
    private String userName;
    private String phonenumber;

}
