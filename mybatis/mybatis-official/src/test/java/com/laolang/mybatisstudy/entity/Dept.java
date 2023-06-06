package com.laolang.mybatisstudy.entity;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Dept {
    private Long id;
    private String name;
    private Long parentId;
    private Long managerUserId;
    private Integer sort;
    private Long createBy;
    private LocalDateTime createTime;
    private Long updateBy;
    private LocalDateTime updateTime;
    private String remark;
    private Integer deleted;
    private Integer version;
}
