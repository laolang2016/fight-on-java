package com.laolang.jx.common.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BasePageReq extends BaseReq{

    private Integer page;
    private Integer size;
}
