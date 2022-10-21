package ru.javarush.quest.repository;

import ru.javarush.quest.entity.Entity;

import java.util.HashMap;
import java.util.Map;

public class NegativeAnswerRepository extends AnswerRepository {

    public NegativeAnswerRepository() {
        super();
        this.nameButton = "NO";
        this.answer.put(1, new Entity(message1, true));
        this.answer.put(2, new Entity(message2, true));
        this.answer.put(3, new Entity(message3, true));
        this.answer.put(4, new Entity(message4, true));
        this.answer.put(5, new Entity(message5, true));

    }

    String message1 = "Answer1";
    String message2 = "Answer2";
    String message3 = "Answer3";
    String message4 = "Answer4";
    String message5 = "Answer5";
}
