package ru.javarush.quest.repository;

import ru.javarush.quest.entity.Entity;

import java.util.HashMap;
import java.util.Map;

public abstract class AnswerRepository {
    Map<Integer, Entity> positiveAnswer;
    Map<Integer, Entity> negativeAnswer;
    String positiveNameButton;
    String negativeNameButton;
    String winMessage;
    String lossMessage;

    public AnswerRepository() {
        this.positiveAnswer = new HashMap<>();
        this.negativeAnswer = new HashMap<>();
    }

    public Entity getEntity(int level, boolean positive) {
        if(positive){
        return positiveAnswer.get(level);}
        return negativeAnswer.get(level);
    }

    public String getLevelMessage(int level, boolean positive) {
        return getEntity(level,positive).getMessage();
    }

    public boolean isGameOver(int level,  boolean positive) {
        return getEntity(level, positive).isGameOver();
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
}
