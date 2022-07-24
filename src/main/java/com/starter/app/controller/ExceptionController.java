package com.starter.app.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.starter.app.annotation.Log;
import com.starter.app.dto.AccountToken;
import com.starter.app.result.CommonResult;
import com.starter.app.service.RedisService;
import com.starter.app.service.UserService;
import com.starter.app.utils.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Slf4j
@RestController
public class ExceptionController {

    /**
     * 重新抛出异常
     */
    @RequestMapping("/exthrow")
    public void rethrow(HttpServletRequest request) throws Exception {
        Object obj = request.getAttribute("filter.exception");
        Exception e = (Exception) obj;
        throw e;
    }


}
