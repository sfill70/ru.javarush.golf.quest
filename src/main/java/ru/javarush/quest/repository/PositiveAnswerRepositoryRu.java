package ru.javarush.quest.repository;

import ru.javarush.quest.entity.Entity;

import java.util.HashMap;
import java.util.Map;

public class PositiveAnswerRepositoryRu extends AnswerRepository {

    public PositiveAnswerRepositoryRu() {
        super();
        this.nameButton = "Согласиться";
        this.answer.put(0, new Entity(message0, false));
        this.answer.put(1, new Entity(message1, false));
        this.answer.put(2, new Entity(message2, false));
        this.answer.put(3, new Entity(message3, false));
        this.answer.put(4, new Entity(message4, false));

    }

    String message0 = "Вопрос0";
    String message1 = "Вопрос1";
    String message2 = "Вопрос2";
    String message3 = "Вопрос3";
    String message4 = "Вопрос4";
    String message5 = "Вопрос5";

    static String[] array = {"qwer", "svdv", "dsvfderv"};

}
