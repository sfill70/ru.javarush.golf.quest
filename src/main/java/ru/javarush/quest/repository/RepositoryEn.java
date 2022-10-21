package ru.javarush.quest.repository;

import ru.javarush.quest.entity.Entity;

public class RepositoryEn extends AnswerRepository {
    public RepositoryEn() {
        super();
        this.negativeNameButton = "NO";
        this.positiveNameButton = "YES";
        this.positiveAnswer.put(0, new Entity(message0, false));
        this.positiveAnswer.put(1, new Entity(message1, false));
        this.positiveAnswer.put(2, new Entity(message2, false));
        this.positiveAnswer.put(3, new Entity(message3, false));
        this.positiveAnswer.put(4, new Entity(message4, false));
        this.negativeAnswer.put(1, new Entity(negativeMessage1, true));
        this.negativeAnswer.put(2, new Entity(negativeMessage2, true));
        this.negativeAnswer.put(3, new Entity(negativeMessage3, true));
        this.negativeAnswer.put(4, new Entity(negativeMessage4, true));
        this.negativeAnswer.put(5, new Entity(negativeMessage5, true));

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
    String negativeMessage5 = "Answer5";
}
