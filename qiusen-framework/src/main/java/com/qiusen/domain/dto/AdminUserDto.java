package com.qiusen.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AdminUserDto {
    private String userName;

    private String phonenumber;

    private String Status;
}
