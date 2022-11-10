package ru.javarush.quest.repository;

import ru.javarush.quest.entity.Entity;

public class RepositoryRu extends AnswerRepository {
    public RepositoryRu() {
        super();
        this.negativeNameButton = "Отказаться";
        this.positiveNameButton = "Согласиться";
        winMessage = win;
        lossMessage = loss;
        statistic = stat;
        this.answer.put(0, new Entity(message0, negativeMessage0, false));
        this.answer.put(1, new Entity(message1, negativeMessage1, false));
        this.answer.put(2, new Entity(message2, negativeMessage2, true));
        this.answer.put(3, new Entity(message3, negativeMessage3, false));
        this.answer.put(4, new Entity(message4, negativeMessage4, true));
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
    String negativeMessage0 = "Ответ0";

    String win = "<h2 > Победил !!! </h2>";

    String loss = "<h2>" + "Хюстон, у нас проблема !!!" + "</h2>"
            + "<span class=" + "second" + "> провалил задание игрок с ником: </span>";

    String[] stat = {"Статистика:", "Твой текущий IP address:", "Имя в игре: ", "Количество игр:"};
}
