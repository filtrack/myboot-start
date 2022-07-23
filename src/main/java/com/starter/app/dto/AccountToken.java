package com.starter.app.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AccountToken implements Serializable {
    private String tokenId;
    private Integer accountId;
    private String account;
    private String password;
    private String ip;
    //客户端
    private String agent;
    // 授权时间
    private Long issuedAt;
    // 过期时间
    private Long expiresAt;
    // 是否记住我
    private boolean remember;



}
