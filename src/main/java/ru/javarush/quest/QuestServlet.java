package ru.javarush.quest;

import ru.javarush.quest.factory.FactoryRepository;
import ru.javarush.quest.repository.AnswerRepository;

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
import ru.javarush.quest.repository.PlayerRepository;

import java.net.URLDecoder;
import java.net.UnknownHostException;

@WebServlet/*(name = "QuestServlet", value = {"/quest-servlet","/start"})*/
public class QuestServlet extends HttpServlet {
    private HttpSession currentSession;
    private AnswerRepository answerRepository;
    private FactoryRepository factoryRepository;
    boolean isGameOver;
    String message;
    String answer;
    private static final Logger logger = LoggerFactory.getLogger(QuestServlet.class);


    @Override
    public void init() throws ServletException {
        super.init();
        try {
            logger.debug(URLDecoder.decode(QuestServlet.class.getProtectionDomain()
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
        logger.error("Service");
        currentSession = req.getSession(true);

        String httpMethod = req.getMethod();
        if (httpMethod.equalsIgnoreCase("GET")) {
            doGet(req, resp);
            return;
        }

       /* String uri = req.getRequestURI();
        logger.debug(uri);
        if (httpMethod.equalsIgnoreCase("GET")) {
            doGet(req, resp);
            return;
        }
        if (uri.equals("/start")) {
            startQuest(req);
        } else if (uri.equals("/quest-servlet")) {
            if (logicQuest(req, resp)) {
                return;
            }
        }*/

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
        logger.error(String.valueOf(currentSession.hashCode()));
        String uri = req.getRequestURI();
        logger.debug(uri);
        if (uri.equals("/start")) {
            startQuest(req);
        } else if (uri.equals("/quest-servlet")) {
            if (logicQuest(req, resp)) {
                return;
            }
        }
        if (isGameOver) {
            logger.error(message + "  isGameOver");
            currentSession.setAttribute("message", message);
            resp.sendRedirect(req.getContextPath() + "/loss.jsp");
            return;
        }
//        transferringDataToRequest(req);
        getServletContext().getRequestDispatcher("/quest.jsp").forward(req, resp);
    }
/*

    private void transferringDataToRequest(HttpServletRequest req, int countLevel) {
        countLevel++;
        logger.error(String.valueOf(countLevel) + " transferringDataToRequest");
        currentSession.setAttribute("countLevel", countLevel);
        req.setAttribute("answer", answer);
        req.setAttribute("message", message);
    }
*/

    public void startQuest(HttpServletRequest req) throws IOException {
        int countLevel = 0;
        isGameOver = false;
        String username = req.getParameter("username");
        int gamesquanity = PlayerRepository.getPlayerCount(username);
        String language = req.getParameter("choiceLanguage");
        answerRepository = getAnswerRepository(language);
        dataTransferPerSession(username, gamesquanity, language);
        getDataFromRepository(true, countLevel);
        transferringDataToRequest(req, countLevel);

//        return false;
    }


    private AnswerRepository getAnswerRepository(String language) {
        AnswerRepository repository = factoryRepository.creatRepository(language);
        logger.debug(repository.getClass() + " downloadDataByLanguage");
        return repository;
    }

    private void dataTransferPerSession(String username, int gamesquanity, String language) throws UnknownHostException {
        currentSession.setAttribute("username", username);
        currentSession.setAttribute("gamesquanity", gamesquanity);
        currentSession.setAttribute("language", language);
        currentSession.setAttribute("ip", Inet4Address.getLocalHost().getHostAddress());
        currentSession.setAttribute("entityInterface", answerRepository.getEntityInterface());
    }

    private void getStartDataFromRepository() {
        message = answerRepository.getStartMessage();
        isGameOver = false;
    }

    private void getDataFromRepository(boolean positiveAnswer, int countLevel) {
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
        logger.error(currentSession.getAttribute("countLevel") + "    logicQuest");
//        int countLevel = (int) currentSession.getAttribute("count");
        int countLevel = (int) currentSession.getAttribute("countLevel");
        if (countLevel == answerRepository.getSize()) {
            resp.sendRedirect(req.getContextPath() + "/victory.jsp");
            return true;
        }
        String radioButtonChoice = req.getParameter("choice");
        if (radioButtonChoice.equalsIgnoreCase("positiveAnswer")) {
            getDataFromRepository(true, countLevel);
        } else {
            getDataFromRepository(false, countLevel);
        }
        transferringDataToRequest(req, countLevel);
        return false;
    }


    private void transferringDataToRequest(HttpServletRequest req, int countLevel) {
        countLevel++;
        logger.error(String.valueOf(countLevel) + " transferringDataToRequest");
        currentSession.setAttribute("countLevel", countLevel);
        req.setAttribute("answer", answer);
        req.setAttribute("message", message);
    }


    @Override
    public void destroy() {
    }
}
