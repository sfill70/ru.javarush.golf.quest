package ru.javarush.quest.repository;

import ru.javarush.quest.entity.Entity;

public class RepositoryRu extends AnswerRepository {
    public RepositoryRu() {
        super();
        this.negativeNameButton = "Отказаться";
        this.positiveNameButton = "Согласиться";
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
        winMessage = win;

    }

    String message0 = "Вопрос0";
    String message1 = "Вопрос1";
    String message2 = "Вопрос2";
    String message3 = "Вопрос3";
    String message4 = "Вопрос4";
    String message5 = "Вопрос5";

    String negativeMessage1 = "Ответ1";
    String negativeMessage2 = "Ответ2";
    String negativeMessage3 = "Ответ3";
    String negativeMessage4 = "Ответ4";
    String negativeMessage5 = "Ответ5";

    String win = "<h2 class="+"second"+">"+"Победил !!!"+"<h2>";
}
