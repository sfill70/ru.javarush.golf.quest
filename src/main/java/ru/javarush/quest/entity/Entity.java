package ru.javarush.quest.entity;

public class Entity {
    public String message;

    public String messageEndGame;
    public boolean isGameOver;

    public Entity(String message, boolean isGameOver) {
        this.message = message;
        this.isGameOver = isGameOver;
    }

    public Entity(String message, String messageEndGame, boolean isGameOver) {
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
