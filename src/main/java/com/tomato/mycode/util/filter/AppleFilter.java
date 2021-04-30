package com.tomato.mycode.util.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "AppleFilter", urlPatterns = "/*")
public class AppleFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(AppleFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        logger.info("AppleFilter::doFilter:: this is apple filter");
        //放行
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        logger.info("AppleFilter::destroy:: this is apple filter");
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        //todo
    }
}
