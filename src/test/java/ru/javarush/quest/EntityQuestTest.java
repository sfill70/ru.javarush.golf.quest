package ru.javarush.quest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.javarush.quest.entity.EntityQuest;

public class EntityQuestTest {

    String answer = "Answer";
    boolean isGameOver;

    EntityQuest entityQuest = new EntityQuest(answer, isGameOver);

    @Test
    public void getMessageTest() {
        Assertions.assertEquals(entityQuest.getMessage(), answer);
    }

    @Test
    public void isGameOverTest() {
        Assertions.assertEquals(entityQuest.isGameOver(), isGameOver);
    }
}