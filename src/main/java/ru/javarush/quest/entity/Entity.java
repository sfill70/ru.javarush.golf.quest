package ru.javarush.quest.entity;

public class Entity {
    public String message;
    public boolean isGameOver;

    public Entity(String message, boolean isGameOver) {
        this.message = message;
        this.isGameOver = isGameOver;
    }

    public String getMessage() {
        return message;
    }

    public boolean isGameOver() {
        return isGameOver;
    }
}
