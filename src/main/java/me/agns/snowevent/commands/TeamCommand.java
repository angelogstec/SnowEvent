package me.agns.snowevent.commands;

import me.agns.snowevent.SnowEvent;
import me.agns.snowevent.Team;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class TeamCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        boolean findout = false;
        boolean isPlayer = false;
        Player playerSender = null;
        if (sender instanceof Player){
            playerSender = (Player) sender;
            isPlayer = true;
        }
        //team add <player> <time>
        if (args[0].equalsIgnoreCase("add")) {
            if (args.length == 2 && isPlayer) {
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
        //team create <name>
        else if (args[0].equalsIgnoreCase("create")){
            if (args.length == 2){
                Team team = new Team(ChatColor.translateAlternateColorCodes('&', args[1]));
                SnowEvent.addTeam(team);
                return true;
            }
            sender.sendMessage("Use /team create <name>");
            return true;

        }
        //team remove <player> <time>
        else if (args[0].equalsIgnoreCase("remove")) {
            if (args.length == 2 && isPlayer) {
                ArrayList<Team> teams = SnowEvent.getTeams();
                for (Team team : teams) {
                    ArrayList<Player> players = team.getPlayers();
                    if (players.contains(playerSender) && players.contains(Bukkit.getPlayerExact(args[1]))) {
                        team.removePlayer(Bukkit.getPlayerExact(args[1]));
                        findout = true;
                        break;
                    }
                }
                if (!findout) {
                    playerSender.sendMessage("Error, use /team remove <player> <time>");
                } else {
                    playerSender.sendMessage("Success");
                }
                return true;
            }else if (args.length == 3) {
                ArrayList<Team> teams = SnowEvent.getTeams();
                for (Team team : teams) {
                    if (team.getName().equalsIgnoreCase(args[2]) && team.getPlayers().contains(Bukkit.getPlayerExact(args[1]))) {
                        team.removePlayer(Bukkit.getPlayerExact(args[1]));
                        findout = true;
                        break;
                    }
                }
                if (!findout) {
                    sender.sendMessage("Error, use /team remove <player> <time>");
                } else {
                    sender.sendMessage("Success");
                }
                return true;
            }
            sender.sendMessage("Use /team remove <player> <time>");
            return true;
        }
        //team <delete> <time>
        else if (args[0].equalsIgnoreCase("delete")){
            if (args.length == 2 && isPlayer){
                ArrayList<Team> teams = SnowEvent.getTeams();
                for (Team team : teams){
                    if (team.getPlayers().contains(playerSender)){
                        teams.remove(team);
                        findout = true;
                    }
                }
                if (findout){
                    playerSender.sendMessage("Sucess");
                }else {
                    playerSender.sendMessage("Error, you don't stay in one time");
                }
                return true;
            }else if (args.length == 2){
                ArrayList<Team> teams = SnowEvent.getTeams();
                for (Team team : teams){
                    if (team.getName().equals(args[1])){
                        teams.remove(team);
                        findout = true;
                    }
                }
                if (findout){
                    sender.sendMessage("Sucess");
                }else {
                    sender.sendMessage("Error, this time not exist");
                }
                return true;
            }
            sender.sendMessage("Use /team <delete> <time>");
            return true;
        }
        sender.sendMessage("Use /team <add, create, remove, delete>");
        return true;
    }
}
