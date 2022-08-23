package com.starter.app.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.starter.app.domain.entity.Order;
import org.apache.ibatis.annotations.Mapper;

/**
* author HJW
* description 
* date 2022-08-23
*/
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}



