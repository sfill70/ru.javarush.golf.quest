package ru.javarush.quest.repository;

import ru.javarush.quest.entity.EntityInterface;
import ru.javarush.quest.entity.EntityQuest;

public class RepositoryEn extends AnswerRepository {
    public RepositoryEn() {
        super();
        this.entityInterface = anInterface;
        this.positiveAnswer.put(0, new EntityQuest(message0, false));
        this.positiveAnswer.put(1, new EntityQuest(message1, false));
        this.positiveAnswer.put(2, new EntityQuest(message2, false));
        this.positiveAnswer.put(3, new EntityQuest(message3, false));
        this.positiveAnswer.put(4, new EntityQuest(message4, false));
        this.positiveAnswer.put(5, new EntityQuest(message5, false));
        this.negativeAnswer.put(1, new EntityQuest(negativeMessage1, true));
        this.negativeAnswer.put(2, new EntityQuest(negativeMessage2, true));
        this.negativeAnswer.put(3, new EntityQuest(negativeMessage3, true));
        this.negativeAnswer.put(4, new EntityQuest(negativeMessage4, true));
        this.negativeAnswer.put(5, new EntityQuest(negativeMessage5, true));

    }

    String message0 = "Question0";
    String message1 = "Question1";
    String message2 = "Question2";
    String message3 = "Question3";
    String message4 = "Question4";
    String message5 = "Question5";

    String negativeMessage1 = "Answer1";
    String negativeMessage2 = "Answer2";
    String negativeMessage3 = "Answer3";
    String negativeMessage4 = "Answer4";
    String negativeMessage0 = "Answer0";
    String negativeMessage5 = "Answer5";
    String start = "Start message";
    String win = "<h2> Victory !!! </h2>";

    String loss = "<h2>" + "Houston, we have a problem !!!" + "</h2>"
            + "<span class=" + "second" + "> failed mission player named: </span>";
    String[] stat = {"Statistic:", "Your current IP address:", "Name in the game: ", "Count game:"};
    EntityInterface anInterface = new EntityInterface(start, "I agree","Disagree",win, loss, stat);
}
