package com.starter.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class ReqPage {

    @JsonIgnore
    private long page = 1;

    @JsonIgnore
    private long Size = 10;

}
