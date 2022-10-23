package ru.javarush.quest;

import ru.javarush.quest.entity.Entity;
import ru.javarush.quest.factory.FactoryRepository;
import ru.javarush.quest.repository.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.Inet4Address;

@WebServlet(name = "InitServlet", value = "/init-servlet")
public class InitServlet extends HttpServlet {

    AnswerRepository answerRepository;
    FactoryRepository factoryRepository;
    int countLevel;
    String username;
    String language;
    String positiveButton;
    String negativeButton;
    String winMessage;
    int gamesquanity;
    boolean isGameOver;

    @Override
    public void init() throws ServletException {
        super.init();
        factoryRepository = new FactoryRepository();
        answerRepository = new RepositoryRu();
        positiveButton = answerRepository.getPositiveNameButton();
        negativeButton = answerRepository.getNegativeNameButton();
        winMessage = answerRepository.getWinMessage();
        countLevel = 0;
    }

    //    Не используется, пригодится для сохранения данных, если req.getSession().invalidate();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession currentSession = req.getSession(true);
        currentSession.setAttribute("ip", Inet4Address.getLocalHost().getHostAddress());
        currentSession.setAttribute("username", username);
        currentSession.setAttribute("gamesquanity", gamesquanity);
        getServletContext().getRequestDispatcher("/").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession currentSession = req.getSession(true);
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
            winMessage = answerRepository.getWinMessage();

            downloadDataByLanguage();

            currentSession.setAttribute("username", username);
            currentSession.setAttribute("gamesquanity", gamesquanity);
            currentSession.setAttribute("language", language);
            currentSession.setAttribute("negativeButton", negativeButton);
            currentSession.setAttribute("positiveButton", positiveButton);
            currentSession.setAttribute("ip", Inet4Address.getLocalHost().getHostAddress());
            currentSession.setAttribute("winMessage", winMessage);
        }

        String radioButtonChoice = "";
        if (formName.equals("endgame")) {
            radioButtonChoice = req.getParameter("choice");
        }
        String answer = "";
        String message = "";
        if (countLevel == answerRepository.getSize()) {
            resp.sendRedirect(req.getContextPath() + "/victory.jsp");
            return;
        }
        if (radioButtonChoice.isBlank() || radioButtonChoice.equals("positiveAnswer")) {
            answer = "YES!!!";
            message = answerRepository.getLevelMessage(countLevel, true);
            isGameOver = answerRepository.isGameOver(countLevel, true);
        } else {
            answer = "NO!!!!";
            message = answerRepository.getLevelMessage(countLevel, false);
            isGameOver = answerRepository.isGameOver(countLevel, false);
        }

        if (isGameOver) {
            currentSession.setAttribute("message", message);
            getServletContext().getRequestDispatcher("/loss.jsp").forward(req, resp);
            return;
        }

        countLevel++;
        currentSession.setAttribute("answer", answer);
        currentSession.setAttribute("message", message);
        //Можно убрать
        currentSession.setAttribute("isGameOver", isGameOver);
        getServletContext().getRequestDispatcher("/mainPage.jsp").forward(req, resp);
    }


    private boolean usernameCheck(HttpServletRequest req, HttpServletResponse resp, HttpSession currentSession) throws ServletException, IOException {
        if (req.getParameter("username").isBlank() || req.getParameter("username").isEmpty()) {
            currentSession.setAttribute("blank", true);
            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
            resp.sendRedirect(req.getContextPath() + "/restart");
            return true;
        }
        return false;
    }

    private void downloadDataByLanguage() {
        answerRepository = factoryRepository.creatRepository(language);
        positiveButton = answerRepository.getPositiveNameButton();
        negativeButton = answerRepository.getNegativeNameButton();
        /* if (language.equals("RU")) {
            answerRepository = new RepositoryRu();
            positiveButton = answerRepository.getPositiveNameButton();
            negativeButton = answerRepository.getNegativeNameButton();
        } else {
            answerRepository = new RepositoryEn();
            positiveButton = answerRepository.getPositiveNameButton();
            negativeButton = answerRepository.getNegativeNameButton();
        }*/
    }

    @Override
    public void destroy() {
    }

}
