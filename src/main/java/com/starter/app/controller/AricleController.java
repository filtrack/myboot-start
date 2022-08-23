package com.starter.app.controller;

import com.starter.app.aspectj.annotation.Log;
import com.starter.app.aspectj.annotation.RateLimiter;
import com.starter.app.domain.dto.AricleDTO;
import com.starter.app.domain.result.CommonResult;
import com.starter.app.service.AricleService;
import com.starter.app.domain.vo.AricleVO;
import com.starter.app.domain.vo.PageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Valid
@RestController
@RequestMapping("/aricle")
public class AricleController {

    final AricleService aricleService;

    public AricleController(AricleService aricleService) {
        this.aricleService = aricleService;
    }



    /**
     * 保存文章
     * @param dto
     * @return CommonResult
     */
    @Log("保存文章")
    @RateLimiter(time = 5,count = 3,limitType = RateLimiter.LimitType.IP)
    @PostMapping("/add_aricle")
    public Object addAricle(AricleDTO dto) {
        Boolean bool =  aricleService.addAricle(dto);
        return CommonResult.success(bool);
    }

    /**
     * 文章详情
     * @param  id 文章ID
     * @return CommonResult
     */
    @Log("文章详情")
    @RateLimiter(time = 5,count = 3,limitType = RateLimiter.LimitType.IP)
    @GetMapping("/{id}")
    public Object user(@PathVariable("id") Long id){
        AricleVO aricleVo = aricleService.findById(id);
        Assert.notNull(aricleVo, "文章不存在！");
        return CommonResult.success(aricleVo);
    }

    /**
     * 发布文章
     * @param dto
     * @return CommonResult
     */
    @Log("发布文章")
    @RateLimiter(time = 5,count = 3,limitType = RateLimiter.LimitType.IP)
    @PostMapping("/publish_aricle")
    public Object publishAricle(AricleDTO dto) {
        Boolean bool =  aricleService.publishAricle(dto);
        return CommonResult.success(bool);
    }



    /**
     * 文章列表
     * @param dto
     * @return CommonResult
     */
    @Log("文章列表")
    @RateLimiter(time = 5,count = 3,limitType = RateLimiter.LimitType.IP)
    @PostMapping("/list")
    public Object list(AricleDTO dto) {
        PageVO<AricleVO> pageResult =  aricleService.queryPage(dto);
        return CommonResult.success(pageResult);
    }



}
