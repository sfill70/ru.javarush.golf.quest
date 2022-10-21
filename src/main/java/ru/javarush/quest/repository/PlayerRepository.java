package ru.javarush.quest.repository;

import java.util.HashMap;
import java.util.Map;

public class PlayerRepository {
    public static Map<String, Integer> players = new HashMap<>();

    public static int getPlayerCount(String player) {
        if (players.containsKey(player)) {
            players.put(player, players.get(player) + 1);
        } else {
            players.put(player, 1);
        }
        return players.get(player);
    }

}
