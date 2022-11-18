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
import ru.javarush.quest.logics.RepositoryRequestHandler;
import ru.javarush.quest.repository.RepositoryEn;
import ru.javarush.quest.repository.RepositoryRu;

@ExtendWith(MockitoExtension.class)
public class RepositoryRequestHandlerTest {
    RepositoryRequestHandler repositoryRequestHandlerEn;
    RepositoryRequestHandler repositoryRequestHandlerRu;

    @BeforeEach
    public void init() {
        repositoryRequestHandlerEn = new RepositoryRequestHandler("EN");
        repositoryRequestHandlerRu = new RepositoryRequestHandler("RU");
    }


    @Test
    public void lastLevelTest() {
        RepositoryRequestHandler repositoryRequestHandler = new RepositoryRequestHandler("EN");
        repositoryRequestHandler.nextLevel();
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

    /*@Test*/
    @ParameterizedTest
    @CsvSource({
            "1", "2", "3", "4",
    })
    public void getPositiveEntityQuestTest(int countLevel) {
        EntityQuest entityQuest = repositoryRequestHandlerRu.getAnswerRepository().getEntityPositiveAnswer(countLevel);
        Assertions.assertEquals(entityQuest.getMessage(), new RepositoryRu().getEntityPositiveAnswer(countLevel).getMessage());
        entityQuest = repositoryRequestHandlerEn.getAnswerRepository().getEntityPositiveAnswer(countLevel);
        Assertions.assertEquals(entityQuest.getMessage(), new RepositoryEn().getEntityPositiveAnswer(countLevel).getMessage());
    }

    /*@Test*/
    @ParameterizedTest
    @CsvSource({
            "1", "2", "3", "4", "5",
    })
    public void getNegativeEntityQuestTest(int countLevel) {
        EntityQuest entityQuest = repositoryRequestHandlerEn.getAnswerRepository().getEntityNegativeAnswer(countLevel);
        Assertions.assertEquals(entityQuest.getMessage(), new RepositoryEn().getEntityNegativeAnswer(countLevel).getMessage());
        entityQuest = repositoryRequestHandlerRu.getAnswerRepository().getEntityNegativeAnswer(countLevel);
        Assertions.assertEquals(entityQuest.getMessage(), new RepositoryRu().getEntityNegativeAnswer(countLevel).getMessage());
    }

    @ParameterizedTest
    @CsvSource({
            "true", "true", "true", "false", "false", "false",
    })
    public void EntityQuestSelectionTest(boolean positiveAnswer) {

        if (positiveAnswer) {
            repositoryRequestHandlerRu.nextLevel();
            EntityQuest entityQuest = repositoryRequestHandlerRu.getPositiveEntityQuest();
            Assertions.assertEquals(entityQuest.getMessage(), new RepositoryRu().
                    getEntityPositiveAnswer(repositoryRequestHandlerRu.getCountLevel()).getMessage());
            repositoryRequestHandlerEn.nextLevel();
            entityQuest = repositoryRequestHandlerEn.getPositiveEntityQuest();
            Assertions.assertEquals(entityQuest.getMessage(), new RepositoryEn().
                    getEntityPositiveAnswer(repositoryRequestHandlerEn.getCountLevel()).getMessage());
        }
        repositoryRequestHandlerRu.nextLevel();
        EntityQuest entityQuest = repositoryRequestHandlerRu.getNegativeEntityQuest();
        Assertions.assertEquals(entityQuest.getMessage(), new RepositoryRu().
                getEntityNegativeAnswer(repositoryRequestHandlerRu.getCountLevel()).getMessage());
        repositoryRequestHandlerEn.nextLevel();
        entityQuest = repositoryRequestHandlerEn.getNegativeEntityQuest();
        Assertions.assertEquals(entityQuest.getMessage(), new RepositoryEn().
                getEntityNegativeAnswer(repositoryRequestHandlerEn.getCountLevel()).getMessage());
    }

}