package ru.javarush.quest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.javarush.quest.entity.EntityStatistics;
import ru.javarush.quest.logics.RepositoryRequestHandler;
import ru.javarush.quest.repository.RepositoryEn;
import ru.javarush.quest.repository.RepositoryRu;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class QuestServletTest {

    public QuestServlet questServlet;
    RepositoryRequestHandler repositoryRequestHandler;

    @BeforeEach
    public void init() throws ServletException {
        this.questServlet = new QuestServlet();
        questServlet.repositoryRequestHandler = new RepositoryRequestHandler("RU");
        questServlet.init();
    }


    @ParameterizedTest
    @CsvSource({
            "RU",
            "EN",
    })
    public void getRepositoryRequestHandlerTest(String language) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class clazz = questServlet.getClass();
        Method downloadData = clazz.getDeclaredMethod("getRepositoryRequestHandler", String.class);
        downloadData.setAccessible(true);
        RepositoryRequestHandler repositoryRequestHandler = (RepositoryRequestHandler) downloadData
                .invoke(questServlet, language);
        if (language.equalsIgnoreCase("RU")) {
            Assertions.assertEquals(repositoryRequestHandler.getAnswerRepository().getClass(), RepositoryRu.class);
        } else {
            Assertions.assertEquals(repositoryRequestHandler.getAnswerRepository().getClass(), RepositoryEn.class);
        }
    }

    @ParameterizedTest
    @CsvSource({
            "name, RU",
            "name, EN",
    })
    public void getDataFromRequestTest(String name, String language) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        /*
        servletContext = mock(ServletContext.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);*/
        HttpServletRequest request = mock(HttpServletRequest.class);
//        lenient().when(request.getSession(true)).thenReturn(session);
        when(request.getParameter("username")).thenReturn(name);
        when(request.getParameter("choiceLanguage")).thenReturn(language);
        Class clazz = questServlet.getClass();
        Method downloadData = clazz.getDeclaredMethod("getDataFromRequest", HttpServletRequest.class);
        downloadData.setAccessible(true);
        EntityStatistics entityStatistics = (EntityStatistics) downloadData
                .invoke(questServlet, request);
        if (language.equalsIgnoreCase("RU")) {
            Assertions.assertEquals(questServlet.repositoryRequestHandler.getAnswerRepository().getClass(), RepositoryRu.class);
            Assertions.assertEquals(entityStatistics, new EntityStatistics(name, 1, "RU"));
        } else {
            Assertions.assertEquals(questServlet.repositoryRequestHandler.getAnswerRepository().getClass(), RepositoryEn.class);
            Assertions.assertEquals(entityStatistics, new EntityStatistics(name, 2, "EN"));
        }
//        Assertions.assertEquals(questServlet.repositoryRequestHandler.getAnswerRepository().getClass(), RepositoryRu.class);
//        lenient().when(request.getSession(true)).thenReturn(currentSession);
    }


    @ParameterizedTest
    @CsvSource({
            "positiveAnswer",
            "negativeAnswer",
    })
    public void choiceEntityQuestTest(String answer) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        QuestServlet questServlet = new QuestServlet();
        questServlet.repositoryRequestHandler = new RepositoryRequestHandler("RU");
        Class clazz = questServlet.getClass();
        Method downloadData = clazz.getDeclaredMethod("choiceEntityQuest", HttpServletRequest.class);
        downloadData.setAccessible(true);
        if (answer.equalsIgnoreCase("positiveAnswer")) {
            when(request.getParameter("choice")).thenReturn("positiveAnswer");
            downloadData.invoke(questServlet, request);
            Assertions.assertEquals(questServlet.getRepositoryRequestHandler().getEntityQuest(), new RepositoryRu().getEntityPositiveAnswer(1));
        } else {
            when(request.getParameter("choice")).thenReturn("negativeAnswer");
            downloadData.invoke(questServlet, request);
            Assertions.assertEquals(questServlet.repositoryRequestHandler.getEntityQuest(), new RepositoryRu().getEntityNegativeAnswer(1));
        }
    }

    /* @ParameterizedTest
     @CsvSource({
             "name, 5, RU" ,
             "user, 2, EN",
     })*/
   /* @Test
    void dataTransferPerSessionTest(*//*String username, int gamesquanity, String language*//*) throws UnknownHostException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//        HttpSession currentSession = mock(HttpSession.class);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        servletContext = mock(ServletContext.class);
//        currentSession = request.getSession(true);
        lenient().when(request.getSession(true)).thenReturn(currentSession);

        currentSession.setAttribute("name", "username");
        System.out.println(servletContext.getAttribute("username"));
        System.out.println(currentSession.getAttribute("name"));
        RepositoryRequestHandler repositoryRequestHandler = new RepositoryRequestHandler("RU");
        Class clazz = QuestServlet.class;
        Method dataTransfer = clazz.getDeclaredMethod("dataTransferPerSession", String.class, int.class, String.class, RepositoryRequestHandler.class, HttpSession.class);
        dataTransfer.setAccessible(true);
//        dataTransfer.invoke(questServlet, username, gamesquanity, language, repositoryRequestHandler, currentSession);
        dataTransfer.invoke(questServlet, "username", 5, "language", repositoryRequestHandler, currentSession);
        currentSession.setAttribute("name", "username");
        System.out.println(servletContext.getAttribute("username"));
        System.out.println(currentSession.getAttribute("name"));
        Assertions.assertEquals((String) currentSession.getAttribute("username"), "username");
        Assertions.assertEquals((int) currentSession.getAttribute("gamesquanity"),5);
        Assertions.assertEquals((String) currentSession.getAttribute("language"), "language");
    }*/

}
