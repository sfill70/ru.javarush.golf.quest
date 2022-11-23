package ru.javarush.quest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.javarush.quest.entity.EntityInterface;
import ru.javarush.quest.entity.EntityStatistics;

public class EntityStatisticsTest {
    EntityStatistics entityStatistics = new EntityStatistics("name", 2, "RU");

    @Test
    public void getName() {
        Assertions.assertEquals(entityStatistics.getName(), "name");
    }

    @Test
    public void getGamesQuanity() {
        Assertions.assertEquals(entityStatistics.getGamesQuanity(), 2);
    }

    @Test
    public void getLanguage() {
        Assertions.assertEquals(entityStatistics.getLanguage(), "RU");
    }


}
