package ru.javarush.quest;

import ru.javarush.quest.factory.FactoryRepository;
import ru.javarush.quest.repository.*;

import javax.servlet.DispatcherType;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.Inet4Address;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.UnknownHostException;
import java.nio.file.Path;
import java.sql.Array;
import java.util.Arrays;
import java.util.Enumeration;


@WebServlet(name = "InitServlet"/*, value = "/init-servlet/*"*/)
public class InitServlet extends HttpServlet {
    HttpSession currentSession;
    AnswerRepository answerRepository;
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
    String[] statistic;
    private static final Logger logger = LoggerFactory.getLogger(InitServlet.class);


    @Override
    public void init() throws ServletException {
        super.init();

        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        String projectPathOut = loader.getResource("").getPath();
        String[] arrayProjectPath = projectPathOut.split("/");
        String projectPath = "";
        for (String st : arrayProjectPath
        ) {
            if (!st.isEmpty() || !st.isBlank()) {
                projectPath = projectPath + st + File.separator;
            }
            if (st.equals("ru.javarush.golf.quest")) {
                break;
            }
        }
        logger.debug(projectPath);
        logger.debug(projectPathOut);
        logger.debug(Arrays.toString(arrayProjectPath));
        logger.debug(loader.getResource("").getPath());
        logger.debug(System.getProperty("user.dir"));
        factoryRepository = new FactoryRepository();
        answerRepository = factoryRepository.creatRepository("RU");
        countLevel = 0;
        /*
        ServletConfig config = this.getServletConfig();
        System.out.println(config.getInitParameterNames());

        Enumeration<String> initParameterNames = config.getInitParameterNames();
        while (initParameterNames.hasMoreElements()){
            String key = initParameterNames.nextElement();
            System.out.printf("%s: %s\n", key, config.getInitParameter(key));
        }*/
    }

    @Override

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("Service!!!!!!!!!!!!!");
        currentSession = req.getSession(true);
        if (req.getDispatcherType() == DispatcherType.ERROR) {
            resp.sendRedirect(req.getContextPath() + "/error.jsp");
        }
        String httpMethod = req.getMethod();
        String uri = req.getRequestURI();
        logger.debug(uri);
        if (httpMethod.equalsIgnoreCase("GET")) {
            doGet(req, resp);
            return;
        }
        doPost(req, resp);
    }

    /*Истользуется при переходе по ссылке Restart
    Не используется, пригодится для сохранения данных, если при restart
    чистить сессию req.getSession().invalidate();*/
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession currentSession = req.getSession(true);
        currentSession.setAttribute("ip", Inet4Address.getLocalHost().getHostAddress());
        currentSession.setAttribute("username", username);
        currentSession.setAttribute("gamesquanity", gamesquanity);
        getServletContext().getRequestDispatcher("/").forward(req, resp);
    }


    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("Post!!!!!!!!!!!!!");
//        currentSession = req.getSession(true);
        logger.debug(String.valueOf(currentSession.hashCode()));
        String formName = req.getParameter("formname");
        if (formName.equals("prologue")) {
            if (usernameCheck(req, resp, currentSession)) {
                return;
            }
            currentSession.setAttribute("blank", false);
            countLevel = 0;
            isGameOver = false;
            username = req.getParameter("username");
            gamesquanity = PlayerRepository.getPlayerCount(username);
            language = req.getParameter("choiceLanguage");

            downloadDataByLanguage();
//            dataTransferPerSession
            dataTransferPerSession(req);
        }

        String radioButtonChoice = "";
        if (formName.equalsIgnoreCase("endgame")) {
            radioButtonChoice = req.getParameter("choice");
        }
        String answer;
        String message;
        if (countLevel == answerRepository.getSize()) {
            resp.sendRedirect(req.getContextPath() + "/victory.jsp");
//            getServletContext().getRequestDispatcher("/victory.jsp").forward(req, resp);
            return;
        }
        if (radioButtonChoice.isBlank() || radioButtonChoice.equals("positiveAnswer")) {
            answer = "YES!!!";

            message = answerRepository.getLevelMessage(countLevel, true);
            isGameOver = answerRepository.isGameOver(countLevel, true);
        } else {
            answer = "NO!!!";
            message = answerRepository.getLevelMessage(countLevel, false);
            isGameOver = answerRepository.isGameOver(countLevel, false);
        }

        if (isGameOver) {
            currentSession.setAttribute("message", message);
            resp.sendRedirect(req.getContextPath() + "/loss.jsp");
//            getServletContext().getRequestDispatcher("/loss.jsp").forward(req, resp);
            return;
        }

        countLevel++;
        currentSession.setAttribute("countLevel", countLevel);
        currentSession.setAttribute("answer", answer);
        currentSession.setAttribute("countLevel", countLevel);
        currentSession.setAttribute("message", message);
        //Можно убрать
        currentSession.setAttribute("isGameOver", isGameOver);

//        getServletContext().getRequestDispatcher("/quest.jsp").forward(req, resp);
        resp.sendRedirect(req.getContextPath() + "/quest.jsp");
    }

    /*Здесь можно организовать валидацию "username"*/
    private boolean usernameCheck(HttpServletRequest req, HttpServletResponse resp, HttpSession currentSession) throws ServletException, IOException {
        if (req.getParameter("username").isBlank() || req.getParameter("username").isEmpty()) {
            currentSession.setAttribute("blank", true);
            resp.sendRedirect(req.getContextPath() + "/");
            return true;
        }
        if (req.getParameter("username").contains("*") || req.getParameter("username").contains("\\")) {
            currentSession.setAttribute("blank", true);
            resp.sendRedirect(req.getContextPath() + "/");
            return true;
        }
        return false;
    }

    private void downloadDataByLanguage() {
        answerRepository = factoryRepository.creatRepository(language);
        //Убрать после тестов
//        answerRepository = new RepositoryRu();
        positiveButton = answerRepository.getPositiveNameButton();
        negativeButton = answerRepository.getNegativeNameButton();
        winMessage = answerRepository.getWinMessage();
        lossMessage = answerRepository.getLossMessage();
        statistic = answerRepository.getStatistic();
    }

    private void dataTransferPerSession(HttpServletRequest req) throws UnknownHostException {
        currentSession = req.getSession(true);
        currentSession.setAttribute("username", username);
        currentSession.setAttribute("gamesquanity", gamesquanity);
        currentSession.setAttribute("language", language);
        currentSession.setAttribute("negativeButton", negativeButton);
        currentSession.setAttribute("positiveButton", positiveButton);
        currentSession.setAttribute("ip", Inet4Address.getLocalHost().getHostAddress());
        currentSession.setAttribute("winMessage", winMessage);
        currentSession.setAttribute("lossMessage", lossMessage);
        currentSession.setAttribute("statistic", statistic);
        currentSession.setAttribute("blank_statistic", true);
    }

    @Override
    public void destroy() {
    }

}
