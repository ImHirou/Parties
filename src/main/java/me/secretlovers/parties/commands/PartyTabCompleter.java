package me.secretlovers.parties.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.Arrays;
import java.util.List;

public class PartyTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        if(args.length==1) {
            return Arrays.asList(
                    "create",
                    "invite",
                    "accept",
                    "list",
                    "teleport",
                    "leave",
                    "chat",
                    "kick");
        }
        else {
            return null;
        }
    }
}
