package me.secretlovers.parties.commands;

import me.secretlovers.parties.Main;
import me.secretlovers.parties.Party;
import me.secretlovers.parties.commands.utils.PartyCommandUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class PartyCommands implements CommandExecutor {
    private Main main;
    public PartyCommands(Main main) {
        this.main = main;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)) return true;

        Player player = (Player) sender;
        Party playerParty = null;
        for(Party p : main.getParty()) {
            if(p.getPlayers().contains(player)) {
                playerParty = p;
                break;
            }
        }

        if(args[0].equals("create")) {

            if(playerParty != null) {
                player.sendMessage(ChatColor.RED + "You're already in a party");
                return true;
            }
            PartyCommandUtils.PartyCreate(player);
            return true;
        }
        else if(args[0].equals("i") || args[0].equals("invite")) {

            if(playerParty==null) {
                player.sendMessage(ChatColor.YELLOW + "You're " + ChatColor.RED + "NOT " + ChatColor.YELLOW + "in a party");
                return true;
            }
            if(playerParty.getLeader() != player) {
                player.sendMessage(ChatColor.YELLOW + "You're " + ChatColor.RED + "NOT " + ChatColor.YELLOW + "a leader of a party");
                return true;
            }
            Player target = Bukkit.getPlayer(args[1]);
            if(target == null) {
                player.sendMessage(ChatColor.RED + "Player offline");
                return true;
            }
            for(Party p : main.getParty()) {
                if(p.getInvitedPlayers().contains(target)) {
                    player.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + target.getDisplayName() + ChatColor.YELLOW + " already invited to a party");
                    return true;
                }
            }
            for(Party p : main.getParty()) {
                if(p.getPlayers().contains(target)) {
                    player.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + target.getDisplayName() + ChatColor.YELLOW + " already in a party");
                    return true;
                }
            }

            PartyCommandUtils.PartyInvite(target, playerParty);
            target.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + player.getDisplayName() + ChatColor.YELLOW + " invited you to a party");
            player.sendMessage(ChatColor.YELLOW + "You're invited " + ChatColor.AQUA + "" + ChatColor.BOLD + target.getDisplayName() + ChatColor.YELLOW + " to a party");
        }

        else if(args[0].equals("accept")) {

            if(playerParty!=null) {
                player.sendMessage(ChatColor.RED + "You're already at a party");
                return true;
            }
            for(Party p : main.getParty()) {
                if(p.getInvitedPlayers().contains(player)) {
                    PartyCommandUtils.PartyAccept(player, p);
                    player.sendMessage(ChatColor.YELLOW + "Now you are in a party!");
                    return true;
                }
            }
            player.sendMessage(ChatColor.RED + "You're not invited to a party");

        }
        else if(args[0].equals("list")) {
            if(playerParty == null) {
                player.sendMessage(ChatColor.RED + "You're not in a party");
                return true;
            }
            PartyCommandUtils.PartyList(player, playerParty);
        }
        else if(args[0].equals("teleport")) {
            if(playerParty == null) {
                player.sendMessage(ChatColor.RED + "You're not in a party");
                return true;
            }
            if(playerParty.getLeader() != player) {
                player.sendMessage(ChatColor.YELLOW + "You're " + ChatColor.RED + "NOT " + ChatColor.YELLOW + "a leader of a party");
                return true;
            }

            PartyCommandUtils.PartyTeleport(player, playerParty);

        }
        else if(args[0].equals("leave")) {
            if(playerParty == null) {
                player.sendMessage(ChatColor.RED + "You're not in a party");
                return true;
            }

            PartyCommandUtils.PartyLeave(player, playerParty);

        }
        else if(args[0].equals("chat")) {
            if(playerParty == null) {
                player.sendMessage(ChatColor.RED + "You're not in a party");
                return true;
            }
            String message = "";
            for(int i=1; i< args.length; i++) {
                message += args[i] + " ";
            }
            PartyCommandUtils.PartyChat(player, playerParty, message);

        }



        return true;
    }
}
