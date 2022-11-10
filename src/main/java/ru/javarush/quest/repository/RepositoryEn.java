package ru.javarush.quest.repository;

import ru.javarush.quest.entity.Entity;

public class RepositoryEn extends AnswerRepository {
    public RepositoryEn() {
        super();
        this.negativeNameButton = "Disagree";
        this.positiveNameButton = "I agree";
        this.winMessage = win;
        lossMessage = loss;
        statistic = stat;
        this.answer.put(0, new Entity(message0, negativeMessage0, false));
        this.answer.put(1, new Entity(message1, negativeMessage1, false));
        this.answer.put(2, new Entity(message2, negativeMessage2, true));
        this.answer.put(3, new Entity(message3, negativeMessage3, false));
        this.answer.put(4, new Entity(message4, negativeMessage4, true));
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
    String win = "<h2> Victory !!! </h2>";

    String loss = "<h2>" + "Houston, we have a problem !!!" + "</h2>"
            + "<span class=" + "second" + "> failed mission player named: </span>";
    String[] stat = {"Statistic:", "Your current IP address:", "Name in the game: ", "Count game:"};
}
