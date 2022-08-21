package ${entityPackage!""};

import java.io.Serializable;
import java.util.*;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
* author ${author}
* description ${desc}
* date ${nowDate?string("yyyy-MM-dd")}
*/
<#if table.tableName?? && table.tableName !="">
    @TableName("${table.tableName!""}")
</#if>
@Data
public class ${table.lowerClassName?cap_first!""} implements Serializable {

private static final long serialVersionUID = 1L;

<#list table.columnInfoList as field>

    <#if field.columnComment?? && field.columnComment !="">
        //${field.columnComment!""}
    </#if>
    <#if field.tabColumnName?? && field.tabColumnName !="">
        @TableField("${field.tabColumnName!""}")
    </#if>
    private ${field.columnType} ${field.lowerColumnName};
</#list>

}