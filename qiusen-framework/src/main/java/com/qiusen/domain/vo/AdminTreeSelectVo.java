package com.qiusen.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class AdminTreeSelectVo {
    /* 菜单ID */
    private Long id;

    /* 标签 */
    private String label;

    /* 父节点id */
    private Long parentId;

    /* 子节点 */
    private List<AdminTreeSelectVo> children;
}
