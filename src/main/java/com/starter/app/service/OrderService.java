package com.starter.app.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.starter.app.domain.dto.OrderDTO;
import com.starter.app.domain.entity.Order;
import com.starter.app.domain.vo.OrderVO;
import com.starter.app.domain.vo.PageVO;

/**
* author HJW
* description 
* date 2022-08-23
*/
public interface OrderService extends IService<Order>{

    PageVO<OrderVO> queryPage(OrderDTO dto);

}



