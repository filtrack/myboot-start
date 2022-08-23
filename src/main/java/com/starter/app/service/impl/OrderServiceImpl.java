package com.starter.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.starter.app.domain.entity.Order;
import com.starter.app.mapper.OrderMapper;
import com.starter.app.service.OrderService;
import org.springframework.stereotype.Service;

/**
* author HJW
* description 
* date 2022-08-23
*/
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>  implements OrderService {

    final OrderMapper orderMapper;
    public OrderServiceImpl(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

}

