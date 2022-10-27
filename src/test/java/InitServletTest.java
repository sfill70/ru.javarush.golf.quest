import org.junit.jupiter.api.Test;
import ru.javarush.quest.InitServlet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.javarush.quest.factory.FactoryRepository;
import ru.javarush.quest.repository.AnswerRepository;
import ru.javarush.quest.repository.RepositoryRu;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InitServletTest {

    public InitServlet initServlet;
    public HttpServletRequest request;
    public HttpServletResponse response;
    public ServletContext servletContext;
    public RequestDispatcher requestDispatcher;
    public HttpSession currentSession;
    public AnswerRepository answerRepository;
    FactoryRepository factoryRepository;

    private final String path = "/init-servlet";
    private final String path2 = "/mainPage.jsp";
    private final String path3 = "/";
    String path4 = "/index.jsp";

    @BeforeEach
    public void init() throws ServletException {
        initServlet = new InitServlet();
//        initServlet.init();
        answerRepository = new RepositoryRu();
        factoryRepository = new FactoryRepository();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        servletContext = mock(ServletContext.class);
        lenient().when(request.getServletContext()).thenReturn(servletContext);
        requestDispatcher = mock(RequestDispatcher.class);
        currentSession = mock(HttpSession.class);
        lenient().when(request.getSession(true)).thenReturn(currentSession);
        lenient().when(servletContext.getRequestDispatcher("/init-servlet")).thenReturn(requestDispatcher);
    }

        @Test
/*    @ParameterizedTest
    @CsvSource({
            "/init-servlet, prologue",
            "init-servlet/formname, prologue",
            "formname, prologue",
            "init-servlet, prologue",
            "prologue, /init.jsp",
            "prologue, /mainPage.jsp",
            "prologue, /init-servlet",
            "prologue, init-servlet",
            "prologue, /",
            "choice, /init.jsp",
            "choiceLanguage, /init.jsp",
            "choiceLanguage, /mainPage.jsp",
            "choiceLanguage, /init-servlet",
            "endgame,  /mainPage.jsp",
            "endgame,  /init-servlet",
            "endgame,  init-servlet",
            "choice, /mainPage.jsp",
            "choice, formname"
    })*/
    public void PostTest(/*String formName, String questionResponseBranch, String pathToJSP*/) throws ServletException, IOException {
        initServlet = new InitServlet();
        initServlet.init();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        servletContext = mock(ServletContext.class);
        answerRepository = new RepositoryRu();
        currentSession = mock(HttpSession.class);
        when(request.getSession(true)).thenReturn(currentSession);

       /* when(request.getParameter("formname")).thenReturn(String.valueOf("prologue"));*/
        System.out.println(servletContext.getRequestDispatcher("/init-servlet"));
        System.out.println(servletContext.getRequestDispatcher("/mainPage"));
      /*  initServlet = new InitServlet();
        request = mock(HttpServletRequest.class);
        servletContext = mock(ServletContext.class);
        currentSession = mock(HttpSession.class);
        response = mock(HttpServletResponse.class);*/
//        currentSession.setAttribute("formname", "prologue");
        System.out.println(currentSession.getAttribute("formname")+"!!!!!!!!!!!!!!!");

/*
        System.out.println(request.getParameter("formname"));
        System.out.println(request.getContextPath());
        System.out.println(request.getParameter("username"));
        System.out.println(servletContext.getContext("formname"));
        System.out.println(request.getParameter("choiceLanguage"));
        System.out.println(request);*/
    }

    @Test
    public void   doPostTest () throws ServletException, IOException, URISyntaxException {
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
        when(request.getParameter("username")).thenReturn(String.valueOf("user"));
//        when(request.getParameter("choiceLanguage")).thenReturn(String.valueOf("RU"));
//        String username = request.getParameter("formname");
//        currentSession.setAttribute("username", request.getParameter("username"));
        System.out.println(request.getParameter("formname")+"!!!!!!!!!!!!!!!");
        System.out.println(request.getParameter("username")+"!!!!!!!!!!!!!!!");

//        lenient().when(request.getServletContext()).thenReturn(servletContext);
       //        lenient().when(request.getSession(true)).thenReturn(currentSession);
//        lenient().wh
//        initServlet.doPost(request , response);
        System.out.println(request.getRequestURI());
        System.out.println(request.getParameter("formname"));

    }
}
