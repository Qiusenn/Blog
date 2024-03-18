package com.qiusen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.qiusen.mapper")
public class QiuSenBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(QiuSenBlogApplication.class, args);
    }
}
