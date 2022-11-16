package ru.javarush.quest.logics;

import ru.javarush.quest.entity.EntityInterface;
import ru.javarush.quest.entity.EntityQuest;
import ru.javarush.quest.repository.AnswerRepository;

public class RepositoryRequestHandler {
    AnswerRepository answerRepository;
    FactoryRepository factoryRepository;

    int countLevel;

    public RepositoryRequestHandler(String language) {
        factoryRepository = new FactoryRepository();
        this.answerRepository = factoryRepository.creatRepository(language);
        this.countLevel = 0;
    }

    public void lastLevel(){
        countLevel++;
    }

    public EntityInterface getEntityInterface(){
        return answerRepository.getEntityInterface();
    }

    public EntityQuest getPositiveEntityQuest(){
        return answerRepository.getEntityPositiveAnswer(countLevel);
    }

    public EntityQuest getNegativeEntityQuest(){
        return answerRepository.getEntityNegativeAnswer(countLevel);
    }

    public String getPositiveMessage(){
        return getPositiveEntityQuest().getMessage();
    }

    public String getNegativeMessage(){
        return getPositiveEntityQuest().getMessage();
    }

    public boolean IsGameOver(){
        return true;
    }
}
