package ru.javarush.quest.repository;

import ru.javarush.quest.entity.Entity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PositiveAnswerRepository extends AnswerRepository {


    public PositiveAnswerRepository() {
        super();
        this.nameButton = "YES";
        this.answer.put(0, new Entity(message0, false));
        this.answer.put(1, new Entity(message1, false));
        this.answer.put(2, new Entity(message2, false));
        this.answer.put(3, new Entity(message3, false));
        this.answer.put(4, new Entity(message4, false));

    }


    String message0 = "Question0";
    String message1 = "Question1";
    String message2 = "Question2";
    String message3 = "Question3";
    String message4 = "Question4";
    String message5 = "Question5";

    static String[] array = {"qwer", "svdv", "dsvfderv"};

}
