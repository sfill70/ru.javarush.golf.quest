package ru.javarush.quest.entity;

public class EntityQuest {
    public String message;

    public String messageEndGame;
    public boolean isGameOver;

    public EntityQuest(){}

    public EntityQuest(String message, boolean isGameOver) {
        this.message = message;
        this.isGameOver = isGameOver;
    }

    public EntityQuest(String message, String messageEndGame, boolean isGameOver) {
        this.message = message;
        this.messageEndGame = messageEndGame;
        this.isGameOver = isGameOver;
    }

    public String getMessage() {
        return message;
    }

    public String getMessageEndGame() {
        return messageEndGame;
    }

    public boolean isGameOver() {
        return isGameOver;
    }
}
