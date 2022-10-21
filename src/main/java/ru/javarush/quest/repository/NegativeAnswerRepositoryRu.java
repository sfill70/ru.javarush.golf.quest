package ru.javarush.quest.repository;

import ru.javarush.quest.entity.Entity;

import java.util.HashMap;
import java.util.Map;

public class NegativeAnswerRepositoryRu extends AnswerRepository {

    public NegativeAnswerRepositoryRu() {
        super();
        this.nameButton = "Отказаться";
        this.answer.put(1, new Entity(message1, true));
        this.answer.put(2, new Entity(message2, true));
        this.answer.put(3, new Entity(message3, true));
        this.answer.put(4, new Entity(message4, true));
        this.answer.put(5, new Entity(message5, true));

    }

    String message1 = "Ответ1";
    String message2 = "Ответ2";
    String message3 = "Ответ3";
    String message4 = "Ответ4";
    String message5 = "Ответ5";
}
