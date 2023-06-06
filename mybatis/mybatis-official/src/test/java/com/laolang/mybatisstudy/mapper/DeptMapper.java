package com.laolang.mybatisstudy.mapper;

import com.laolang.mybatisstudy.entity.Dept;
import org.apache.ibatis.annotations.Param;

public interface DeptMapper {

    Dept getById(@Param("id") Long id);
}
