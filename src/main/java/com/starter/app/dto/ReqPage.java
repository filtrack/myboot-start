package com.starter.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

@Data
public class ReqPage implements Serializable {



    @JsonIgnore
    private long page = 1;

    @JsonIgnore
    private long Size = 10;

}
