package ru.javarush.quest.handler;

import ru.javarush.quest.entity.EntityInterface;
import ru.javarush.quest.entity.EntityQuest;
import ru.javarush.quest.repository.AnswerRepository;
import ru.javarush.quest.repository.RepositoryLanguageType;

public class RepositoryRequestHandler {
    AnswerRepository answerRepository;
    RepositorySelection repositorySelection;
    EntityQuest entityQuest;
    int countLevel;

    public RepositoryRequestHandler(RepositoryLanguageType type) {
        repositorySelection = new RepositorySelection();
        this.answerRepository = repositorySelection.creatRepository(type);
        this.countLevel = 0;
    }
    public void lastLevel() {
        countLevel++;
    }

    public AnswerRepository getAnswerRepository() {
        return answerRepository;
    }

    public EntityInterface getEntityInterface() {
        return answerRepository.getEntityInterface();
    }

    public EntityQuest getPositiveEntityQuest() {
        return answerRepository.getEntityPositiveAnswerToLevel(countLevel);
    }

    public EntityQuest getNegativeEntityQuest() {
        return answerRepository.getEntityNegativeAnswerToLevel(countLevel);
    }

    public void EntityQuestSelection(AnswerType answerType) {
        if (answerType == AnswerType.POSITIVE) {
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