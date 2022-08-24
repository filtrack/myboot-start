package com.starter.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.starter.app.domain.dto.OrderDTO;
import com.starter.app.domain.entity.Order;
import com.starter.app.domain.ext.MyPage;
import com.starter.app.domain.vo.OrderVO;
import com.starter.app.domain.vo.PageVO;
import com.starter.app.mapper.OrderMapper;
import com.starter.app.service.OrderService;
import com.starter.app.utils.OrikaUtils;

import cn.hutool.core.util.ObjectUtil;

import java.util.LinkedHashMap;

import org.springframework.core.Ordered;
import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;
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
    @Override
    public PageVO<OrderVO> queryPage(OrderDTO dto) {
       
        LinkedHashMap<String,String> orderMap = new LinkedHashMap<>();
        orderMap.put("id","asc");

        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(ObjectUtil.isNotEmpty(dto.getUserId()),"user_id",dto.getUserId());
        Page<Order> page = MyPage.of(dto.getPage(),dto.getSize(),orderMap);
        orderMapper.selectPage(page,queryWrapper);

        return OrikaUtils.convertPageVo(page, OrderVO.class);
    }


}

