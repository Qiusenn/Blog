package com.qiusen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.qiusen.mapper")
@EnableScheduling
public class QiuSenBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(QiuSenBlogApplication.class, args);
    }
}
