package com.qiusen.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AdminUserUpdateDto {
    private String email;
    private String nickName;
    private String sex;
    private String status;
    private String userName;
    private Long id;
    private List<String> roleIds;

}
