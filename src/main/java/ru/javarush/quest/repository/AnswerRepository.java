package ru.javarush.quest.repository;

import ru.javarush.quest.entity.Entity;

import java.util.HashMap;
import java.util.Map;

public abstract class AnswerRepository {
    Map<Integer, Entity> answer;
    String nameButton;

    public AnswerRepository() {
        this.answer = new HashMap<>();
    }

    public Entity getEntity (int level) {
        return answer.get(level);
    }

    public String getLevelMessage(int level){
        return getEntity(level).getMessage();
    }

    public boolean isGameOver (int level){
        return getEntity(level).isGameOver();
    }

    public int getSize(){
        return answer.size();
    }

    public String getNameButton(){
        return nameButton;
    }
}
