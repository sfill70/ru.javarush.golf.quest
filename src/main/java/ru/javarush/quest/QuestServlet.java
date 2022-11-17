package ru.javarush.quest;


import ru.javarush.quest.logics.RepositoryRequestHandler;

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
        getServletContext().getRequestDispatcher("/quest.jsp").forward(req, resp);
    }

    public void startQuest(HttpServletRequest req) throws IOException {
        String username = req.getParameter("username");
        String language = req.getParameter("choiceLanguage");
        int gamesquanity = PlayerRepository.getPlayerCount(username);
        repositoryRequestHandler = getRepositoryRequestHandler(language);
        dataTransferPerSession(username, gamesquanity, language, repositoryRequestHandler, currentSession);
        repositoryRequestHandler.EntityQuestSelection(true);
        transferringDataToRequest(req);
    }

    private RepositoryRequestHandler getRepositoryRequestHandler(String language) {
        return new RepositoryRequestHandler(language);
    }

    private void dataTransferPerSession(String username, int gamesquanity, String language,
                                        RepositoryRequestHandler repositoryRequestHandler,
                                        HttpSession currentSession) throws UnknownHostException {
        System.out.println(username);
        System.out.println(currentSession.getId());
        currentSession.setAttribute("username", username);
        currentSession.setAttribute("gamesquanity", gamesquanity);
        currentSession.setAttribute("language", language);
        currentSession.setAttribute("ip", Inet4Address.getLocalHost().getHostAddress());
        currentSession.setAttribute("entityInterface", repositoryRequestHandler.getEntityInterface());
    }

    private boolean logicQuest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        repositoryRequestHandler.lastLevel();
        logger.debug(repositoryRequestHandler.getCountLevel() + " logicQuest");
        String radioButtonChoice = req.getParameter("choice");
        if (radioButtonChoice.equalsIgnoreCase("positiveAnswer")) {
            repositoryRequestHandler.EntityQuestSelection(true);
        } else {
            repositoryRequestHandler.EntityQuestSelection(false);
        }
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

    private void transferringDataToRequest(HttpServletRequest req) {
        req.setAttribute("countLevel", repositoryRequestHandler.getCountLevel());
        currentSession.setAttribute("message", repositoryRequestHandler.getMessage());
    }

    @Override
    public void destroy() {
    }
}
