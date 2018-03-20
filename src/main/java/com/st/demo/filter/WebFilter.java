package com.st.demo.filter;


import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Component
@javax.servlet.annotation.WebFilter(urlPatterns = "/*",filterName = "webFilter")
public class WebFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    }

    @Override
    public void destroy() {

    }
}
