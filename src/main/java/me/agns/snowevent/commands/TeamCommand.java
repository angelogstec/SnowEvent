package me.agns.snowevent.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TeamCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args[0].equalsIgnoreCase("add")){
            new AddCommand().onCommand(sender, command, label, args);
        }else if (args[0].equalsIgnoreCase("create")){
            new CreateCommand().onCommand(sender, command, label, args);
        }else if(args[0].equalsIgnoreCase("delete")){
            new DeleteCommand().onCommand(sender, command, label, args);
        }else if (args[0].equalsIgnoreCase("remove")){
            new RemoveCommand().onCommand(sender, command, label, args);
        }else {
            sender.sendMessage("Use /team <add, create, remove, delete>");
        }
        return true;
    }
}
