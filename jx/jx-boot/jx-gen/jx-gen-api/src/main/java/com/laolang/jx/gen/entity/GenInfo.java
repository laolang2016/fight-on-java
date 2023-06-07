package com.laolang.jx.gen.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.laolang.jx.common.persist.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("gen_info")
public class GenInfo extends BaseEntity {

    private String tableName;
    private String tableComment;
    private String tplType;
    private String genType;
    private String packageName;
    private String moduleName;
    private String className;
    private String functionName;
    private String functionAuthor;
    private Integer deleted;
}
