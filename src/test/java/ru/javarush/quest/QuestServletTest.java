package ru.javarush.quest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.javarush.quest.logics.RepositoryRequestHandler;
import ru.javarush.quest.logics.RepositorySelection;
import ru.javarush.quest.repository.AnswerRepository;
import ru.javarush.quest.repository.RepositoryEn;
import ru.javarush.quest.repository.RepositoryRu;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.UnknownHostException;

import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
@WebServlet
public class QuestServletTest {

    public QuestServlet questServlet;
    public HttpServletRequest request;
    public HttpServletResponse response;
    public ServletContext servletContext;
    public RequestDispatcher requestDispatcher;
    public HttpSession currentSession;
    RepositoryRequestHandler repositoryRequestHandler;


    private final String path = "/init-servlet";
    private final String path2 = "/quest.jsp";
    private final String path3 = "/";
    String path4 = "/index.jsp";

    @BeforeEach
    public void init() throws ServletException {
        this.questServlet = new QuestServlet();
        repositoryRequestHandler = new RepositoryRequestHandler("RU");
        questServlet.init();
//        initServlet.init();
//        answerRepository = new RepositoryRu();
//        this.factoryRepository = new FactoryRepository();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        servletContext = mock(ServletContext.class);
//        lenient().when(request.getServletContext()).thenReturn(servletContext);
        requestDispatcher = mock(RequestDispatcher.class);
//        currentSession = mock(HttpSession.class);
//        lenient().when(request.getSession(true)).thenReturn(currentSession);
//        lenient().when(servletContext.getRequestDispatcher("/init-servlet")).thenReturn(requestDispatcher);
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

    /* @ParameterizedTest
     @CsvSource({
             "name, 5, RU" ,
             "user, 2, EN",
     })*/
    /*Как добавить сессию в тест????*/
    @Test
    void dataTransferPerSessionTest(/*String username, int gamesquanity, String language*/) throws UnknownHostException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
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
    }

}
