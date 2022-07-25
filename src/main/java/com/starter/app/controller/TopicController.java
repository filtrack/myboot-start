package com.starter.app.controller;

import com.starter.app.annotation.Log;
import com.starter.app.annotation.RateLimiter;
import com.starter.app.dto.TopicDto;
import com.starter.app.result.CommonResult;
import com.starter.app.service.TopicService;
import com.starter.app.vo.PageVo;
import com.starter.app.vo.TopicVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@Valid
@RestController
@RequestMapping("/topic")
public class TopicController {

    final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    /**
     * 专栏列表
     * @param topicDto
     * @return CommonResult
     */
    @Log("专栏列表")
    @RateLimiter(time = 5,count = 3,limitType = RateLimiter.LimitType.IP)
    @PostMapping("/list")
    public Object list(TopicDto topicDto) {
        PageVo<TopicVo> pageResult =  topicService.queryPage(topicDto);
        return CommonResult.success(pageResult);
    }






}
