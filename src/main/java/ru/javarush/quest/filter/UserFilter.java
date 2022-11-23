package ru.javarush.quest.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

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
        logger.debug("Filter!");
        if (usernameCheck(req, resp)) {
            return;
        }
        chain.doFilter(req, resp);
    }

    public boolean usernameCheck(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("username");
        if (IsCheckName(name)) {
            checkNoValid(req, resp);
            return true;
        }
        return false;
    }

    public boolean IsCheckName(String name) {
        return name.isEmpty() || name.isBlank() || name.contains("*") || name.contains("\\") || name.contains("/");
    }

    public void checkNoValid(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
        ServletContext ctx = filterConfig.getServletContext();
        req.setAttribute("blank", true);
        RequestDispatcher dispatcher = ctx.getRequestDispatcher("/");
        dispatcher.forward(req, resp);
    }


}
