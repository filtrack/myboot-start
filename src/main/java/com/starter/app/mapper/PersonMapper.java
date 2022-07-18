package com.starter.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.starter.app.entity.Person;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PersonMapper extends BaseMapper<Person> {

}
