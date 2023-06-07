package com.laolang.jx.gen.service.impl;

import com.laolang.jx.common.persist.BaseServiceImpl;
import com.laolang.jx.gen.entity.GenInfo;
import com.laolang.jx.gen.mapper.GenInfoMapper;
import com.laolang.jx.gen.service.GenInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GenInfoServiceImpl extends BaseServiceImpl<GenInfoMapper, GenInfo> implements GenInfoService {

    private final GenInfoMapper genInfoMapper;
}
