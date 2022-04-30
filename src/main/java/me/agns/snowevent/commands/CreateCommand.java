package me.agns.snowevent.commands;

import me.agns.snowevent.SnowEvent;
import me.agns.snowevent.Team;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CreateCommand implements CommandExecutor {
    //team create <name>
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 2) {
            Team team = new Team(ChatColor.translateAlternateColorCodes('&', args[1]));
            SnowEvent.addTeam(team);
            return true;
        }
        sender.sendMessage("Use /team create <name>");
        return true;
    }
}
