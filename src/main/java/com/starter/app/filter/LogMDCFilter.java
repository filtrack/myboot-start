package com.starter.app.filter;

import cn.hutool.core.util.IdUtil;
import org.slf4j.MDC;

import javax.servlet.*;
import java.io.IOException;

public class LogMDCFilter implements Filter {

    private static final String UNIQUE_ID_NAME = "traceId";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        MDC.put(UNIQUE_ID_NAME, IdUtil.getSnowflake().nextIdStr());
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            MDC.remove(UNIQUE_ID_NAME);
        }
    }
}
