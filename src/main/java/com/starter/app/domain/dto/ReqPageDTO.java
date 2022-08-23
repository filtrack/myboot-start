package com.starter.app.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

@Data
public class ReqPageDTO implements Serializable {



    @JsonIgnore
    private long page = 1;

    @JsonIgnore
    private long Size = 10;

}
