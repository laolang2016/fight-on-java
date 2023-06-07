package com.laolang.jx.gen.logic;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageInfo;
import com.laolang.jx.common.logic.BaseLogic;
import com.laolang.jx.gen.entity.GenInfo;
import com.laolang.jx.gen.mapstruct.GenInfoMapstruct;
import com.laolang.jx.gen.req.GenInfoListReq;
import com.laolang.jx.gen.rsp.GenInfoListRsp;
import com.laolang.jx.gen.service.GenInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class GenInfoLogic extends BaseLogic {

    private final GenInfoService genInfoService;
    private final GenInfoMapstruct genInfoMapstruct;

    public PageInfo<GenInfoListRsp> list(GenInfoListReq req) {
        log.info("gen list info");
        LambdaQueryWrapper<GenInfo> wrapper = Wrappers.lambdaQuery(GenInfo.class);
        PageInfo<GenInfo> pageinfo = genInfoService.listPage(req.getPage(), req.getSize(), wrapper);
        return buildPageInfo(pageinfo, genInfoMapstruct::entity2ListRsp);
    }
}
