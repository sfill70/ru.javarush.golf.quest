package ru.javarush.quest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.javarush.quest.filter.UserFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.mockito.Mockito.*;

import static org.mockito.Mockito.mock;

public class UserFilterTest {

    private UserFilter userFilter;
    FilterConfig filterConfig;

    @BeforeEach
    public void init() throws ServletException {
        this.userFilter = new UserFilter();
        userFilter.init(filterConfig);
    }

    @Test
    public void usernameCheckTest() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("username")).thenReturn("name");
        boolean check = userFilter.usernameCheck(request, response);
        Assertions.assertFalse(check);
    }

    @ParameterizedTest
    @CsvSource({
            "nam*e", "n/ame",
    })
    public void IIsCheckName(String name){
        boolean check = userFilter.IsCheckName(name);
        Assertions.assertTrue(check);
    }

   /* @Test
    public void checkNoValidTest() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
//        ServletContext ctx = filterConfig.getServletContext();
        userFilter.checkNoValid(request,response);
        when(request.getRequestDispatcher("/")).thenReturn(dispatcher);
        *//*RequestDispatcher dispatcher = ctx.getRequestDispatcher("/");
        dispatcher.forward(req, resp);*//*
    }*/
}
