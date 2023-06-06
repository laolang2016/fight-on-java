package com.laolang.mybatisstudy;

import cn.hutool.json.JSONUtil;
import com.laolang.mybatisstudy.entity.Dept;
import com.laolang.mybatisstudy.mapper.DeptMapper;
import java.io.Reader;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Slf4j
public class HelloWorldTest {

    private SqlSessionFactory sqlSessionFactory;

    @BeforeClass
    public void beforeClass() {
        try {
            Reader reader = Resources.getResourceAsReader("mybatis-hello-world-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            reader.close();
        } catch (Exception e) {
            log.error("初始化 mybatis 失败:{}", ExceptionUtils.getMessage(e));
        }
    }

    @Test
    public void testGetById() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            DeptMapper deptMapper = session.getMapper(DeptMapper.class);
            Dept dept = deptMapper.getById(1L);
            log.info(JSONUtil.toJsonPrettyStr(dept));
        }
    }
}
