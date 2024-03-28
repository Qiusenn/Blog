package com.qiusen.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AdminUserAddDto {
    private String userName;
    private String nickName;
    private String password;
    private String phonenumber;
    private String email;
    private String sex;
    private String status;
    private List<String> roleIds;
}
