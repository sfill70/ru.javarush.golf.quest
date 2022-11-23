package ru.javarush.quest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.javarush.quest.entity.EntityInterface;
import ru.javarush.quest.repository.RepositoryEn;
import ru.javarush.quest.repository.RepositoryRu;

public class EntityInterfaceTest {

    String win = "<h2> Victory !!! </h2>";
    String loss = "<h2>" + "Houston, we have a problem !!!" + "</h2>"
            + "<span class=" + "second" + "> failed mission player named: </span>";
    String[] stat = {"Statistic:", "Your current IP address:", "Name in the game: ", "Count game:"};
    EntityInterface entityInterfaceEn = new RepositoryEn().getEntityInterface();
    EntityInterface entityInterface = new RepositoryRu().getEntityInterface();


    @ParameterizedTest
    @CsvSource({
            "EN", "RU",
    })
    public void getPositiveNameButton(String language) {
        if (language.equalsIgnoreCase("EN")) {
            Assertions.assertEquals(entityInterfaceEn.getPositiveNameButton(), "I agree");
            return;
        }
        Assertions.assertEquals(entityInterface.getPositiveNameButton(), "Согласиться");

    }

    @ParameterizedTest
    @CsvSource({
            "EN", "RU",
    })
    public void getNegativeNameButton(String language) {
        if (language.equalsIgnoreCase("EN")) {
            Assertions.assertEquals(entityInterfaceEn.getNegativeNameButton(), "Disagree");
        }
        Assertions.assertEquals(entityInterface.getNegativeNameButton(), "Отказаться");
    }

    @Test
    public void getAnswerButton() {
        Assertions.assertEquals(entityInterfaceEn.getAnswerButton(), "Reply");

    }

    @Test
    public void getWinMessage() {
        Assertions.assertEquals(entityInterfaceEn.getWinMessage(), win);
    }

    @Test
    public void getLossMessage() {
        Assertions.assertEquals(entityInterfaceEn.getLossMessage(), loss);
    }

    @Test
    public void getStatistic() {
        Assertions.assertArrayEquals(entityInterfaceEn.getStatistic(), stat);
    }
}
