package com.qiusen.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CategryListDto {

    /* 分类名称 */
    private String name;

    /* 状态0:正常,1禁用 */
    private String status;
}
