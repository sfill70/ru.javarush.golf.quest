package ru.javarush.quest.logics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javarush.quest.repository.AnswerRepository;
import ru.javarush.quest.repository.RepositoryEn;
import ru.javarush.quest.repository.RepositoryRu;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class RepositorySelection {
    private static final Logger logger = LoggerFactory.getLogger(RepositorySelection.class);
    public AnswerRepository creatRepository(String language) {
        logger.debug("Fabrica");
        switch (language) {
            case "RU":
                return new RepositoryRu();
            case "EN":
                return new RepositoryEn();
            default:
                return new RepositoryRu();
        }
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        RepositorySelection repositorySelection = new RepositorySelection();

        System.out.println(repositorySelection.creatRepository("EN").getEntityInterface().getNegativeNameButton());

        String ANSI = " РЈСЃС‚Р°РЅРѕРІРєР° РІРµР± РїСЂРёР»РѕР¶РµРЅРёСЏ РІ РїР°РїРєСѓ";
        String ANSI3 = "Ð°Ð±Ð²Ð³";
        String myUTF8 = new String(ANSI.getBytes(StandardCharsets.ISO_8859_1),"UTF-8");
        System.out.println(myUTF8);
        char c = 'Р';
        char u = 'Ð';
        System.out.println((int) c);
        System.out.println((int) u);

//        String myUTF8 = new String(my8859.getBytes("ISO-8859-1"),"UTF-8")


    }
}
