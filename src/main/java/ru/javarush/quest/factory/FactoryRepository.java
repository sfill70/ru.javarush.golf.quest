package ru.javarush.quest.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javarush.quest.InitServlet;
import ru.javarush.quest.repository.AnswerRepository;
import ru.javarush.quest.repository.RepositoryEn;
import ru.javarush.quest.repository.RepositoryRu;

public class FactoryRepository {
    private static final Logger logger = LoggerFactory.getLogger(FactoryRepository.class);
    public AnswerRepository creatRepository(String language) {
        logger.debug(" FactoryRepository");
        switch (language) {
            case "RU":
                return new RepositoryRu();
            case "EN":
                return new RepositoryEn();
            default:
                return new RepositoryRu();
        }
    }

    public static void main(String[] args) {
        FactoryRepository factoryRepository = new FactoryRepository();

        System.out.println(factoryRepository.creatRepository("EN").getNegativeNameButton());

    }
}
