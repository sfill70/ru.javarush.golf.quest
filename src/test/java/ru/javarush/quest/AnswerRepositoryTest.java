package ru.javarush.quest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.javarush.quest.repository.RepositoryEn;

public class AnswerRepositoryTest extends RepositoryEn {

    @ParameterizedTest
    @CsvSource({
            "0", "1", "2",
    })
    public void getEntityPositiveAnswerTest(int level) {

        Assertions.assertEquals(getEntityPositiveAnswerToLevel(level), this.positiveAnswer.get(level));
    }

    @ParameterizedTest
    @CsvSource({
            "0", "1", "2",
    })
    public void getEntityNegativeAnswerTest(int level) {
        Assertions.assertEquals(new AnswerRepositoryTest().getEntityNegativeAnswerToLevel(level), this.negativeAnswer.get(level));
    }

    @Test
    public void getEntityInterfaceTest() {
        Assertions.assertEquals(new AnswerRepositoryTest().getEntityInterface(), this.entityInterface);
    }
}
