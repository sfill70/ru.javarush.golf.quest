package ru.javarush.quest.repository;

import ru.javarush.quest.entity.EntityInterface;
import ru.javarush.quest.entity.EntityQuest;

public class RepositoryRu extends AnswerRepository {
    public RepositoryRu() {
        super();
        this.entityInterface = anInterface;
        this.positiveAnswer.put(0, new EntityQuest(quest0, false));
        this.positiveAnswer.put(1, new EntityQuest(quest1, false));
        this.positiveAnswer.put(2, new EntityQuest(quest2, true));
        this.positiveAnswer.put(3, new EntityQuest(quest3, true));
        this.positiveAnswer.put(4, new EntityQuest(quest4, false));
        this.positiveAnswer.put(5, new EntityQuest(quest5, true));
        this.positiveAnswer.put(6, new EntityQuest(quest6, false));
        this.negativeAnswer.put(1, new EntityQuest(questNegative1, true));
        this.negativeAnswer.put(2, new EntityQuest(questNegative2, false));
        this.negativeAnswer.put(3, new EntityQuest(questNegative3, false));
        this.negativeAnswer.put(4, new EntityQuest(questNegative4, true));
        this.negativeAnswer.put(5, new EntityQuest(questNegative5, false));
        this.negativeAnswer.put(6, new EntityQuest(questNegative6, true));
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
    String quest1 = "<p>Майор Том, на связи центр управления.</p>" +
            "<p>Отсчет пошел, двигатели включены.</p>" +
            "<p>Проверьте зажигание, и да помогут вам боги Майор Том, это центр управления.</p>" +
            "<p>Вы уже в космосе.</p>" +
            "<p>Пресса хочет знать, какого производителя рубашку вы носите?</p>" +
            "<p>Какой зубной пастой чистите зубы, какие смотрите фильмы? Страна должна зать своих героев.</p>" +
            "<p>А теперь пора покинуть корабль, и выйти в откратый космос.</p>" +
            "<p>Пресса хочет видеть человека в открытом космосе, если вы осмелитесь ))).</p>" +
            "<p>Вы выйдите в открытый космос?</p>";
    String quest2 = "Майор Том, вышел в открытый космос.\n" + "Но тросс порвался и заклинило люк, кислород закончится через...\n" +
            "Отсчет пошел...";
    String quest3 = "Апарат для анабиоза был передалан из китайской морозильной камеры,\n" +
            "хладоген вытек, таймер сломался, экспедиция завершилась провалом.";
    String quest4 = "Майор Том, оказался от таблеток, занимается обслуживанием оборудования, от скуки изучает все подряд, " +
            "теорию машин и механизмов, молекулярную физику и химию, биологию, лингвистику и т.д. Автоматика подает сигнал, обнаружена планета" +
            " с разумной жизнью, но судя по ландшафту, уровень развития не слишком высокий, только наяалось строительскво железных дорог." +
            "Совершить посадку ?";
    String quest5 = "Планета оказалась заселена, человекоподобными существами с которыми Майор Том бысро наладил контакт." +
            "пользуясь получиными знаниями и технологиями находивщимися на корабле, он основал \"промышленную\" " +
            "империю и наслаждаетс богатством и славой. Он не хочет возвращаться. ";
    String quest6 = "Планету населяла расса разумных пауков. В развитии они стояли на шаг впереди человечества. " +
            "вы обменялись информацией о своих цивилизациях, они предложили доставить корабль Тома, через свой \"Null\"-портал " +
            "к границам Солнечной системы. Он скоро будет на Земле, но из-за временного парадокса, все тех кого он знал, " +
            "уже нет. Майор Том ни кому не нужен, только Прессе, у нее много важных вопроссов к Тому, про любимую еду, " +
            "одежду...." ;


    String questNegative1 = "Майор Том отказался от полета и прожил, ужастную, скучную жизнь с любимой женой \n" +
            "в соем домике в Калифорнии, на берегу океана, управлял самолетами внутренних авиалиний,\n" +
            "у них бало трое детей и умерли они в 80 лет в один день.";
    String questNegative2 = "Майор Том отказался выходить в открытый космос ему плевать на Прессу,\n" +
            "Чувствуестся, что он в унынии.\n" +
            "Майор Том передает центру управления \n" +
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
    String questNegative3 = "Майор Том, Майор Том, на связи центр управления, нас волнует Ваше состояние. \n" +
            "начните принимать специальные зеленые таблетки от депресии. Майор Том выполнил указания, но \n" +
            "кто-то (наверно шпион), забил буробоном до отказа, апарат для анабиоза, виски с таблетками \n" +
            "дают странный эфект. Майору Тому кажется, что в иллюминатор стчится женщина очень похожая на его жену \n" +
            "Том в раздумье, бросить пить таблетки или впустить женщину \n" +
            "Бросить пить таблетки ?";

    String questNegative4 = "Майор Том, проводит время в обществе воображаемой супруги. таблеток хватит на весь полет, " +
            "бурбона много, он забыл зачеми куда он летит в космосе. ";

    String questNegative5 = "Майор Том решил продолжить полет, он не хочет вмешиваться в жизнь этой планеты. " +
            "Время медленно течет, но вот автоматика снова ожила. Обнаружена планета с признаком васокоразвитой цивилизации." +
            "По всм признакам они уже давно освоили космос. " +
            "Установить контакт?";
    String questNegative6 = "Том опасается, что контакт с такой развитой цивилизацией, будет опасен для Земли." +
            "он летит дальше в космос.... больше ведений о нем нет.";


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
