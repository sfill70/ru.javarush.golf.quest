package ru.javarush.quest.entity;

import java.util.Objects;


public class EntityStatistics {
    String name;
    int gamesQuanity;
    String language;

    public EntityStatistics(String name, int gamesQuanity, String language) {
        this.name = name;
        this.gamesQuanity = gamesQuanity;
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public int getGamesQuanity() {
        return gamesQuanity;
    }

    public String getLanguage() {
        return language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntityStatistics that = (EntityStatistics) o;

        if (gamesQuanity != that.gamesQuanity) return false;
        if (!Objects.equals(name, that.name)) return false;
        return Objects.equals(language, that.language);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + gamesQuanity;
        result = 31 * result + (language != null ? language.hashCode() : 0);
        return result;
    }
}
