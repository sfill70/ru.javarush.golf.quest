package ru.javarush.quest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.javarush.quest.handler.RepositorySelection;
import ru.javarush.quest.repository.RepositoryEn;
import ru.javarush.quest.repository.RepositoryLanguageType;
import ru.javarush.quest.repository.RepositoryRu;

public class RepositorySelectionTest {

    @Test
    public void creatRepositoryTest() {
        Assertions.assertEquals(new RepositorySelection().creatRepository(RepositoryLanguageType.RU).getClass(),
                RepositoryRu.class);
        Assertions.assertEquals(new RepositorySelection().creatRepository(RepositoryLanguageType.EN).getClass(),
                RepositoryEn.class);
    }
}
