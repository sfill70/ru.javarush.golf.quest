package ru.javarush.quest.repository;

import ru.javarush.quest.entity.EntityInterface;
import ru.javarush.quest.entity.EntityQuest;

public class RepositoryRu extends AnswerRepository {
    public RepositoryRu() {
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

    String message0 = "Вопрос0";
    String message1 = "Вопрос1";
    String message2 = "Вопрос2";
    String message3 = "Вопрос3";
    String message4 = "Вопрос4";
    String message5 = "Вопрос5";

    String quest0 = "Майор Том, на связи центр управления, Вы готовы полететь к звездам,\n" +
            " в надежде найти обатаемые миры и братьев по разуму.\n " +
            "Вы согласны?";
    String quest1 = "Майор Том, на связи центр управления\n" +
            "Отсчет пошел, двигатели включены\n" +
            "Проверьте зажигание, и да помогут вам боги Майор Том, это центр управления\n" +
            "Вы уже в космосе\n" +
            "Пресса хочет знать, какого производителя рубашку вы носите? \n" +
            "Какой зубной пастой чистите зубы, какие смотрите фильмы? Страна должна зать своих героев\n" +
            "А теперь пора покинуть корабль, и выйти в откратый космос\n" +
            "Пресса хочет видеть человека в открытом космосе, если вы осмелитесь ))).\n" +
            "Вы выйдите в открытый космос?";
    String quest2 = "Майор Том, вышел в открытый космос.\n" + "Но тросс порвался и заклинило люк, кислород закончится через...\n" +
            "Отсчет пошел...";
    String quest3 = "Апарат для анабиоза был передалан из китайской морозильной камеры,\n" +
            "хладоген вытек, таймер сломался, экспедиция завершилась провалом.";


    String questNegative1 = "Майор Том отказался от полета и прожил, ужастную, скучную жизнь с любимой женой \n" +
            "в соем домике в Калифорнии, на берегу океана, управлял самолетами внутренних авиалиний,\n" +
            "у них бало трое детей и умерли они в 80 лет в один день.";
    String questNegative2 = "Майор Том отказался выходить в открытый космос ему плевать на Прессу,\n" +
            "Чувствуестся, что он в унынии\n" +
            "Майор Том передает центру управленияЖ\n" +
            "Сижу я в жестяной банке\n" +
            "Высоко-высоко над миром\n" +
            "Земля очень красива - Голубая планета \n" +
            "Но я чувствую себя совершенно беспомощным.\n" +
            "Хоть и пролетел сотню тысяч миль\n" +
            "И думаю, мой космический корабль знает, куда лететь\n" +
            "Скажите моей жене, что я очень сильно люблю ее, она знает это \n" +
            "Майор Том, на связи центр управления, советуем лечь в анабиоз, полет очень длинный. \n" +
            "При обнаружении признаков жизни, автоматика разбудит Вас. \n" +
            "Согласны лечь в анабиоз?";
    String questNegative3 ="Майор Том, Майор Том, на связи центр управления, нас волнует Ваше состояние. \n" +
            "начните принимать специальные зеленые таблетки от депресии. Майор Том выполнил указания, но \n" +
            "кто-то (наверно шпион), забил буробоном до отказа, апарат для анабиоза, виски с таблетками \n" +
            "дают странный эфект. Майору Тому кажется, что в иллюминатор стутся женщина очень похожая на его жену \n" +
            "Том в раздумье, бросить пить таблетки или впустить женщину \n" +
            "Бросить пить таблетки ?";

    String questNegative4 ="";


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
    EntityInterface anInterface = new EntityInterface(start, "Согласиться", "Отказаться", "Ответить", win, loss, stat);
}
