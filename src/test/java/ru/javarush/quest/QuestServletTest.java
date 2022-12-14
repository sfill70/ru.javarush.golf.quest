package ru.javarush.quest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.javarush.quest.entity.EntityStatistics;
import ru.javarush.quest.handler.RepositoryRequestHandler;
import ru.javarush.quest.repository.RepositoryEn;
import ru.javarush.quest.repository.RepositoryLanguageType;
import ru.javarush.quest.repository.RepositoryRu;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class QuestServletTest {
    public QuestServlet questServlet;

    @BeforeEach
    public void init() throws ServletException {
        this.questServlet = new QuestServlet();
        questServlet.init();
    }

    @ParameterizedTest
    @CsvSource({
            "RU",
            "EN",
    })
    public void getRepositoryRequestHandlerTest(String language) {
        RepositoryRequestHandler repositoryRequestHandler = questServlet.getRepositoryRequestHandler(language);
        if (language.equalsIgnoreCase("RU")) {
            Assertions.assertEquals(repositoryRequestHandler.getAnswerRepository().getClass(), RepositoryRu.class);
        } else {
            Assertions.assertEquals(repositoryRequestHandler.getAnswerRepository().getClass(), RepositoryEn.class);
        }
    }

    @ParameterizedTest
    @CsvSource({
            "name, RU",
            "name, EN",
    })
    public void getDataFromRequestTest(String name, String languageFromRequest) {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("username")).thenReturn(name);
        when(request.getParameter("choiceLanguage")).thenReturn(languageFromRequest);
        EntityStatistics entityStatistics = questServlet.getDataFromRequest(request);
        if (languageFromRequest.equalsIgnoreCase("RU")) {
            Assertions.assertEquals(questServlet.repositoryRequestHandler.getAnswerRepository().getClass(), RepositoryRu.class);
            Assertions.assertEquals(entityStatistics, new EntityStatistics(name, 1, "RU"));
        } else {
            Assertions.assertEquals(questServlet.repositoryRequestHandler.getAnswerRepository().getClass(), RepositoryEn.class);
            Assertions.assertEquals(entityStatistics, new EntityStatistics(name, 2, "EN"));
        }
    }


    @ParameterizedTest
    @CsvSource({
            "positiveAnswer",
            "negativeAnswer",
    })
    public void choiceEntityQuestTest(String answerFromRequest) {
        HttpServletRequest request = mock(HttpServletRequest.class);
        questServlet.repositoryRequestHandler = new RepositoryRequestHandler(RepositoryLanguageType.RU);
        if (answerFromRequest.equalsIgnoreCase("positiveAnswer")) {
            when(request.getParameter("choice")).thenReturn("positiveAnswer");
            questServlet.choiceEntityQuest(request);
            Assertions.assertEquals(questServlet.getRepositoryRequestHandler().getPositiveEntityQuest(),
                    new RepositoryRu().getEntityPositiveAnswerToLevel(1));
        } else {
            when(request.getParameter("choice")).thenReturn("negativeAnswer");
            questServlet.choiceEntityQuest(request);
            Assertions.assertEquals(questServlet.repositoryRequestHandler.getNegativeEntityQuest(),
                    new RepositoryRu().getEntityNegativeAnswerToLevel(1));
        }
    }
}
