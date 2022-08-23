package com.starter.app.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageVO<T> implements Serializable {

    /**
     * 当前页
     */
    private long page = 1;

    /**
     * 每页显示条数，默认 10
     */
    private long size = 10;

    /**
     * 总页数
     */
    private long pageSize;

    /**
     * 总条数
     */
    private long total = 0;

    /**
     * 是否有下一页
     */
    private boolean hasNext;

    /**
     * 是否有上一页
     */
    private boolean hasPrevious;

    /**
     * 数据
     */
    private List<T> records;


    public PageVO(List<T> dataList, long total, long pageSize, boolean hasNext, boolean hasPrevious, long page, long size) {
        this.records = dataList;
        this.total = total;
        this.pageSize = pageSize;
        this.hasNext = hasNext;
        this.hasPrevious = hasPrevious;
        this.page = page;
        this.size = size;
    }
}
