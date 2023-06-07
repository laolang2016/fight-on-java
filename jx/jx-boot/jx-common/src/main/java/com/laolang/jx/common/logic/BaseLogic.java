package com.laolang.jx.common.logic;

import cn.hutool.core.collection.CollUtil;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BaseLogic {

    protected <T, R> PageInfo<R> buildPageInfo(PageInfo<T> pageInfo, Function<T, R> tranform) {
        PageInfo<R> p = new PageInfo<>();
        p.setPageNum(pageInfo.getPageNum());
        p.setPageSize(pageInfo.getPageSize());
        p.setTotal(pageInfo.getTotal());
        p.setPages(pageInfo.getPages());
        p.setList(CollUtil.isEmpty(pageInfo.getList()) ? Lists.newArrayList() :
                pageInfo.getList().stream().map(tranform).collect(Collectors.toList()));
        return p;
    }
}
