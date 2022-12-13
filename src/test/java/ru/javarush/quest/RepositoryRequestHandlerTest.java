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
import ru.javarush.quest.repository.AnswerRepository;
import ru.javarush.quest.repository.RepositoryEn;
import ru.javarush.quest.repository.RepositoryLanguageType;
import ru.javarush.quest.repository.RepositoryRu;

@ExtendWith(MockitoExtension.class)
public class RepositoryRequestHandlerTest {
    AnswerRepository answerRepositoryRu;
    AnswerRepository answerRepositoryEn;

    EntityQuest entityQuest;

    @BeforeEach
    public void init() {
        answerRepositoryRu = new RepositoryRu();
        answerRepositoryEn = new RepositoryEn();
        entityQuest = new EntityQuest("answer", true);
    }


    @Test
    public void lastLevelTest() {
        RepositoryRequestHandler repositoryRequestHandler = new RepositoryRequestHandler(RepositoryLanguageType.EN);
        repositoryRequestHandler.lastLevel();
        Assertions.assertEquals(repositoryRequestHandler.getCountLevel(), 1);
    }

    @Test
    public void getEntityInterfaceTest() {
        RepositoryRequestHandler repositoryRequestHandler = new RepositoryRequestHandler(RepositoryLanguageType.RU);
        EntityInterface entityInterface = repositoryRequestHandler.getEntityInterface();
        Assertions.assertEquals(entityInterface, answerRepositoryRu.getEntityInterface());
        repositoryRequestHandler = new RepositoryRequestHandler(RepositoryLanguageType.EN);
        entityInterface = repositoryRequestHandler.getEntityInterface();
        Assertions.assertEquals(entityInterface, answerRepositoryEn.getEntityInterface());
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
        Assertions.assertEquals(entityQuest, answerRepositoryRu.getEntityPositiveAnswerToLevel(countLevel));

        RepositoryRequestHandler repositoryRequestHandlerEn = new RepositoryRequestHandler(RepositoryLanguageType.EN);
        for (int i = 0; i < countLevel; i++) {
            repositoryRequestHandlerEn.lastLevel();
        }
        entityQuest = repositoryRequestHandlerEn.getPositiveEntityQuest();
        Assertions.assertEquals(entityQuest, answerRepositoryEn.getEntityPositiveAnswerToLevel(countLevel));
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
        Assertions.assertEquals(entityQuest, answerRepositoryRu.getEntityNegativeAnswerToLevel(countLevel));

        RepositoryRequestHandler repositoryRequestHandlerEn = new RepositoryRequestHandler(RepositoryLanguageType.EN);
        for (int i = 0; i < countLevel; i++) {
            repositoryRequestHandlerEn.lastLevel();
        }
        entityQuest = repositoryRequestHandlerEn.getNegativeEntityQuest();
        Assertions.assertEquals(entityQuest, answerRepositoryEn.getEntityNegativeAnswerToLevel(countLevel));
    }


    @Test
    public void entityQuestSelectionTest() {
        RepositoryRequestHandler repositoryRequestHandlerRu = new RepositoryRequestHandler(RepositoryLanguageType.RU);
        RepositoryRequestHandler repositoryRequestHandlerEn = new RepositoryRequestHandler(RepositoryLanguageType.EN);

        for (int i = 0; i < 5; i++) {
            repositoryRequestHandlerRu.entityQuestSelection(AnswerType.POSITIVE);
            Assertions.assertEquals(repositoryRequestHandlerRu.getEntityQuest(), answerRepositoryRu.
                    getEntityPositiveAnswerToLevel(repositoryRequestHandlerRu.getCountLevel()));
            repositoryRequestHandlerRu.lastLevel();

            repositoryRequestHandlerEn.entityQuestSelection(AnswerType.NEGATIVE);
            Assertions.assertEquals(repositoryRequestHandlerEn.getEntityQuest(), answerRepositoryEn.
                    getEntityNegativeAnswerToLevel(repositoryRequestHandlerEn.getCountLevel()));
            repositoryRequestHandlerEn.lastLevel();
        }
    }

    @Test
    public void getMessageTest() {
        Assertions.assertEquals(answerRepositoryEn.getEntityNegativeAnswerToLevel(1).getMessage(), "Answer1");
        Assertions.assertEquals(answerRepositoryEn.getEntityPositiveAnswerToLevel(1).getMessage(), "Question1");
    }

    @Test
    public void IsGameOverTest() {
        Assertions.assertFalse(answerRepositoryEn.getEntityPositiveAnswerToLevel(1).isGameOver());
        Assertions.assertTrue(answerRepositoryEn.getEntityNegativeAnswerToLevel(1).isGameOver());
    }

    @Test
    public void IsVictory() {
        Assertions.assertTrue(!answerRepositoryEn.getEntityPositiveAnswerToLevel(5).isGameOver()
                && answerRepositoryEn.getSize() - 1 == 5);
    }
}