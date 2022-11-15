package ru.javarush.quest.entity;

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

    public String getStartMessage() {
        return startMessage;
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
}
