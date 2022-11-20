package ru.javarush.quest.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javarush.quest.repository.AnswerRepository;
import ru.javarush.quest.repository.RepositoryEn;
import ru.javarush.quest.repository.RepositoryLanguageType;
import ru.javarush.quest.repository.RepositoryRu;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class RepositorySelection {
    private static final Logger logger = LoggerFactory.getLogger(RepositorySelection.class);

    public AnswerRepository creatRepository(RepositoryLanguageType type) {
        logger.debug("Selection");
        switch (type) {
            case RU:
                return new RepositoryRu();
            case EN:
                return new RepositoryEn();
            default:
                return new RepositoryRu();
        }
    }
}
