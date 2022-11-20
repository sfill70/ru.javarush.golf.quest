package ru.javarush.quest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.javarush.quest.entity.EntityInterface;
import ru.javarush.quest.entity.EntityQuest;
import ru.javarush.quest.handler.AnswerType;
import ru.javarush.quest.handler.RepositoryRequestHandler;
import ru.javarush.quest.repository.RepositoryEn;
import ru.javarush.quest.repository.RepositoryLanguageType;
import ru.javarush.quest.repository.RepositoryRu;

@ExtendWith(MockitoExtension.class)
public class RepositoryRequestHandlerTest {
    RepositoryRequestHandler repositoryRequestHandlerEn;
    RepositoryRequestHandler repositoryRequestHandlerRu;

    @BeforeEach
    public void init() {
        repositoryRequestHandlerEn = new RepositoryRequestHandler(RepositoryLanguageType.EN);
        repositoryRequestHandlerRu = new RepositoryRequestHandler(RepositoryLanguageType.RU);
    }


    @Test
    public void lastLevelTest() {
        RepositoryRequestHandler repositoryRequestHandler = new RepositoryRequestHandler(RepositoryLanguageType.EN);
        repositoryRequestHandler.lastLevel();
        Assertions.assertEquals(repositoryRequestHandler.getCountLevel(), 1);
    }

    @Test
    public void getEntityInterfaceTest() {
        RepositoryRequestHandler repositoryRequestHandler = repositoryRequestHandlerEn;
        EntityInterface entityInterface = repositoryRequestHandler.getAnswerRepository().getEntityInterface();
        Assertions.assertEquals(entityInterface.getWinMessage(), new RepositoryEn().getEntityInterface().getWinMessage());
        repositoryRequestHandler = repositoryRequestHandlerRu;
        entityInterface = repositoryRequestHandler.getAnswerRepository().getEntityInterface();
        Assertions.assertEquals(entityInterface.getWinMessage(), new RepositoryRu().getEntityInterface().getWinMessage());
    }

    @ParameterizedTest
    @CsvSource({
            "0", "1", "2", "3",
    })
    public void getPositiveEntityQuestTest(int countLevel) {
        RepositoryRequestHandler repositoryRequestHandlerRu = new RepositoryRequestHandler(RepositoryLanguageType.RU);
        for (int i = 0; i < countLevel; i++) {
            repositoryRequestHandlerRu.lastLevel();
        }
        EntityQuest entityQuest = repositoryRequestHandlerRu.getPositiveEntityQuest();
        Assertions.assertEquals(entityQuest, new RepositoryRu().getEntityPositiveAnswerToLevel(countLevel));
        repositoryRequestHandlerRu.lastLevel();
        RepositoryRequestHandler repositoryRequestHandlerEn = new RepositoryRequestHandler(RepositoryLanguageType.EN);
        for (int i = 0; i < countLevel; i++) {
            repositoryRequestHandlerEn.lastLevel();
        }
        entityQuest = repositoryRequestHandlerEn.getPositiveEntityQuest();
        Assertions.assertEquals(entityQuest, new RepositoryEn().getEntityPositiveAnswerToLevel(countLevel));
    }


    @ParameterizedTest
    @CsvSource({
            "1", "2", "3", "4", "5",
    })
    public void getNegativeEntityQuestTest(int countLevel) {
        RepositoryRequestHandler repositoryRequestHandlerRu = new RepositoryRequestHandler(RepositoryLanguageType.RU);
        for (int i = 0; i < countLevel; i++) {
            repositoryRequestHandlerRu.lastLevel();
        }
        EntityQuest entityQuest = repositoryRequestHandlerRu.getNegativeEntityQuest();
        Assertions.assertEquals(entityQuest, new RepositoryRu().getEntityNegativeAnswerToLevel(countLevel));
        repositoryRequestHandlerRu.lastLevel();
        RepositoryRequestHandler repositoryRequestHandlerEn = new RepositoryRequestHandler(RepositoryLanguageType.EN);
        for (int i = 0; i < countLevel; i++) {
            repositoryRequestHandlerEn.lastLevel();
        }
        entityQuest = repositoryRequestHandlerEn.getNegativeEntityQuest();
        Assertions.assertEquals(entityQuest, new RepositoryEn().getEntityNegativeAnswerToLevel(countLevel));
    }

    @ParameterizedTest
    @CsvSource({
            "POSITIVE", "NEGATIVE",
    })
    public void EntityQuestSelectionTest(AnswerType answerType) {
        if (answerType == AnswerType.POSITIVE) {
            repositoryRequestHandlerRu.lastLevel();
            EntityQuest entityQuest = repositoryRequestHandlerRu.getPositiveEntityQuest();
            Assertions.assertEquals(entityQuest.getMessage(), new RepositoryRu().
                    getEntityPositiveAnswerToLevel(repositoryRequestHandlerRu.getCountLevel()).getMessage());
            repositoryRequestHandlerEn.lastLevel();
            entityQuest = repositoryRequestHandlerEn.getPositiveEntityQuest();
            Assertions.assertEquals(entityQuest.getMessage(), new RepositoryEn().
                    getEntityPositiveAnswerToLevel(repositoryRequestHandlerEn.getCountLevel()).getMessage());
        } else {
            repositoryRequestHandlerRu.lastLevel();
            EntityQuest entityQuest = repositoryRequestHandlerRu.getNegativeEntityQuest();
            Assertions.assertEquals(entityQuest.getMessage(), new RepositoryRu().
                    getEntityNegativeAnswerToLevel(repositoryRequestHandlerRu.getCountLevel()).getMessage());
            repositoryRequestHandlerEn.lastLevel();
            entityQuest = repositoryRequestHandlerEn.getNegativeEntityQuest();
            Assertions.assertEquals(entityQuest.getMessage(), new RepositoryEn().
                    getEntityNegativeAnswerToLevel(repositoryRequestHandlerEn.getCountLevel()).getMessage());
        }
    }
}