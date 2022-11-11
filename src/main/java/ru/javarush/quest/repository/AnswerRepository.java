package ru.javarush.quest.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javarush.quest.InitServlet;
import ru.javarush.quest.entity.Entity;

import java.util.HashMap;
import java.util.Map;

public abstract class AnswerRepository {

    Map<Integer, Entity> positiveAnswer;
    Map<Integer, Entity> negativeAnswer;
    String startMessage;
    String positiveNameButton;
    String negativeNameButton;
    String winMessage;
    String lossMessage;
    String[] statistic;
    private static final Logger logger = LoggerFactory.getLogger(InitServlet.class);

    public AnswerRepository() {
        this.positiveAnswer = new HashMap<>();
        this.negativeAnswer = new HashMap<>();
    }


    public Entity getEntity(int level, boolean positive) {
        if (positive) {
            return positiveAnswer.get(level);
        }
        return negativeAnswer.get(level);
    }

    public String getLevelMessage(int level, boolean positive) {

        return getEntity(level, positive).getMessage();
    }

    public boolean isGameOver(int level, boolean positive) {
        return getEntity(level, positive).isGameOver();
    }

    public String getStartMessage() {
        return startMessage;
    }

    public int getSize() {
        return positiveAnswer.size();
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
