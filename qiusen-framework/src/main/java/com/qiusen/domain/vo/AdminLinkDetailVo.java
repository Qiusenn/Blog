package com.qiusen.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AdminLinkDetailVo {

    private String address;

    private String description;

    private Long id;

    private String logo;

    private String name;

    private String status;
}
