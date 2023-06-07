package com.laolang.jx.gen.mapstruct;

import com.laolang.jx.gen.entity.GenInfo;
import com.laolang.jx.gen.rsp.GenInfoListRsp;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GenInfoMapstruct {

    GenInfoListRsp entity2ListRsp(GenInfo entity);
}
