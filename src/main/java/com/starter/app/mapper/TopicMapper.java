package com.starter.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.starter.app.domain.entity.Topic;
import org.apache.ibatis.annotations.Mapper;

/**
* author HJW
* description 专题
* date 2022-08-22
*/
@Mapper
public interface TopicMapper  extends BaseMapper<Topic> {

}



