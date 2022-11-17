package ru.javarush.quest.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@WebFilter/*(urlPatterns = "/start")*/
public class UserFilter implements Filter {

    public UserFilter() {
    }

    private FilterConfig filterConfig;
    private static final Logger logger = LoggerFactory.getLogger(UserFilter.class);

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        filterConfig = fConfig;

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        logger.error("Filter!");
        if (usernameCheck(req, resp)) {
            return;
        }
        chain.doFilter(req, resp);
    }

    /*Здесь можно организовать валидацию "username"*/
    private boolean usernameCheck(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
        ServletContext ctx = filterConfig.getServletContext();
        if (req.getParameter("username").isBlank() || req.getParameter("username").isEmpty()) {
            req.setAttribute("blank", true);
            RequestDispatcher dispatcher = ctx.getRequestDispatcher("/");
            dispatcher.forward(req, resp);
            return true;
        }
        if (req.getParameter("username").contains("*") || req.getParameter("username").contains("\\")||
                req.getParameter("username").contains("/")) {
            req.setAttribute("blank", true);
            RequestDispatcher dispatcher = ctx.getRequestDispatcher("/");
            dispatcher.forward(req, resp);
            return true;
        }
        return false;
    }

}
