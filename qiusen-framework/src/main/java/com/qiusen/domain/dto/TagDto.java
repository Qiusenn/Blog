package com.qiusen.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class TagDto {

    private Long id;

    /* 标签名 */
    private String name;

    /* 备注 */
    private String remark;
}
