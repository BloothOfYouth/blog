package com.hjf.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan(value = "com.hjf.blog.dao") //使用MapperScan批量扫描所有的Mapper接口
@EnableTransactionManagement // 开启注解事务管理
@SpringBootApplication
public class BlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

}
