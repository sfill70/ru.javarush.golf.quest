package ru.javarush.quest.factory;

import ru.javarush.quest.repository.AnswerRepository;
import ru.javarush.quest.repository.RepositoryEn;
import ru.javarush.quest.repository.RepositoryRu;

public class FactoryRepository {

    public AnswerRepository creatRepository(String language) {
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
