package me.agns.snowevent;

import me.agns.snowevent.commands.TeamCommand;
import me.agns.snowevent.event.ProjectHitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class SnowEvent extends JavaPlugin {
    private static final ArrayList<Team> teams = new ArrayList<>();


    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new ProjectHitEvent(), this);
        getServer().getPluginCommand("Team").setExecutor(new TeamCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static void addTeam(Team team) {
        teams.add(team);
    }

    public static ArrayList<Team> getTeams() {
        return teams;
    }
}
