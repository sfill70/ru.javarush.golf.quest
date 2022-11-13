package ru.javarush.quest;

import ru.javarush.quest.factory.FactoryRepository;
import ru.javarush.quest.repository.*;

import javax.servlet.DispatcherType;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Inet4Address;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLDecoder;
import java.net.UnknownHostException;


@WebServlet(name = "InitServlet"/*, value = "/init-servlet/*"*/)
public class InitServlet extends HttpServlet {
    HttpSession currentSession;
    public AnswerRepository answerRepository;
    FactoryRepository factoryRepository;
    int countLevel;
    boolean isGameOver;
    String message;
    String answer;
    private static final Logger logger = LoggerFactory.getLogger(InitServlet.class);


    @Override
    public void init() throws ServletException {
        super.init();
        try {
            logger.debug(URLDecoder.decode(InitServlet.class.getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .getPath(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        this.factoryRepository = new FactoryRepository();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        String projectPathOut = loader.getResource("").getPath();
        String[] arrayProjectPath = projectPathOut.split("/");
        String projectPath = "";
        for (String st : arrayProjectPath) {
            if (!st.isEmpty() || !st.isBlank()) {
                projectPath = projectPath + st + File.separator;
            }
            if (st.equals("ru.javarush.golf.quest")) {
                break;
            }
        }
        logger.debug(projectPath);
        logger.debug(System.getProperty("user.dir"));
    }

    @Override

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("Service");
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
        if (uri.equals("/start")) {
            /*if (usernameCheck(req, resp, currentSession)) {
                return;
            }*/
            startQuest(req);
        } else if (uri.equals("/init-servlet")) {
//            logicQuest(req, resp);
            if (logicQuest(req, resp)) {
                return;
            }
        }

        doPost(req, resp);
    }


    /*не используется*/
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }


    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("Post");
        logger.error(message);
        logger.debug(String.valueOf(currentSession.hashCode()));
        if (isGameOver) {
            logger.error(message + "  isGameOver");
            req.setAttribute("message", message);
            getServletContext().getRequestDispatcher("/loss.jsp").forward(req, resp);
//            resp.sendRedirect(req.getContextPath() + "/loss.jsp");
            return;
        }
        logger.error(message + "  NotGameOver");
        countLevel++;
        req.setAttribute("countLevel", countLevel);
        req.setAttribute("answer", answer);
        req.setAttribute("message", message);
        getServletContext().getRequestDispatcher("/quest.jsp").forward(req, resp);
//        resp.sendRedirect(req.getContextPath() + "/quest.jsp");
    }

    public void startQuest(HttpServletRequest req) throws IOException {
        countLevel = 0;
        isGameOver = false;
        String username = req.getParameter("username");
        int gamesquanity = PlayerRepository.getPlayerCount(username);
        String language = req.getParameter("choiceLanguage");
        answerRepository = getAnswerRepository(language);
        dataTransferPerSession(username, gamesquanity, language);
        getDataFromRepository(true);

//        return false;
    }


    private AnswerRepository getAnswerRepository(String language) {
        AnswerRepository repository = factoryRepository.creatRepository(language);
        logger.debug(repository.getClass() + " downloadDataByLanguage");
        return repository;
    }

    private void dataTransferPerSession(String username, int gamesquanity, String language) throws UnknownHostException {
//        currentSession = req.getSession(true);
        currentSession.setAttribute("username", username);
        currentSession.setAttribute("gamesquanity", gamesquanity);
        currentSession.setAttribute("language", language);
//        currentSession.setAttribute("negativeButton", negativeButton);
//        currentSession.setAttribute("positiveButton", positiveButton);
        currentSession.setAttribute("ip", Inet4Address.getLocalHost().getHostAddress());
//        currentSession.setAttribute("winMessage", winMessage);
//        currentSession.setAttribute("lossMessage", lossMessage);
//        currentSession.setAttribute("statistic", statistic);
        currentSession.setAttribute("entityInterface", answerRepository.getEntityInterface());
//        req.setAttribute("entityInterface", entityInterface);
//        req.setAttribute("loss", lossMessage);
//        currentSession.setAttribute("blank_statistic", true);
    }

    private void getStartDataFromRepository() {
        message = answerRepository.getStartMessage();
        isGameOver = false;
        logger.error(String.valueOf(isGameOver) + " / " + message + " / " + countLevel);
    }

    private void getDataFromRepository(boolean positiveAnswer) {
        answer = String.valueOf(positiveAnswer);
        if (positiveAnswer) {
            isGameOver = answerRepository.getLevelPositiveIsGameOver(countLevel);
            message = answerRepository.getLevelPositiveMessage(countLevel);
        } else {
            isGameOver = answerRepository.getLevelNegativeIsGameOver(countLevel);
            message = answerRepository.getLevelNegativeMessage(countLevel);
        }
    }

    private boolean logicQuest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (countLevel == answerRepository.getSize()) {
            resp.sendRedirect(req.getContextPath() + "/victory.jsp");
            return true;
        }
        String radioButtonChoice = req.getParameter("choice");
        if (radioButtonChoice.equalsIgnoreCase("positiveAnswer")) {
            getDataFromRepository(true);
        } else {
            getDataFromRepository(false);
        }
        return false;
    }

    @Override
    public void destroy() {
    }

}
