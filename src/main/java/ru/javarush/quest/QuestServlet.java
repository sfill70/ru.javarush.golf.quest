package ru.javarush.quest;

import ru.javarush.quest.logics.RepositoryRequestHandler;
import ru.javarush.quest.logics.RepositorySelection;
import ru.javarush.quest.repository.AnswerRepository;

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
import ru.javarush.quest.repository.PlayerRepository;

import java.net.UnknownHostException;

@WebServlet(name = "QuestServlet", value = {"/quest-servlet","/start","/game"})
public class QuestServlet extends HttpServlet {
    private HttpSession currentSession;
    private AnswerRepository answerRepository;
    private RepositorySelection repositorySelection;
    boolean isGameOver;
    String message;

    RepositoryRequestHandler repositoryRequestHandler;
    private static final Logger logger = LoggerFactory.getLogger(QuestServlet.class);


    @Override
    public void init() throws ServletException {
        super.init();
        this.repositorySelection = new RepositorySelection();
        /*Нахождение директории проекта если понадобитьс*/
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
        doPost(req, resp);
    }


    /*не используется*/
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }


    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("Post");
        String uri = req.getRequestURI();
        logger.debug(uri);
        if (uri.equals("/start")) {
            startQuest(req);
        } else if (uri.equals("/quest-servlet")) {
            if (logicQuest(req, resp)) {
                return;
            }
        }
       if(repositoryRequestHandler.IsGameOver()){
           currentSession.setAttribute("message", repositoryRequestHandler.getMessage());
           resp.sendRedirect(req.getContextPath() + "/loss.jsp");
           return;
       }
        /* if (isGameOver) {
            logger.error(message + "  isGameOver");
            currentSession.setAttribute("message", message);
            resp.sendRedirect(req.getContextPath() + "/loss.jsp");
            return;
        }*/
        getServletContext().getRequestDispatcher("/quest.jsp").forward(req, resp);
    }

    public void startQuest(HttpServletRequest req) throws IOException {
        String username = req.getParameter("username");
        String language = req.getParameter("choiceLanguage");
        int gamesquanity = PlayerRepository.getPlayerCount(username);

        repositoryRequestHandler = getRepositoryRequestHandler(language);


        int countLevel = 0;
        isGameOver = false;
        /*String username = req.getParameter("username");
        int gamesquanity = PlayerRepository.getPlayerCount(username);
        String language = req.getParameter("choiceLanguage");*/
        answerRepository = getAnswerRepository(language);
        dataTransferPerSession(username, gamesquanity, language);
        getDataFromRepository(true, countLevel);
        transferringDataToRequest(req, countLevel);
    }


    private AnswerRepository getAnswerRepository(String language) {
        AnswerRepository repository = repositorySelection.creatRepository(language);
        logger.debug(repository.getClass() + " downloadDataByLanguage");
        return repository;
    }

    private RepositoryRequestHandler getRepositoryRequestHandler(String language){
        return new RepositoryRequestHandler(language);
    }

    private void dataTransferPerSession(String username, int gamesquanity, String language) throws UnknownHostException {
        currentSession.setAttribute("username", username);
        currentSession.setAttribute("gamesquanity", gamesquanity);
        currentSession.setAttribute("language", language);
        currentSession.setAttribute("ip", Inet4Address.getLocalHost().getHostAddress());
//        currentSession.setAttribute("entityInterface", answerRepository.getEntityInterface());
        currentSession.setAttribute("entityInterface", repositoryRequestHandler.getEntityInterface());
    }

    private void getDataFromRepository(boolean positiveAnswer, int countLevel) {
        if (positiveAnswer) {
            repositoryRequestHandler.EntityQuestSelection(true);
            /*isGameOver = answerRepository.getLevelPositiveIsGameOver(countLevel);
            message = answerRepository.getLevelPositiveMessage(countLevel);*/
        } else {
            repositoryRequestHandler.EntityQuestSelection(false);
            /*isGameOver = answerRepository.getLevelNegativeIsGameOver(countLevel);
            message = answerRepository.getLevelNegativeMessage(countLevel);*/
        }
    }

    private boolean logicQuest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.error(currentSession.getAttribute("countLevel") + "    logicQuest");
        repositoryRequestHandler.lastLevel();
        int countLevel = (int) currentSession.getAttribute("countLevel");
        if (repositoryRequestHandler.IsVictory()) {
            resp.sendRedirect(req.getContextPath() + "/victory.jsp");
            return true;
        }
        /*if (countLevel == answerRepository.getSize()) {
            resp.sendRedirect(req.getContextPath() + "/victory.jsp");
            return true;
        }*/
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
        currentSession.setAttribute("countLevel", repositoryRequestHandler.getCountLevel());
        req.setAttribute("message", repositoryRequestHandler.getMessage());
        /*currentSession.setAttribute("countLevel", countLevel);
        req.setAttribute("message", message);*/
    }


    @Override
    public void destroy() {
    }
}
