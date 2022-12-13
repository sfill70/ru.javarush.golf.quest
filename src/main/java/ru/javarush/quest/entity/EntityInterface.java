package ru.javarush.quest.entity;

import java.util.Arrays;
import java.util.Objects;

public class EntityInterface {
    String startMessage;
    String positiveNameButton;
    String negativeNameButton;
    String answerButton;
    String winMessage;
    String lossMessage;
    String[] statistic;

    public EntityInterface(String startMessage, String positiveNameButton,
                           String negativeNameButton, String answerButton, String winMessage,
                           String lossMessage, String[] statistic) {
        this.startMessage = startMessage;
        this.positiveNameButton = positiveNameButton;
        this.negativeNameButton = negativeNameButton;
        this.answerButton = answerButton;
        this.winMessage = winMessage;
        this.lossMessage = lossMessage;
        this.statistic = statistic;
    }

    public String getPositiveNameButton() {
        return positiveNameButton;
    }

    public String getNegativeNameButton() {
        return negativeNameButton;
    }

    public String getAnswerButton() {
        return answerButton;
    }

    public String getWinMessage() {
        return winMessage;
    }

    public String getLossMessage() {
        return lossMessage;
    }

    public String[] getStatistic() {
        return statistic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntityInterface that = (EntityInterface) o;

        if (!Objects.equals(startMessage, that.startMessage)) return false;
        if (!Objects.equals(positiveNameButton, that.positiveNameButton))
            return false;
        if (!Objects.equals(negativeNameButton, that.negativeNameButton))
            return false;
        if (!Objects.equals(answerButton, that.answerButton)) return false;
        if (!Objects.equals(winMessage, that.winMessage)) return false;
        if (!Objects.equals(lossMessage, that.lossMessage)) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(statistic, that.statistic);
    }

    @Override
    public int hashCode() {
        int result = startMessage != null ? startMessage.hashCode() : 0;
        result = 31 * result + (positiveNameButton != null ? positiveNameButton.hashCode() : 0);
        result = 31 * result + (negativeNameButton != null ? negativeNameButton.hashCode() : 0);
        result = 31 * result + (answerButton != null ? answerButton.hashCode() : 0);
        result = 31 * result + (winMessage != null ? winMessage.hashCode() : 0);
        result = 31 * result + (lossMessage != null ? lossMessage.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(statistic);
        return result;
    }
}
