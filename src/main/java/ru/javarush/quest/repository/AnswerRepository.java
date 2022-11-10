package ru.javarush.quest.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javarush.quest.InitServlet;
import ru.javarush.quest.entity.Entity;

import java.util.HashMap;
import java.util.Map;

public abstract class AnswerRepository {
    /**
     * these repositories contain messages that will be displayed if the game is continued or the game is over.
     * positiveAnswer - game message to continue the game, negativeAnswer - final message if the game is lost.
     */
    Map<Integer, Entity> positiveAnswer;
    Map<Integer, Entity> negativeAnswer;

    Map<Integer, Entity> answer;
    String positiveNameButton;
    String negativeNameButton;
    String winMessage;
    String lossMessage;
    String[] statistic;
    private static final Logger logger = LoggerFactory.getLogger(InitServlet.class);

    /*public AnswerRepository() {
        this.positiveAnswer = new HashMap<>();
        this.negativeAnswer = new HashMap<>();
    }*/

    public AnswerRepository(String st) {
        this.answer = new HashMap<>();
    }

    /*public Entity getEntity(int level, boolean positive) {
        if (positive) {
            return positiveAnswer.get(level);
        }
        return negativeAnswer.get(level);
    }*/

    public Entity getEntity(int level) {
        return answer.get(level);
    }
    /*public String getLevelMessage(int level, boolean positive) {

        return getEntity(level, positive).getMessage();
    }*/

    public String getLevelMessage(int level, boolean positive, String s) {
        if (positive) {
            return getEntity(level).getMessage();
        }
        return getEntity(level).getMessageEndGame();
    }

    /*public boolean isGameOver(int level, boolean positive) {
        return getEntity(level, positive).isGameOver();
    }*/

    public boolean isGameOver(int level, boolean positive, String s) {
        logger.error(String.valueOf(positive && getEntity(level).isGameOver()) + " isG = "
                + getEntity(level).isGameOver() + "/ " + positive);
        return positive == getEntity(level).isGameOver();
    }

    public int getSize() {
        return answer.size();
    }

    public String getPositiveNameButton() {
        return positiveNameButton;
    }

    public String getNegativeNameButton() {
        return negativeNameButton;
    }

    public String getLossMessage() {
        return lossMessage;
    }

    public String getWinMessage() {
        return winMessage;
    }

    public String[] getStatistic() {
        return statistic;
    }
}
