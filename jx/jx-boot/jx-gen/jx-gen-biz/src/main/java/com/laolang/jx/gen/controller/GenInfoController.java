package com.laolang.jx.gen.controller;

import com.github.pagehelper.PageInfo;
import com.laolang.jx.common.domain.R;
import com.laolang.jx.gen.logic.GenInfoLogic;
import com.laolang.jx.gen.req.GenInfoListReq;
import com.laolang.jx.gen.rsp.GenInfoListRsp;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/gen")
@RequiredArgsConstructor
@RestController
public class GenInfoController {

    public final GenInfoLogic genInfoLogic;

    @GetMapping("list")
    public R<PageInfo<GenInfoListRsp>> list(@RequestBody GenInfoListReq req) {
        return R.ok(genInfoLogic.list(req));
    }
}
