package com.starter.app.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
@Data
public class GlobalConst {

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expire}")
    private Long expire;
}
