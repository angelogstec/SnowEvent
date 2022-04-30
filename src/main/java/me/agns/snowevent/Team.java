package me.agns.snowevent;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;

public class Team {
    private final String name;
    private final ArrayList<Player> players = new ArrayList<>();

    public Team(String name) {
        this.name = name;
    }

    public void addPlayer(Player player){
        this.players.add(player);
    }

    public ArrayList<Player> getPlayers() {
        return (ArrayList<Player>) Collections.unmodifiableList(players);
    }

    public String getName() {
        return name;
    }

    public void removePlayer(Player player){
        this.players.remove(player);
    }
}
