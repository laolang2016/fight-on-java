package com.laolang.jx.common.persist;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

public interface BaseService<T extends BaseEntity> extends IService<T> {

    /**
     * PageHelper 分页方式
     *
     * @param page    第几页
     * @param size    页尺寸
     * @param wrapper mybatis-plus 分页条件
     * @return PageInfo<T>
     */
    PageInfo<T> listPage(Integer page, Integer size, Wrapper<T> wrapper);
}
