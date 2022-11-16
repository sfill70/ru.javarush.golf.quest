package ru.javarush.quest.entity;

public class EntityStatistics {
    String ip;
    String name;
    int gamesQuanity;
    String language;

    public EntityStatistics(String ip, String name, int gamesQuanity, String language) {
        this.ip = ip;
        if(ip.isEmpty()||ip.isBlank()){
            this.ip = "undefined";
        }
        this.name = name;
        this.gamesQuanity = gamesQuanity;
        this.language = language;
    }

    public String getIp() {
        return ip;
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
}
