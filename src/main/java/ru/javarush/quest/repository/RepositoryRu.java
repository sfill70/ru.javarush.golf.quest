package ru.javarush.quest.repository;

import ru.javarush.quest.entity.EntityInterface;
import ru.javarush.quest.entity.EntityQuest;

public class RepositoryRu extends AnswerRepository {
    public RepositoryRu() {
        super();
        this.entityInterface = anInterface;
        this.negativeNameButton = "Отказаться";
        this.positiveNameButton = "Согласиться";
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
        startMessage = start;
        winMessage = win;
        lossMessage = loss;
        statistic = stat;

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
    String negativeMessage5 = "Ответ5";

    String start = "Стартовое сообщение";
    String win = "<h2 > Победил !!! </h2>";

    String loss = "<h2>" + "Хюстон, у нас проблема !!!" + "</h2>"
            + "<span class=" + "second" + "> провалил задание игрок с ником: </span>";

    String[] stat = {"Статистика:", "Твой текущий IP address:", "Имя в игре: ", "Количество игр:"};
    EntityInterface anInterface = new EntityInterface(start, "Согласиться","Отказаться",win, loss, stat);
}
