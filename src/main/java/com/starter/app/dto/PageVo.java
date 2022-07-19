package com.starter.app.dto;

import lombok.Data;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class PageVo<T> implements Serializable {

    private long total = 0;
    /**
     * 每页显示条数，默认 10
     */
    private long size = 10;

    /**
     * 当前页
     */
    private long current = 1;

    /**
     * 数据
     */
    private List<T> records;

}
