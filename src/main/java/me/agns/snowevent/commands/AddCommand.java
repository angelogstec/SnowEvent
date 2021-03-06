package me.agns.snowevent.commands;

import me.agns.snowevent.SnowEvent;
import me.agns.snowevent.Team;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class AddCommand implements CommandExecutor {
    //team add <player> <time>
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        boolean findout = false;
        Player playerSender = null;
        if (sender instanceof Player) {
            playerSender = (Player) sender;
        }
        if (args.length == 2 && playerSender != null) {
            ArrayList<Team> teams = SnowEvent.getTeams();
            for (Team team : teams) {
                ArrayList<Player> players = team.getPlayers();
                if (players.contains(playerSender)) {
                    team.addPlayer(Bukkit.getPlayerExact(args[1]));
                    findout = true;
                    break;
                }
            }
            if (!findout) {
                playerSender.sendMessage("Error, You don't stay in one time, use /team add <player> <time>");
            } else {
                playerSender.sendMessage("Success");
            }
            return true;
        } else if (args.length == 3) {
            ArrayList<Team> teams = SnowEvent.getTeams();
            for (Team team : teams) {
                if (team.getName().equalsIgnoreCase(args[2])) {
                    team.addPlayer(Bukkit.getPlayerExact(args[1]));
                    findout = true;
                    break;
                }
            }
            if (!findout) {
                sender.sendMessage("Error, use /team <player> <time>");
            } else {
                sender.sendMessage("Success");
            }
            return true;
        }
        sender.sendMessage("use /team add <player> <time>");
        return true;
    }
}
