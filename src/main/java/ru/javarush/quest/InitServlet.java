package ru.javarush.quest;

import ru.javarush.quest.entity.Entity;
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

    AnswerRepository positiveAnswerRepository;
    AnswerRepository negativeAnswerRepository;
    int countLevel;
    String username;
    String language;
    String positiveButton;
    String negativeButton;
    int gamesquanity;
    boolean isGameOver;

    Entity entity;

    @Override
    public void init() throws ServletException {
        super.init();
        positiveAnswerRepository = new PositiveAnswerRepositoryRu();
        negativeAnswerRepository = new NegativeAnswerRepositoryRu();
        positiveButton = positiveAnswerRepository.getNameButton();
        negativeButton = negativeAnswerRepository.getNameButton();
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

            downloadDataByLanguage();

            currentSession.setAttribute("username", username);
            currentSession.setAttribute("gamesquanity", gamesquanity);
            currentSession.setAttribute("language", language);
            currentSession.setAttribute("negativeButton", negativeButton);
            currentSession.setAttribute("positiveButton", positiveButton);
            currentSession.setAttribute("ip", Inet4Address.getLocalHost().getHostAddress());
        }

        String radioButtonChoice = "";
        if (formName.equals("endgame")) {
            radioButtonChoice = req.getParameter("choice");
        }
        String answer = "";
        String message = "";
        if (countLevel == positiveAnswerRepository.getSize()) {
            resp.sendRedirect(req.getContextPath() + "/victory.jsp");
            return;
        }
        if (radioButtonChoice.isBlank() || radioButtonChoice.equals("positiveAnswer")) {
            answer = "YES!!!";
            message = positiveAnswerRepository.getLevelMessage(countLevel);
            isGameOver = positiveAnswerRepository.isGameOver(countLevel);
        } else {
            answer = "NO!!!!";
            message = negativeAnswerRepository.getLevelMessage(countLevel);
            isGameOver = negativeAnswerRepository.isGameOver(countLevel);
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
        if (language.equals("RU")) {
            positiveAnswerRepository = new PositiveAnswerRepositoryRu();
            negativeAnswerRepository = new NegativeAnswerRepositoryRu();
            positiveButton = positiveAnswerRepository.getNameButton();
            negativeButton = negativeAnswerRepository.getNameButton();
        } else {
            positiveAnswerRepository = new PositiveAnswerRepository();
            negativeAnswerRepository = new NegativeAnswerRepository();
            positiveButton = positiveAnswerRepository.getNameButton();
            negativeButton = negativeAnswerRepository.getNameButton();
        }
    }

    @Override
    public void destroy() {
    }

}
