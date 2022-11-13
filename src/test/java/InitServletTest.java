import org.junit.jupiter.api.Test;
import ru.javarush.quest.QuestServlet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.javarush.quest.factory.FactoryRepository;
import ru.javarush.quest.repository.AnswerRepository;
import ru.javarush.quest.repository.RepositoryEn;
import ru.javarush.quest.repository.RepositoryRu;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.net.http.HttpRequest;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InitServletTest {

  /*  public InitServlet initServlet;
    public HttpServletRequest request;
    public HttpServletResponse response;
    public ServletContext servletContext;
    public RequestDispatcher requestDispatcher;
    public HttpSession currentSession;
    public AnswerRepository answerRepository;
    FactoryRepository factoryRepository;
    int countLevel;
    String username;
    String language;
    String positiveButton;
    String negativeButton;
    String winMessage;
    String lossMessage;
    int gamesquanity;
    boolean isGameOver;
    String message;
    String answer;

    private final String path = "/init-servlet";
    private final String path2 = "/questquest.jsp";
    private final String path3 = "/";
    String path4 = "/index.jsp";

    @BeforeEach
    public void init() throws ServletException {
        this.initServlet = new InitServlet();
        initServlet.init();
//        initServlet.init();
//        answerRepository = new RepositoryRu();
//        this.factoryRepository = new FactoryRepository();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        servletContext = mock(ServletContext.class);
//        lenient().when(request.getServletContext()).thenReturn(servletContext);
        requestDispatcher = mock(RequestDispatcher.class);
        currentSession = mock(HttpSession.class);
        lenient().when(request.getSession(true)).thenReturn(currentSession);
//        lenient().when(servletContext.getRequestDispatcher("/init-servlet")).thenReturn(requestDispatcher);
    }

    @ParameterizedTest
    @CsvSource({
            "RU, RepositoryRu.class",
            "EN, RepositoryEn.class",
    })
    public void downloadDataByLanguageTest(String language) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//        initServlet.downloadDataByLanguage(language);
        Class clazz = initServlet.getClass();
        Method downloadData = clazz.getDeclaredMethod("downloadDataByLanguage", String.class);
        downloadData.setAccessible(true);
        downloadData.invoke(initServlet, language);
        if (language.equalsIgnoreCase("RU")) {
            Assertions.assertEquals(initServlet.answerRepository.getClass(), RepositoryRu.class);
        } else {
            Assertions.assertEquals(initServlet.answerRepository.getClass(), RepositoryEn.class);
        }
    }

    void dataTransferPerSessionTest(HttpServletRequest req) throws UnknownHostException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class clazz = InitServlet.class;
        Method dataTransfer = clazz.getDeclaredMethod("dataTransferPerSession");
        dataTransfer.setAccessible(true);
        dataTransfer.invoke(req);
    }


    @Test
 *//*   @ParameterizedTest
    @CsvSource({
            "/start?formname=prologue&username=svsv&choiceLanguage=RU, RepositoryRu.class",
    })*//*

    public boolean startQuestTest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        when(request.getSession(true)).thenReturn(currentSession);
//        when(request.getRequestURI()).thenReturn("/start");
        System.out.println("!!!!!!!!!!!!!!!!!!!!");
        lenient().when(servletContext.getRequestDispatcher("/start")).thenReturn(requestDispatcher);
        when(request.getParameter("username")).thenReturn("user");
        System.out.println(request.getParameter("username") + "!!!!!!!!!!!!!!!");
        when(request.getParameter("choiceLanguage")).thenReturn("RU");
        initServlet.startQuest(request, response);
//        initServlet.downloadDataByLanguage(language);
//        Assertions.assertEquals(answerRepository.getClass(), );
        Assertions.assertEquals(initServlet.answerRepository.getClass(), RepositoryRu.class);
        System.out.println(language);
//        System.out.println(answerRepository.getWinMessage());
        return false;
    }

    @Test
*//*    @ParameterizedTest
       })*//*
    public void PostTest(*//*String formName, String questionResponseBranch, String pathToJSP*//*) throws ServletException, IOException {
        initServlet = new InitServlet();
        initServlet.init();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        servletContext = mock(ServletContext.class);
        answerRepository = new RepositoryRu();
        currentSession = mock(HttpSession.class);
        when(request.getSession(true)).thenReturn(currentSession);

        *//* when(request.getParameter("formname")).thenReturn(String.valueOf("prologue"));*//*
        System.out.println(servletContext.getRequestDispatcher("/init-servlet"));
        System.out.println(servletContext.getRequestDispatcher("/mainPage"));
      *//*  initServlet = new InitServlet();
        request = mock(HttpServletRequest.class);
        servletContext = mock(ServletContext.class);
        currentSession = mock(HttpSession.class);
        response = mock(HttpServletResponse.class);*//*
//        currentSession.setAttribute("formname", "prologue");
        System.out.println(currentSession.getAttribute("formname") + "!!!!!!!!!!!!!!!");

*//*
        System.out.println(request.getParameter("formname"));
        System.out.println(request.getContextPath());
        System.out.println(request.getParameter("username"));
        System.out.println(servletContext.getContext("formname"));
        System.out.println(request.getParameter("choiceLanguage"));
        System.out.println(request);*//*
    }

    @Test
    public void doPostTest() throws ServletException, IOException, URISyntaxException {
        initServlet = new InitServlet();
        initServlet.init();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        servletContext = mock(ServletContext.class);
        answerRepository = new RepositoryRu();
        currentSession = mock(HttpSession.class);
//        when(request.getSession(true)).thenReturn(currentSession);
//        when(request.getParameter("formname")).thenReturn(String.valueOf(true));
        when(request.getParameter("formname")).thenReturn(String.valueOf("prologue"));
//        when(request.getParameter("blank")).thenReturn(String.valueOf(false));
        when(request.getParameter("username")).thenReturn("user");
//        when(request.getParameter("choiceLanguage")).thenReturn(String.valueOf("RU"));
//        String username = request.getParameter("formname");
//        currentSession.setAttribute("username", request.getParameter("username"));
        System.out.println(request.getParameter("formname") + "!!!!!!!!!!!!!!!");
        System.out.println(request.getParameter("username") + "!!!!!!!!!!!!!!!");

//        lenient().when(request.getServletContext()).thenReturn(servletContext);
        //        lenient().when(request.getSession(true)).thenReturn(currentSession);
//        lenient().wh
//        initServlet.doPost(request , response);
        System.out.println(request.getRequestURI());
        System.out.println(request.getParameter("formname"));

    }*/
}
