package ru.javarush.quest.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@WebFilter(urlPatterns = "/start")
public class XXSFilter implements Filter {

    public XXSFilter() {
    }

    private FilterConfig filterConfig;
    private static final Logger logger = LoggerFactory.getLogger(XXSFilter.class);

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        filterConfig = fConfig;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        logger.error("Filter!!!!!!!!!!!!!");
        if (usernameCheck(req, resp)) {
            return;
        }
//                chain.doFilter(new XSSRequestWrapper((HttpServletRequest) req), resp);
        chain.doFilter(req, resp);
    }

    private boolean usernameCheck(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("username").isBlank() || req.getParameter("username").isEmpty()) {
            req.setAttribute("blank", true);
            ServletContext ctx = filterConfig.getServletContext();
            RequestDispatcher dispatcher = ctx.getRequestDispatcher("/");
            dispatcher.forward(req, resp);
            return true;
        }
        if (req.getParameter("username").contains("*") || req.getParameter("username").contains("\\")) {
            ServletContext ctx = filterConfig.getServletContext();
            RequestDispatcher dispatcher = ctx.getRequestDispatcher("/");
            dispatcher.forward(req, resp);
            return true;
        }
        return false;
    }


}
