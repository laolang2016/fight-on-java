package com.laolang.jx.mybatisplus;

import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.laolang.jx.**.mapper")
public class MybatisPlusConfig {

    /**
     * 打印 sql 拦截器
     *
     * @return Interceptor
     */
    @Bean
    public Interceptor mybatisPrintSqlInterceptor() {
        return new MybatisPrintSqlInterceptor();
    }
}
