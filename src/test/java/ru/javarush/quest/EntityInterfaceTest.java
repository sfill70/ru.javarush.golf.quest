package ru.javarush.quest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.javarush.quest.entity.EntityInterface;
import ru.javarush.quest.repository.RepositoryEn;

public class EntityInterfaceTest {

    String start = "Start message";
    String win = "<h2> Victory !!! </h2>";
    String loss = "<h2>" + "Houston, we have a problem !!!" + "</h2>"
            + "<span class=" + "second" + "> failed mission player named: </span>";
    String[] stat = {"Statistic:", "Your current IP address:", "Name in the game: ", "Count game:"};
    EntityInterface entityInterface = new RepositoryEn().getEntityInterface();

    @Test
    public void getPositiveNameButton() {
        Assertions.assertEquals(entityInterface.getPositiveNameButton(), "I agree");
    }

    @Test
    public void getNegativeNameButton() {
        Assertions.assertEquals(entityInterface.getNegativeNameButton(), "Disagree");
    }

    @Test
    public void getAnswerButton() {
        Assertions.assertEquals(entityInterface.getAnswerButton(), "Reply");

    }

    @Test
    public void getWinMessage() {
        Assertions.assertEquals(entityInterface.getWinMessage(), win);
    }

    @Test
    public void getLossMessage() {
        Assertions.assertEquals(entityInterface.getLossMessage(), loss);
    }

   /* @Test
    public void getStatistic() {
        Assertions.assertEquals(entityInterface.getStatistic(), stat);
    }*/


}
