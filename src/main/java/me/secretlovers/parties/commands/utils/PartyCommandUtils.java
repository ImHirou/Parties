package me.secretlovers.parties.commands.utils;

import me.secretlovers.parties.Main;
import me.secretlovers.parties.Party;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.*;

public class PartyCommandUtils {
    private static Main main;
    private Party party;
    public PartyCommandUtils(Main main) {
        this.main = main;
        this.party = main.getParties();
    }

    public static void PartyCreate(Player creator) {
        Party newParty = new Party(main);
        newParty.setUuid(UUID.randomUUID());
        newParty.setLeader(creator);
        ArrayList<Player> newPlayer = new ArrayList<>();
        newPlayer.add(creator);
        newParty.setPlayers(newPlayer);
        main.getParty().add(newParty);
        creator.sendMessage(ChatColor.YELLOW + "You " + ChatColor.GREEN + "Successfully " + ChatColor.YELLOW + "Created a party");
    }

    public static void PartyInvite(Player invited, Party newParty) {
        ArrayList<Player> newInvited = newParty.getInvitedPlayers();
        newInvited.add(invited);
        newParty.setInvitedPlayers(newInvited);
    }

    public static void PartyAccept(Player invited, Party newParty) {
        ArrayList<Player> newPlayer = newParty.getPlayers();
        newPlayer.add(invited);
        ArrayList<Player> newInvited = newParty.getInvitedPlayers();
        newInvited.remove(invited);
        newParty.setPlayers(newPlayer);
        newParty.setInvitedPlayers(newInvited);
    }

    public static void PartyList(Player player, Party newParty) {
        ArrayList<Player> newPlayer = newParty.getPlayers();
        for(Player p: newPlayer) {
            player.sendMessage(ChatColor.GRAY + "-->" + ChatColor.AQUA + "" + ChatColor.BOLD + p.getDisplayName());
        }
    }

    public static void PartyTeleport(Player player, Party newParty) {
        ArrayList<Player> newPlayer = newParty.getPlayers();
        for(Player p: newPlayer) {
            p.teleport(player.getLocation());
        }
    }

    public static void PartyLeave(Player player, Party newParty) {
        ArrayList<Player> newPlayer = newParty.getPlayers();
        newPlayer.remove(player);
        newParty.setPlayers(newPlayer);
    }

    public static void PartyChat(Player player, Party newParty, String message) {
        for(Player p: newParty.getPlayers()) {
            p.sendMessage(ChatColor.GRAY + "[" + ChatColor.DARK_PURPLE + "PC" + ChatColor.GRAY + "]" +
                    " " + ChatColor.DARK_PURPLE + ChatColor.BOLD + player.getDisplayName() + ChatColor.GRAY + ": " + ChatColor.DARK_PURPLE + message);
        }
    }

}
