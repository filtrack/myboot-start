package com.starter.app.controller;

import com.starter.app.aspectj.annotation.Log;
import com.starter.app.aspectj.annotation.RateLimiter;
import com.starter.app.domain.dto.LaunageDTO;
import com.starter.app.domain.result.CommonResult;
import com.starter.app.service.LaunageService;
import com.starter.app.domain.vo.LaunageVO;
import com.starter.app.domain.vo.PageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@Valid
@RestController
@RequestMapping("/lan")
public class LaunageController {

    final LaunageService launageService;

    public LaunageController(LaunageService launageService) {
        this.launageService = launageService;
    }

    /**
     * 专栏列表
     * @param dto
     * @return CommonResult
     */
    @Log("语言列表")
    @RateLimiter(time = 5,count = 3,limitType = RateLimiter.LimitType.IP)
    @PostMapping("/list")
    public Object list(LaunageDTO dto) {
        PageVO<LaunageVO> pageResult =  launageService.queryPage(dto);
        return CommonResult.success(pageResult);
    }






}
