package com.starter.app.dto;

import lombok.Data;

@Data
public class ReqPage {

    private long page = 1;
    private long Size = 10;

}
