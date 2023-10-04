package me.secretlovers.parties;

import me.secretlovers.parties.commands.PartyCommands;
import me.secretlovers.parties.commands.utils.PartyCommandUtils;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class Main extends JavaPlugin {

    private ArrayList<Party> party = new ArrayList<>();
    private Party parties = new Party(this);
    private PartyCommandUtils partyCommandUtils = new PartyCommandUtils(this);

    @Override
    public void onEnable() {

        getCommand("party").setExecutor(new PartyCommands(this));

    }

    public ArrayList<Party> getParty() {
        return party;
    }

    public void setParty(ArrayList<Party> party) {
        this.party = party;
    }

    public Party getParties() {
        return parties;
    }

    public void setParties(Party parties) {
        this.parties = parties;
    }

    public PartyCommandUtils getPartyCommandUtils() {
        return partyCommandUtils;
    }

    public void setPartyCommandUtils(PartyCommandUtils partyCommandUtils) {
        this.partyCommandUtils = partyCommandUtils;
    }
}
