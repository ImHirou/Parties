package me.secretlovers.parties;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class Party {
    Main main;
    private UUID uuid;
    private ArrayList<Player> players;
    private Player leader;
    private ArrayList<Player> invitedPlayers;
    public Party(Main main) {
        this.main = main;
        this.uuid = null;
        this.players = new ArrayList<>();
        this.leader = null;
        this.invitedPlayers = new ArrayList<>();
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public Player getLeader() {
        return leader;
    }

    public void setLeader(Player leader) {
        this.leader = leader;
    }

    public ArrayList<Player> getInvitedPlayers() {
        return invitedPlayers;
    }

    public void setInvitedPlayers(ArrayList<Player> invitedPlayers) {
        this.invitedPlayers = invitedPlayers;
    }

}
