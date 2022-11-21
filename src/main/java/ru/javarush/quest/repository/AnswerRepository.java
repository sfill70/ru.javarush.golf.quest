package ru.javarush.quest.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javarush.quest.entity.EntityInterface;
import ru.javarush.quest.entity.EntityQuest;

import java.util.HashMap;
import java.util.Map;

public abstract class AnswerRepository {

    /*positive response repository*/
    protected Map<Integer, EntityQuest> positiveAnswer;
    /*negative response repository*/
    protected Map<Integer, EntityQuest> negativeAnswer;
    /*Repository for the third branch of the quest*/
    Map<Integer, EntityQuest> someAnswer;
    protected EntityInterface entityInterface;
    private static final Logger logger = LoggerFactory.getLogger(AnswerRepository.class);

    public AnswerRepository() {
        this.positiveAnswer = new HashMap<>();
        this.negativeAnswer = new HashMap<>();
    }

    public EntityQuest getEntityPositiveAnswerToLevel(int level) {
        return positiveAnswer.get(level);
    }


    public EntityQuest getEntityNegativeAnswerToLevel(int level) {
        return negativeAnswer.get(level);
    }


    public int getSize() {
        return positiveAnswer.size();
    }

    public EntityInterface getEntityInterface() {
        return entityInterface;
    }
}
