package ru.javarush.quest.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javarush.quest.InitServlet;
import ru.javarush.quest.entity.Entity;

import java.util.HashMap;
import java.util.Map;

public abstract class AnswerRepository {
    Map<Integer, Entity> answer;
    String positiveNameButton;
    String negativeNameButton;
    String winMessage;
    String lossMessage;
    String[] statistic;

    private static final Logger logger = LoggerFactory.getLogger(AnswerRepository.class);

    public AnswerRepository() {
        this.answer = new HashMap<>();
    }

    public Entity getEntity(int level) {
        return answer.get(level);
    }

    public String getLevelMessage(int level, boolean positive) {
        if (positive) {
            return getEntity(level).getMessage();
        }
        return getEntity(level).getMessageEndGame();
    }

    public boolean isGameOver(int level, boolean positive) {
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
