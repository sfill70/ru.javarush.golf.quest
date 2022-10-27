package ru.javarush.quest.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@WebFilter(urlPatterns ="/init-servlet")
public class XXSFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Filter!!!!!!!!!!!!!");
        chain.doFilter(new XSSRequestWrapper((HttpServletRequest) request), response);
    }
}
