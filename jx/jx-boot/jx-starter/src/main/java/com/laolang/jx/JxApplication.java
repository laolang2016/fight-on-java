package com.laolang.jx;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@EnableTransactionManagement
@SpringBootApplication
public class JxApplication {

    public static void main(String[] args) {
        SpringApplication.run(JxApplication.class);
        log.info("jx is running...");
    }
}
