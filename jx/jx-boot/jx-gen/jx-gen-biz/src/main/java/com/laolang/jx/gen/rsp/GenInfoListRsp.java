package com.laolang.jx.gen.rsp;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class GenInfoListRsp {
    private Long id;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String tableName;
    private String tableComment;
    private String className;
}
