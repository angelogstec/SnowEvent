package me.agns.snowevent.commands;

import me.agns.snowevent.SnowEvent;
import me.agns.snowevent.Team;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class DeleteCommand implements CommandExecutor {
    //team <delete> <time>
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        boolean findout = false;
        boolean isPlayer = false;
        Player playerSender = null;
        if (sender instanceof Player){
            playerSender = (Player) sender;
            isPlayer = true;
        }
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

}
