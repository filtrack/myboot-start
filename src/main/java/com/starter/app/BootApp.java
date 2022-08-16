package com.starter.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.starter.app.mapper")
public class BootApp {

    public static void main(String[] args) {
        SpringApplication.run(BootApp.class, args);
    }

}
