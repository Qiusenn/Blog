package com.qiusen.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AdminLinkListDto {
    private String name;

    /* 角色状态（0正常 1停用） */
    private String status;
}
