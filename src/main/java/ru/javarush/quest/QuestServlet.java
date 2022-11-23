package ru.javarush.quest;


import ru.javarush.quest.entity.EntityStatistics;
import ru.javarush.quest.handler.AnswerType;
import ru.javarush.quest.handler.RepositoryRequestHandler;

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
import ru.javarush.quest.repository.RepositoryLanguageType;

import java.net.UnknownHostException;

@WebServlet(name = "QuestServlet", value = {"/quest-servlet", "/start"})
public class QuestServlet extends HttpServlet {
    private HttpSession currentSession;
    RepositoryRequestHandler repositoryRequestHandler;
    private static final Logger logger = LoggerFactory.getLogger(QuestServlet.class);

    @Override
    public void init() throws ServletException {
        super.init();
        /*Нахождение директории проекта если понадобитьс*/
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        String projectPathOut = loader.getResource("").getPath();
        logger.debug(projectPathOut);
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
        currentSession = req.getSession(true);
        String httpMethod = req.getMethod();
        if (httpMethod.equalsIgnoreCase("GET")) {
            doGet(req, resp);
            return;
        }
        doPost(req, resp);
    }

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
        getServletContext().getRequestDispatcher("/quest.jsp").forward(req, resp);
    }

    private void startQuest(HttpServletRequest req) throws IOException {
        dataTransferPerSession(getDataFromRequest(req));
        repositoryRequestHandler.entityQuestSelection(AnswerType.POSITIVE);
        transferringDataToRequest(req);
    }

    public EntityStatistics getDataFromRequest(HttpServletRequest req) {
        String username = req.getParameter("username");
        String language = req.getParameter("choiceLanguage");
        int gamesquanity = PlayerRepository.getPlayerCount(username);
        EntityStatistics entityStatistics = new EntityStatistics(username, gamesquanity, language);
        repositoryRequestHandler = getRepositoryRequestHandler(language);
        return entityStatistics;
    }

    public RepositoryRequestHandler getRepositoryRequestHandler(String language) {
        return new RepositoryRequestHandler(RepositoryLanguageType.valueOf(language));
    }

    private void dataTransferPerSession(EntityStatistics entityStatistics) throws UnknownHostException {
        currentSession.setAttribute("ip", Inet4Address.getLocalHost().getHostAddress());
        currentSession.setAttribute("entityStatistics", entityStatistics);
        currentSession.setAttribute("entityInterface", repositoryRequestHandler.getEntityInterface());
    }

    private boolean logicQuest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        choiceEntityQuest(req);
        transferringDataToRequest(req);
        if (repositoryRequestHandler.IsVictory()) {
            resp.sendRedirect(req.getContextPath() + "/victory.jsp");
            return true;
        }
        if (repositoryRequestHandler.IsGameOver()) {
            resp.sendRedirect(req.getContextPath() + "/loss.jsp");
            return true;
        }
        return false;
    }

    public void choiceEntityQuest(HttpServletRequest req) {
        repositoryRequestHandler.lastLevel();
        logger.debug(repositoryRequestHandler.getCountLevel() + " logicQuest");
        String radioButtonChoice = req.getParameter("choice");
        if (radioButtonChoice.equalsIgnoreCase("positiveAnswer")) {
            repositoryRequestHandler.entityQuestSelection(AnswerType.POSITIVE);
        } else {
            repositoryRequestHandler.entityQuestSelection(AnswerType.NEGATIVE);
        }
    }

    private void transferringDataToRequest(HttpServletRequest req) {
        req.setAttribute("countLevel", repositoryRequestHandler.getCountLevel());
        currentSession.setAttribute("message", repositoryRequestHandler.getMessage());
    }

    public RepositoryRequestHandler getRepositoryRequestHandler() {
        return repositoryRequestHandler;
    }

    public void setRepositoryRequestHandler(RepositoryRequestHandler repositoryRequestHandler) {
        this.repositoryRequestHandler = repositoryRequestHandler;
    }

    @Override
    public void destroy() {
    }
}
