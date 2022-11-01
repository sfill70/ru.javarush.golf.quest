package ru.javarush.quest.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@WebFilter(urlPatterns = "/*")
public class XXSFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(XXSFilter.class);
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.debug("Filter!!!!!!!!!!!!!");
        chain.doFilter(new XSSRequestWrapper((HttpServletRequest) request), response);
    }
}
