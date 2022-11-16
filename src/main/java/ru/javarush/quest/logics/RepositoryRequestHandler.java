package ru.javarush.quest.logics;

import ru.javarush.quest.entity.EntityInterface;
import ru.javarush.quest.entity.EntityQuest;
import ru.javarush.quest.repository.AnswerRepository;

public class RepositoryRequestHandler {
    AnswerRepository answerRepository;
    RepositorySelection repositorySelection;

    EntityQuest entityQuest;
    int countLevel;

    public RepositoryRequestHandler(String language) {
        repositorySelection = new RepositorySelection();
        this.answerRepository = repositorySelection.creatRepository(language);
        this.countLevel = 0;
    }

    public void lastLevel() {
        countLevel++;
    }


    public EntityInterface getEntityInterface() {
        return answerRepository.getEntityInterface();
    }

    public EntityQuest getPositiveEntityQuest() {
        return answerRepository.getEntityPositiveAnswer(countLevel);
    }

    public EntityQuest getNegativeEntityQuest() {
        return answerRepository.getEntityNegativeAnswer(countLevel);
    }

    public void EntityQuestSelection(boolean positiveAnswer) {
        if (positiveAnswer) {
            this.entityQuest = getPositiveEntityQuest();
            return;
        }
        this.entityQuest = getNegativeEntityQuest();
    }

    public String getMessage() {
        return entityQuest.getMessage();
    }

    public boolean IsGameOver() {
        return entityQuest.isGameOver();
    }

    public boolean IsVictory() {
        return !IsGameOver() && countLevel == answerRepository.getSize()-1;
    }

    public int getCountLevel(){
        return countLevel;
    }


}
