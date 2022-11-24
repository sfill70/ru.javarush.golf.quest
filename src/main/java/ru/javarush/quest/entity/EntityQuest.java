package ru.javarush.quest.entity;

import java.util.Objects;

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

    public boolean isGameOver() {
        return isGameOver;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntityQuest that = (EntityQuest) o;

        if (isGameOver != that.isGameOver) return false;
        return Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        int result = message != null ? message.hashCode() : 0;
        result = 31 * result + (isGameOver ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "EntityQuest{" +
                "message='" + message + '\'' +
                ", isGameOver=" + isGameOver +
                '}';
    }
}
