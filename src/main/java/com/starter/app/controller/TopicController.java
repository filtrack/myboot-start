package com.starter.app.controller;

import com.starter.app.aspectj.annotation.Log;
import com.starter.app.aspectj.annotation.RateLimiter;
import com.starter.app.domain.dto.TopicDTO;
import com.starter.app.domain.result.CommonResult;
import com.starter.app.service.TopicService;
import com.starter.app.domain.vo.PageVO;
import com.starter.app.domain.vo.TopicVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
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
    public Object list(TopicDTO topicDto) {
        PageVO<TopicVO> pageResult =  topicService.queryPage(topicDto);
        return CommonResult.success(pageResult);
    }






}
