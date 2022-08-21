<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${mapperPackage!""}.${entityMapper?cap_first!""}">

    <resultMap id="BaseResultMap" type="${entityPackage!""}.${entityName?cap_first!""}">
        <#list table.columnInfoList as field>
        <#if field.tabColumnName?? && field.tabColumnName==table.primaryKey>
        <id property="${field.lowerColumnName!""}" column="${field.tabColumnName!""}" jdbcType="${field.tableColumnType?upper_case!""}"/>
        </#if>
        </#list>
        <#list table.columnInfoList as field>
        <#if field.tabColumnName?? && field.tabColumnName!=table.primaryKey>
        <result property="${field.lowerColumnName!""}" column="${field.tabColumnName!""}"/>
        </#if>
        </#list>
    </resultMap>

    <sql id="Base_Column_List">
        <#list table.columnInfoList as field>${field.tabColumnName!""}<#if (field_index+1)<(table.columnInfoList?size)>,</#if></#list>
    </sql>


</mapper>

