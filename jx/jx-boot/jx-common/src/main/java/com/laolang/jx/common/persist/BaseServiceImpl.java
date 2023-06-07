package com.laolang.jx.common.persist;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.time.LocalDateTime;
import java.util.Collection;
import org.springframework.transaction.annotation.Transactional;

public class BaseServiceImpl<M extends BaseMapper<T>, T extends BaseEntity> extends ServiceImpl<M, T> implements BaseService<T> {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean save(T entity) {
        entity.setVersion(1);
        entity.setCreateTime(LocalDateTime.now());
        return super.save(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateById(T entity) {
        entity.setUpdateTime(LocalDateTime.now());
        return super.updateById(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateBatchById(Collection<T> entityList) {
        if (CollUtil.isNotEmpty(entityList)) {
            for (T t : entityList) {
                t.setUpdateTime(LocalDateTime.now());
            }
        }
        return super.updateBatchById(entityList);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveBatch(Collection<T> entityList) {
        if (CollUtil.isNotEmpty(entityList)) {
            for (T t : entityList) {
                t.setVersion(1);
                t.setCreateTime(LocalDateTime.now());
            }
        }
        return super.saveBatch(entityList);
    }

    @Override
    public PageInfo<T> listPage(Integer page, Integer size, Wrapper<T> wrapper) {
        PageHelper.startPage(page, size);
        return new PageInfo<>(list(wrapper));
    }
}

