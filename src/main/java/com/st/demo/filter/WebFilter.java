package com.st.demo.filter;


import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import com.st.demo.util.*;

@Component
@javax.servlet.annotation.WebFilter(urlPatterns = "/*",filterName = "webFilter")
public class WebFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            System.out.println("webFilter");
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            TraceIdUtils.clear();
        }
    }

    @Override
    public void destroy() {

    }
}
