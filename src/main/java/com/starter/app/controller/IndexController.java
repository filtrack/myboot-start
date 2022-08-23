package com.starter.app.controller;

import com.starter.app.aspectj.annotation.Log;
import com.starter.app.aspectj.annotation.RateLimiter;
import com.starter.app.domain.dto.AricleDTO;
import com.starter.app.domain.entity.Order;
import com.starter.app.domain.result.CommonResult;
import com.starter.app.domain.vo.AricleVO;
import com.starter.app.domain.vo.PageVO;
import com.starter.app.service.AricleService;
import com.starter.app.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import javax.validation.Valid;
import java.sql.Connection;
import java.sql.SQLException;

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


}
