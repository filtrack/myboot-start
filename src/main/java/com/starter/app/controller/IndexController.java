package com.starter.app.controller;

import java.sql.SQLException;

import javax.validation.Valid;

import org.springframework.core.Ordered;
import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.starter.app.aspectj.annotation.Log;
import com.starter.app.aspectj.annotation.RateLimiter;
import com.starter.app.domain.dto.OrderDTO;
import com.starter.app.domain.entity.Order;
import com.starter.app.domain.result.CommonResult;
import com.starter.app.domain.vo.OrderVO;
import com.starter.app.domain.vo.PageVO;
import com.starter.app.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Valid
@RestController
@RequestMapping("/")
public class IndexController {

    final OrderService orderService;

    public IndexController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping(value = "/add")
    public Object add(@RequestBody Order order) throws SQLException {
        orderService.save(order);
        return CommonResult.success();
    }


     /**
     * 订单列表
     * @param dto
     * @return CommonResult
     */
    @Log("订单列表")
    @RateLimiter(time = 5,count = 3,limitType = RateLimiter.LimitType.IP)
    @PostMapping("/list")
    public Object list(OrderDTO dto) {
        PageVO<OrderVO> pageResult =  orderService.queryPage(dto);
        return CommonResult.success(pageResult);
    }
}
