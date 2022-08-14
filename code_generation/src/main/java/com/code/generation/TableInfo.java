package com.code.generation;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TableInfo {

    private String primaryKey;
    private String tableName;
    private String lowerClassName;
    private String upClassName;
    private String classComment;

    private List<ColumnInfo> columnInfoList;

    public TableInfo(String primaryKey,String tableName, String lowerClassName, String upClassName,String classComment, List<ColumnInfo> columnInfoList) {
        this.primaryKey = primaryKey;
        this.tableName = tableName;
        this.lowerClassName = lowerClassName;
        this.upClassName = upClassName;
        this.classComment = classComment;
        this.columnInfoList = columnInfoList;
    }
}
